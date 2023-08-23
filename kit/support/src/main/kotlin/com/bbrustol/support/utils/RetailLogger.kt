package com.bbrustol.support.utils

object RetailLogger {
    @PublishedApi internal var delegate: Log? = null

    @JvmStatic
    fun v(tag: String, message: String?) {
        delegate?.v(tag, message)
    }

    @JvmStatic
    fun v(tag: String, `object`: Any?) {
        delegate?.v(tag, `object`)
    }

    @JvmStatic
    inline fun v(tag: String, block: () -> String) {
        delegate?.v(tag, block())
    }

    @JvmStatic
    fun i(tag: String, message: String?) {
        delegate?.i(tag, message)
    }

    @JvmStatic
    fun i(tag: String, `object`: Any?) {
        delegate?.i(tag, `object`)
    }

    @JvmStatic
    inline fun i(tag: String, block: () -> String) {
        delegate?.i(tag, block())
    }

    @JvmStatic
    fun d(tag: String, message: String?) {
        delegate?.d(tag, message)
    }

    @JvmStatic
    fun d(tag: String, `object`: Any?) {
        delegate?.d(tag, `object`)
    }

    @JvmStatic
    inline fun d(tag: String, block: () -> String) {
        delegate?.d(tag, block())
    }

    @JvmStatic
    fun e(tag: String, message: String?) {
        delegate?.e(tag, message)
    }

    @JvmStatic
    fun e(tag: String, `object`: Any?) {
        delegate?.e(tag, `object`)
    }

    @JvmStatic
    inline fun e(tag: String, block: () -> String) {
        delegate?.e(tag, block())
    }

    @JvmStatic
    fun e(tag: String, message: String?, throwable: Throwable) {
        delegate?.e(tag, message, throwable)
    }

    @JvmStatic
    fun e(tag: String, `object`: Any?, throwable: Throwable) {
        delegate?.e(tag, `object`, throwable)
    }

    @JvmStatic
    inline fun e(tag: String, throwable: Throwable, block: () -> String) {
        delegate?.e(tag, block(), throwable)
    }

    @JvmStatic
    fun setLog(log: Log) {
        delegate = log
    }

    interface Log {
        fun v(tag: String, message: String?)
        fun v(tag: String, `object`: Any?)
        fun i(tag: String, message: String?)
        fun i(tag: String, `object`: Any?)
        fun d(tag: String, message: String?)
        fun d(tag: String, `object`: Any?)
        fun e(tag: String, message: String?)
        fun e(tag: String, `object`: Any?)
        fun e(tag: String, message: String?, throwable: Throwable)
        fun e(tag: String, `object`: Any?, throwable: Throwable)
    }
}
