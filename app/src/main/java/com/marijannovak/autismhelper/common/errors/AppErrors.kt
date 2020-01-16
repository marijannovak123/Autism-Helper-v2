package com.marijannovak.autismhelper.common.errors

sealed class AppError: Exception() {
    object BadRequest: AppError()
    object InvalidCredentials: AppError()
    object Unknown: AppError()
    object NonExistentUser: AppError()
}