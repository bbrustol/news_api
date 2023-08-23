package com.bbrustol.support.domain.usecase

import app.cash.turbine.test
import com.bbrustol.support.domain.usecase.error.GlobalErrorHandlerModel
import com.bbrustol.support.domain.usecase.error.GlobalErrorManager
import com.bbrustol.support.domain.usecase.error.GlobalErrorMapper
import com.bbrustol.support.domain.usecase.error.GlobalErrorType
import com.bbrustol.support.utils.BaseUnitTest
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
class GlobalErrorManagerTest: BaseUnitTest() {

    private var globalErrorMapper = mockk<GlobalErrorMapper>()

    private lateinit var globalErrorManager: GlobalErrorManager

    @Before
    override fun setUp() {
        globalErrorManager = GlobalErrorManager(globalErrorMapper)
    }

    @Test
    fun `when emit error is called, then map the exception and emit an event`() = testRule.runTest {
        val throwable = Throwable()
        every { globalErrorMapper.map(any()) } returns GlobalErrorType.GENERIC_ERROR
        globalErrorManager.event.test {
            globalErrorManager.emitError(throwable)
            assertEquals(GlobalErrorHandlerModel(GlobalErrorType.GENERIC_ERROR), awaitItem())
        }
    }
}

