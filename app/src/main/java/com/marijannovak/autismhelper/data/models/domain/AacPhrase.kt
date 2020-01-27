package com.marijannovak.autismhelper.data.models.domain

import com.marijannovak.autismhelper.data.models.persistence.DBAacPhrase

data class AacPhrase(
    val id: Int,
    val name: String,
    val text: String,
    val iconPath: String,
    val categoryId: Int
) {
    fun toDatabase() = DBAacPhrase (id, name, text, iconPath, categoryId)
}