package com.marijannovak.autismhelper.data.models.domain

import com.marijannovak.autismhelper.data.models.persistence.DBQuizQuestion

data class QuizQuestion(
    val id: Int,
    val text: String,
    val categoryId: Int,
    val extraData: String?,
    val imgPath: String?,
    val answers: List<QuizAnswer>
) {
    fun toDatabase() = DBQuizQuestion (id, text, categoryId, extraData, imgPath)
}