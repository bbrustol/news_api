package com.bbrustol.uikit.utils

enum class TimeFormat(val pattern: String) {
    DDMMYYYY("dd-MM-yyyy"),
    HHMMSS("HH:mm:ss"),
    YYYYMMDD_T_HHMMSSSSSZ("yyyy-MM-dd'T'HH:mm:ss.SSSZ'"),
    YYYY("yyyy")
}