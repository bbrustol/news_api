package com.bbrustol.support.domain.usecase.error.exception

class ServiceException(message: String, url: String? = null) : AbsException(message, url)
