package com.bbrustol.features.home.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HeadlineModel(
    val id: String,
    val author: String,
    val content: String?,
    val description: String,
    val publishedAt: String,
    val sourceName: String,
    val title: String,
    val url: String,
    val urlToImage: String
): Parcelable