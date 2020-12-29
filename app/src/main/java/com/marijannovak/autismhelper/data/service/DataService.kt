package com.marijannovak.autismhelper.data.service

import com.marijannovak.autismhelper.data.models.domain.ContentWrapper
import com.marijannovak.autismhelper.data.network.API
import javax.inject.Inject

interface DataService {

    suspend fun getContent(): ContentWrapper
}

class DataServiceImpl @Inject constructor(
    private val api: API
) : DataService {

    override suspend fun getContent() = ContentWrapper(
        api.getCategories(),
        api.getQuestions(),
        api.getPhrases(),
        api.getPhraseCategories()
    )
}