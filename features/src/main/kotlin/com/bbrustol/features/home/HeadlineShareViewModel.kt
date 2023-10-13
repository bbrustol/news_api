package com.bbrustol.features.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.bbrustol.features.home.model.HeadlineModel

class HeadlineShareViewModel : ViewModel() {
    var headlineModel by mutableStateOf<HeadlineModel?>(null)
        private set

    fun addHeadlineModel(model: HeadlineModel) {
        headlineModel = model
    }
}