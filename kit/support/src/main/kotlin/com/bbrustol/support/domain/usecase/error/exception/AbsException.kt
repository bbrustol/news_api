package com.bbrustol.support.domain.usecase.error.exception

import java.lang.Exception

abstract class AbsException(message: String, val url: String? = null) : Exception(message) {}
