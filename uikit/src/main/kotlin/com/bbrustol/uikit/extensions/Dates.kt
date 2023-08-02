package com.bbrustol.uikit.extensions

import android.icu.text.SimpleDateFormat
import androidx.core.net.ParseException
import com.bbrustol.uikit.utils.TimeFormatType
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

fun String.formatDate(pattern: String) : String {
    return try {
        val inputFormat = SimpleDateFormat(TimeFormatType.YYYYMMDD_T_HHMMSSSSSZ.pattern, Locale.getDefault())
        val outputFormat = SimpleDateFormat(pattern, Locale.getDefault())
        return outputFormat.format(inputFormat.parse(this))
    } catch (e: ParseException,) {
        "parse error"
    } catch (e: NullPointerException,) {
        "Null Pointer"
    }
}

fun isAfter(dateTime: String): Boolean {
    val strToDate = SimpleDateFormat(TimeFormatType.YYYYMMDD_T_HHMMSSSSSZ.pattern, Locale.getDefault()).parse(dateTime)
    return (Date().after(strToDate))
}

fun String.betweenDates(): String {
    val days365 = 365
    val days30 = 30
    val strDate = SimpleDateFormat(TimeFormatType.YYYYMMDD_T_HHMMSSSSSZ.pattern, Locale.getDefault()).parse(this)
    val millionSeconds = strDate.time - Calendar.getInstance().timeInMillis

    var days = TimeUnit.MILLISECONDS.toDays(millionSeconds)
    val yr = days / days365
    days %= days365
    val mn = days / days30
    days %= days30

    val hr = (TimeUnit.MILLISECONDS.toHours(millionSeconds) - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(millionSeconds)))
    val min = (TimeUnit.MILLISECONDS.toMinutes(millionSeconds) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millionSeconds)))

    return String.format("%dY %dM %dD %dH %dM", yr, mn, days, hr, min)
}
