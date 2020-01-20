package com.marijannovak.autismhelper.data.storage

import com.marijannovak.autismhelper.data.database.dao.*
import com.marijannovak.autismhelper.data.models.domain.ContentWrapper
import kotlinx.coroutines.delay

class DataStorage (
    private val answerDao: AnswerDao,
    private val phraseCategoryDao: PhraseCategoryDao,
    private val phraseDao: PhraseDao,
    private val questionDao: QuestionDao,
    private val quizCategory: QuizCategoryDao
) {

    suspend fun saveContent(content: ContentWrapper) {
        delay(1000)//for now
    }

}