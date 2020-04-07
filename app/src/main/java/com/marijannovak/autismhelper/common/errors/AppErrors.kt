package com.marijannovak.autismhelper.common.errors

/**
 * Seal exceptions that should be handled by the App
 */
sealed class AppError: Exception() {
    object BadRequest: AppError()
    object InvalidCredentials: AppError()
    object Unknown: AppError()
    object NonExistentUser: AppError()
}