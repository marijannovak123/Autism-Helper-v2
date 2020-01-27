package com.marijannovak.autismhelper.data.service

import com.marijannovak.autismhelper.data.models.domain.ContentWrapper
import com.marijannovak.autismhelper.data.network.API

class DataService(
    private val api: API
) {
    suspend fun getContent() = ContentWrapper(
        api.getCategories(),
        api.getQuestions(),
        api.getPhrases(),
        api.getPhraseCategories()
    )

}