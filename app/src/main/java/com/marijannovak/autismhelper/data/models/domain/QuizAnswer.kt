package com.marijannovak.autismhelper.data.models.domain

import com.marijannovak.autismhelper.data.models.persistence.DBQuizAnswer

data class QuizAnswer(
    val id: Int,
    val text: String,
    val isCorrect: Boolean,
    val questionId: Int
) {
    fun toDatabase() = DBQuizAnswer(id, text, isCorrect, questionId)
}