package com.example.k2024_02_20_quiz_page.model


class AllQuestions {
    val allQuestions = arrayListOf(
        Question<Boolean>("There are 366 days in a leap year", true, Difficulty.EASY),
        Question<Boolean>("The Earth is flat", false, Difficulty.EASY),
        Question<Boolean>("Kotlin is a programming language", true, Difficulty.EASY),
        Question<Boolean>("The Great Wall of China is visible from the Moon", false, Difficulty.MEDIUM),
        Question<Boolean>("Java is an island in Indonesia", false, Difficulty.MEDIUM),
        Question<Boolean>("The capital of Canada is Toronto", false, Difficulty.MEDIUM),
        Question<Boolean>("The Pacific Ocean is the largest ocean on Earth", true, Difficulty.HARD),
        Question<Boolean>("C++ is an object-oriented programming language", true, Difficulty.HARD),
        Question<Boolean>("Mount Everest is the tallest mountain on Earth", true, Difficulty.HARD)
    )

    fun getNumberOfQuestions(): Int {
        return allQuestions.size
    }

    fun getQuestion(i: Int): Question<Boolean> {
        return allQuestions[i]
    }
}
