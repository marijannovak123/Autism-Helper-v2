package com.marijannovak.autismhelper.data.storage

import com.marijannovak.autismhelper.constants.ApiConstants
import com.marijannovak.autismhelper.data.Prefs
import com.marijannovak.autismhelper.data.database.dao.*
import com.marijannovak.autismhelper.data.models.domain.ContentWrapper
import javax.inject.Inject

interface DataStorage {
    val shouldSync: Boolean

    suspend fun saveContent(content: ContentWrapper)
}

class DataStorageImpl @Inject constructor(
    private val answerDao: AnswerDao,
    private val phraseCategoryDao: PhraseCategoryDao,
    private val phraseDao: PhraseDao,
    private val questionDao: QuestionDao,
    private val quizCategoryDao: QuizCategoryDao,
    private val prefs: Prefs
) : DataStorage {

    override val shouldSync: Boolean
        get() = System.currentTimeMillis() - prefs.lastSync > ApiConstants.SYNC_PERIOD

    override suspend fun saveContent(content: ContentWrapper) {
        content.run {
            val answers = questions.flatMap { it.answers }
            answerDao.insertMultiple(answers.map { it.toDatabase() })
            quizCategoryDao.insertMultiple(categories.map { it.toDatabase() })
            questionDao.insertMultiple(questions.map { it.toDatabase() })
            phraseCategoryDao.insertMultiple(phraseCategories.map { it.toDatabase() })
            phraseDao.insertMultiple(phrases.map { it.toDatabase() })
        }
        prefs.lastSync = System.currentTimeMillis()
    }
}
