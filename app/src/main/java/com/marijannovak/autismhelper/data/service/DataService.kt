package com.marijannovak.autismhelper.data.service

import com.marijannovak.autismhelper.data.models.domain.ContentWrapper
import com.marijannovak.autismhelper.data.network.API
import com.marijannovak.autismhelper.data.network.apiRequest

class DataService (
    private val api: API
) {
    suspend fun getContent(): ContentWrapper {
        return ContentWrapper(
            apiRequest { api.getCategories() },
            apiRequest { api.getQuestions() },
            apiRequest { api.getPhrases() },
            apiRequest { api.getPhraseCategories() }
        )
    }
}