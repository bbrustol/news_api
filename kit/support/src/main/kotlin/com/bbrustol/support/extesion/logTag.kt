package com.bbrustol.support.extesion

import com.bbrustol.support.utils.NoCoverage

@NoCoverage
val Any.logTag: String
    get() = this::class.java.simpleName