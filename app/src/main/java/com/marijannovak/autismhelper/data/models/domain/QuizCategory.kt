package com.marijannovak.autismhelper.data.models.domain

import com.marijannovak.autismhelper.data.models.persistence.DBQuizCategory

data class QuizCategory (
    val id: Int,
    val name: String
) {
    fun toDatabase() = DBQuizCategory (id, name)
}