package com.marijannovak.autismhelper.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.marijannovak.autismhelper.data.database.dao.*
import com.marijannovak.autismhelper.data.models.persistence.*

@Database(
    entities = [
        DBUser::class, DBAacPhrase::class, DBChild::class, DBChildScore::class,
        DBPhraseCategory::class, DBQuizAnswer::class, DBQuizCategory::class,
        DBQuizQuestion::class, DBSavedSentence::class], version = 1,
    exportSchema = false
)
abstract class AutismHelperDB: RoomDatabase() {
    abstract val userDao: UserDao
    abstract val phraseDao: PhraseDao
    abstract val childDao: ChildDao
    abstract val childScoreDao: ChildScoreDao
    abstract val phraseCategoryDao: PhraseCategoryDao
    abstract val answerDao: AnswerDao
    abstract val quizCategoryDao: QuizCategoryDao
    abstract val questionDao: QuestionDao
    abstract val savedSentenceDao: SavedSentenceDao
}