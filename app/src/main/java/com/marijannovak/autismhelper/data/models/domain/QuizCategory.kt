package com.marijannovak.autismhelper.data.models.domain

data class QuizCategory (
    val id: Int,
    val name: String,
    val questions: List<QuizQuestion>
)