package com.marijannovak.autismhelper.di.modules

import android.content.Context
import androidx.room.Room
import com.marijannovak.autismhelper.constants.DBConstants.DB_NAME
import com.marijannovak.autismhelper.data.database.AutismHelperDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AutismHelperDB {
        return Room.databaseBuilder(
            context,
            AutismHelperDB::class.java,
            DB_NAME
        ).build()
    }

    @Provides
    fun provideUserDao(db: AutismHelperDB) = db.userDao

    @Provides
    fun provideAnswerDao(db: AutismHelperDB) = db.answerDao

    @Provides
    fun provideChildDao(db: AutismHelperDB) = db.childDao

    @Provides
    fun provideChildScoreDao(db: AutismHelperDB) = db.childScoreDao

    @Provides
    fun providePhraseDao(db: AutismHelperDB) = db.phraseDao

    @Provides
    fun providePhraseCategoryDao(db: AutismHelperDB) = db.phraseCategoryDao

    @Provides
    fun provideSavedSentenceDao(db: AutismHelperDB) = db.savedSentenceDao

    @Provides
    fun provideQuestionDao(db: AutismHelperDB) = db.questionDao

    @Provides
    fun provideQuizCategoryDao(db: AutismHelperDB) = db.quizCategoryDao
}
