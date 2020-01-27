package com.marijannovak.autismhelper.common.errors

import com.marijannovak.autismhelper.R

/**
 * Handle errors by response code
 */
object ErrorHandler {
    val defaultApiExceptionResolver: (Int) -> Throwable = { code ->
        when (code) {
            else -> Exception()
        }
    }

    fun resolveExceptionMessageId(error: Throwable): Int = R.string.unknown_error
}