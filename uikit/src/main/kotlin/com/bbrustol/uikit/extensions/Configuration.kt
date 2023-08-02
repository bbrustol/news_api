package com.bbrustol.uikit.extensions

import android.content.res.Configuration
import androidx.compose.ui.unit.dp

fun Configuration.getWidth(divide:Int = 1) = screenHeightDp.dp / divide
fun Configuration.getHeight(divide:Int = 1) = screenHeightDp.dp / divide