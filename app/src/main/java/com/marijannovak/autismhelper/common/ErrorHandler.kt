package com.marijannovak.autismhelper.common

import com.marijannovak.autismhelper.R


object ErrorHandler {
    val defaultApiExceptionResolver: (Int) -> Throwable = { code ->
        when (code) {
            else -> Exception()
        }
    }

    fun resolveExceptionMessageId(error: Throwable): Int = R.string.unknown_error
}