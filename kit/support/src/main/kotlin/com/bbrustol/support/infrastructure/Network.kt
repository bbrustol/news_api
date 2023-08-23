package com.bbrustol.support.infrastructure

import android.content.Context
import com.bbrustol.support.domain.usecase.error.exception.NetworkException
import com.bbrustol.support.domain.usecase.error.exception.ServiceException
import com.bbrustol.support.extesion.isNetworkAvailable
import com.bbrustol.support.extesion.logTag
import com.bbrustol.support.utils.Either
import com.bbrustol.support.utils.RetailLogger
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Headers
import retrofit2.Call
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton
@Singleton
class Network @Inject constructor(
    @ApplicationContext private val context: Context
) {

    @Throws(NetworkException::class, ServiceException::class)
    suspend fun <T : Any> handleApi(request: suspend () -> Call<T>): Either<ServiceResponse<T>, ServiceErrorResponse> {

        return try {
            context.isNetworkAvailable()

            with(request().execute()) {
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
                request().request().url().toString()
            )
        } catch (e: IOException) {
            RetailLogger.e(logTag, e.toString())
            throw ServiceException(e.message ?: "", request().request().url().toString())
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