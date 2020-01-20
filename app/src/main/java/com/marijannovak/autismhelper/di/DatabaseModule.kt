package com.marijannovak.autismhelper.di

import androidx.room.Room
import com.marijannovak.autismhelper.constants.DBConstants.DB_NAME
import com.marijannovak.autismhelper.data.database.AutismHelperDB
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dbModule = module {
    single {
        Room.databaseBuilder(androidContext(), AutismHelperDB::class.java,
            DB_NAME
        ).build()
    }

    single {
        get<AutismHelperDB>().userDao
    }

    single {
        get<AutismHelperDB>().answerDao
    }

    single {
        get<AutismHelperDB>().childDao
    }

    single {
        get<AutismHelperDB>().childScoreDao
    }

    single {
        get<AutismHelperDB>().phraseCategoryDao
    }

    single {
        get<AutismHelperDB>().phraseDao
    }

    single {
        get<AutismHelperDB>().savedSentenceDao
    }

    single {
        get<AutismHelperDB>().quizCategoryDao
    }

    single {
        get<AutismHelperDB>().questionDao
    }

}