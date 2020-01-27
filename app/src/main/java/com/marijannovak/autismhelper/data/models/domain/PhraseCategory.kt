package com.marijannovak.autismhelper.data.models.domain

import com.marijannovak.autismhelper.data.models.persistence.DBPhraseCategory

data class PhraseCategory(
    val id: Int,
    val name: String
) {
    fun toDatabase() = DBPhraseCategory (id, name)
}