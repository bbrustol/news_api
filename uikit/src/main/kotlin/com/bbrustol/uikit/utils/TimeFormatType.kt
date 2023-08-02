package com.bbrustol.uikit.utils

enum class TimeFormatType(val pattern: String) {
    DDMMYYYY("dd-MM-yyyy"),
    HHMMSS("HH:mm:ss"),
    YYYYMMDD_T_HHMMSSSSSZ("yyyy-MM-dd'T'HH:mm:ss.SSSZ'"),
    YYYY("yyyy"),
    DDMMMYYYY_HHMM("dd-MMM-yyyy HH:mm")
}