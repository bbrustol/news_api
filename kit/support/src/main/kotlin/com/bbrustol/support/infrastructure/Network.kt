package com.bbrustol.support.infrastructure

import com.bbrustol.support.domain.network.NetworkManager
import com.bbrustol.support.domain.usecase.error.exception.NetworkException
import com.bbrustol.support.domain.usecase.error.exception.ServiceException
import com.bbrustol.support.extesion.logTag
import com.bbrustol.support.utils.Either
import com.bbrustol.support.utils.RetailLogger
import okhttp3.Headers
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton
@Singleton
class Network @Inject constructor(
//    private val networkManager: NetworkManager
) {

    @Throws(NetworkException::class, ServiceException::class)
    suspend fun <T> handleApi(request: suspend () -> Response<T>): Either<ServiceResponse<T>, ServiceErrorResponse> {

//        networkManager.checkConnectivity()

        return try {
            with(request()) {
                if (isSuccessful) {
                    Either.Success(
                        ServiceResponse(
                            optData = body(),
                            statusCode = code(),
                            headers = headers()
                        )
                    )
                } else {
                    Either.Failure(
                        ServiceErrorResponse(
                            description = errorBody()?.string(),
                            statusCode = code(),
                            headers = headers()
                        )
                    )
                }
            }
        } catch (e: HttpException) {
            RetailLogger.e(logTag, e.toString())
            throw ServiceException(
                e.message ?: "",
                request().errorBody().toString()
            )
        } catch (e: IOException) {
            RetailLogger.e(logTag, e.toString())
            throw ServiceException(e.message ?: "", request().errorBody().toString())
        }
    }

    data class ServiceResponse<T>(
        val optData: T?,
        val statusCode: Int,
        val headers: Headers,
    ) {
        val data: T
            get() = optData!!
    }

    data class ServiceErrorResponse(
        val description: String?,
        val statusCode: Int,
        val headers: Headers,
    )

}