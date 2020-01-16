package com.marijannovak.autismhelper.data.models.domain

data class QuizQuestion(
    val id: Int,
    val text: String,
    val categoryId: Int,
    val extraData: String?,
    val imgPath: String?,
    val answers: List<QuizAnswer>
)