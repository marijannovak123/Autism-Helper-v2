package com.marijannovak.autismhelper.data.models.domain

data class QuizAnswer(
    val id: Int,
    val text: String,
    val isCorrect: Boolean,
    val questionId: Int
)