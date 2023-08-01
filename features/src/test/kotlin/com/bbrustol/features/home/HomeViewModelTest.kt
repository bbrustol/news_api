package com.bbrustol.features.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.bbrustol.core.data.infrastructure.ApiError
import com.bbrustol.core.data.infrastructure.ApiException
import com.bbrustol.core.data.infrastructure.ApiSuccess
import com.bbrustol.core.data.infrastructure.ResourceUtils
import com.bbrustol.core.data.remote.newsapi.model.response.headline.HeadlineResponse
import com.bbrustol.core.data.repository.NewsApiRepository
import com.bbrustol.features.home.HomeViewModel.*
import com.squareup.moshi.Moshi
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val newsApiRepository = mockk<NewsApiRepository>()

    private lateinit var viewModel: HomeViewModel

    private lateinit var moshi: Moshi

    @Before
    fun setUp() {
        moshi = Moshi.Builder().build()
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }

    private fun getHeadlinesMock(): HeadlineResponse {
        val response = ResourceUtils().openFile("headlines_200.json")
        val adapter = moshi.adapter(HeadlineResponse::class.java)
        return adapter.fromJson(response)!!
    }

    @Test
    fun `WHEN viewModel is loaded, THEN State going to be an Idle`() = runTest {
        viewModel = HomeViewModel(newsApiRepository)

        viewModel.uiState.test {
            assertTrue(awaitItem() is UiState.Idle)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `WHEN fetch getHeadline is requested and receive a throwable before emit, THEN State going to be an Catch`() = runTest {

        coEvery { newsApiRepository.getHeadline() } returns flow { throw IllegalStateException() }

        viewModel = HomeViewModel(newsApiRepository)
        viewModel.fetchHeadline()

        viewModel.uiState.test {
            assertTrue(awaitItem() is UiState.Catch)
            coVerify(exactly = 1) { newsApiRepository.getHeadline() }
            confirmVerified(newsApiRepository)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `WHEN fetch getHeadline is requested unsuccessfully, THEN State going to be an Catch`() = runTest {

        coEvery { newsApiRepository.getHeadline() } returns flow { emit(ApiException(Throwable(""))) }

        viewModel = HomeViewModel(newsApiRepository)
        viewModel.fetchHeadline()

        viewModel.uiState.test {
            assertTrue(awaitItem() is UiState.Catch)
            coVerify(exactly = 1) { newsApiRepository.getHeadline() }
            confirmVerified(newsApiRepository)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `WHEN fetch getHeadline is requested unsuccessfully, THEN State going to be an Failure`() = runTest {

        coEvery { newsApiRepository.getHeadline() } returns flow { emit(ApiError(0, "")) }

        viewModel = HomeViewModel(newsApiRepository)
        viewModel.fetchHeadline()

        viewModel.uiState.test {
            assertTrue(awaitItem() is UiState.Failure)
            coVerify(exactly = 1) { newsApiRepository.getHeadline() }
            confirmVerified(newsApiRepository)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `WHEN fetch getHeadline is requested successfully, THEN State going to be a Success`() = runTest {

        coEvery { newsApiRepository.getHeadline() } returns flow { emit(ApiSuccess(getHeadlinesMock())) }

        viewModel = HomeViewModel(newsApiRepository)
        viewModel.fetchHeadline()

        viewModel.uiState.test {
            assertTrue(awaitItem() is UiState.Success)
            coVerify(atMost = 1) { newsApiRepository.getHeadline() }
            confirmVerified(newsApiRepository)
            cancelAndConsumeRemainingEvents()
        }
    }
}