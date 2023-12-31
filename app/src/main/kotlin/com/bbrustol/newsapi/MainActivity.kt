package com.bbrustol.newsapi

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.fragment.app.FragmentActivity
import com.bbrustol.newsapi.compose.SetupView
import com.bbrustol.uikit.utils.Biometric
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { SetupView(Biometric.check(this)) }
    }
}