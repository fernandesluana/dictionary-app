package com.luanafernandes.dictionaryapp.feature_dictionary.domain.model

data class Meaning(
    val definitions: List<Definition>,
    val partOfSpeech: String
)
