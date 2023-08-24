package com.bbrustol.features.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bbrustol.domain.model.HeadlineErrorModel
import com.bbrustol.domain.model.HeadlineModel
import com.bbrustol.domain.usecase.HeadlineUseCase
import com.bbrustol.features.ui.home.UiState.*
import com.bbrustol.support.domain.usecase.SafeFlowUseCaseDelegate
import com.bbrustol.support.domain.usecase.error.GlobalErrorType
import com.bbrustol.support.domain.usecase.error.GlobalErrorType.GENERIC_ERROR
import com.bbrustol.support.extesion.onFailure
import com.bbrustol.support.extesion.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val headlineUseCase: HeadlineUseCase,
    safeFlowUseCaseDelegate: SafeFlowUseCaseDelegate
) : ViewModel(), SafeFlowUseCaseDelegate by safeFlowUseCaseDelegate {

    private val _uiState = MutableStateFlow<UiState>(Idle)
    var uiState: StateFlow<UiState> = _uiState

    init {
        fetchHeadline()
    }

    fun fetchHeadline() = headlineUseCase
        .safePrepare(
            Unit,
            onGenericError = { _uiState.value = GlobalError() },
            onNetworkUnavailable = { _uiState.value = NetWorkUnavailable }
        )
        .onStart { _uiState.value = Loading }
        .onEach {
            it.onSuccess { model -> Success(model) }
            it.onFailure { error -> Failure(error) }
        }
        .onCompletion { _uiState.value = Idle }
        .launchIn(viewModelScope)
}

sealed class UiState {
    object Idle : UiState()
    object Loading : UiState()
    data class Success(val headlineModel: List<HeadlineModel>) : UiState()
    data class Failure(val headlineErrorModel: HeadlineErrorModel) : UiState()
    data class GlobalError(val errorType: GlobalErrorType = GENERIC_ERROR) : UiState()
    object NetWorkUnavailable : UiState()
}
