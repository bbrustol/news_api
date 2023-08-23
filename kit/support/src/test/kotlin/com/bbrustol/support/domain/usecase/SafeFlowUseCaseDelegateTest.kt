package com.bbrustol.support.domain.usecase

import app.cash.turbine.test
import com.bbrustol.support.domain.usecase.error.GlobalErrorManager
import com.bbrustol.support.utils.BaseUnitTest
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class SafeFlowUseCaseDelegateTest: BaseUnitTest() {

    private var globalErrorManager = mockk<GlobalErrorManager>()
    private var repositoryTest =  mockk<RepositoryTest>()

    private lateinit var safeFlowUseCase: SafeFlowUseCaseDelegate
    private lateinit var useCase: FlowUseCaseTest

    @Before
    override fun setUp() {
        safeFlowUseCase = SafeFlowUseCaseDefault(globalErrorManager)
        useCase = FlowUseCaseTest(repositoryTest)
    }

    @Test
    fun `when a use case return an exception, the delegate emit an error`() {
        testRule.runTest {
            every { repositoryTest.testString() } throws Exception()
            with(safeFlowUseCase) {
                useCase.safePrepare().test {
                    coVerify(exactly = 1) { globalErrorManager.emitError(any()) }
                    awaitComplete()
                }
            }
        }
    }

    @Test
    fun `when a use case return a normal result, the delegate return the result`() {
        testRule.runTest {
            val response = "0"
            every { repositoryTest.testString() } returns response
            with(safeFlowUseCase) {
                useCase.safePrepare(Unit).test {
                    coVerify(exactly = 0) { safeFlowUseCase.globalErrorManager.emitError(any()) }
                    assertEquals(response, awaitItem())
                    awaitComplete()
                }
            }
        }
    }

    class FlowUseCaseTest(private val repositoryTest: RepositoryTest) :
        FlowUseCase<Unit, String>(UnconfinedTestDispatcher()) {
        override fun prepareFlow(input: Unit): Flow<String> = flow { emit(repositoryTest.testString()) }
    }

    interface RepositoryTest {
        fun testString(): String
    }
}
