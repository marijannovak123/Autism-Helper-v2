package com.marijannovak.autismhelper.data.models.domain

data class ContentWrapper(
    val categories: List<QuizCategory>,
    val questions: List<QuizQuestion>,
    val phrases: List<AacPhrase>,
    val phraseCategories: List<PhraseCategory>
)