package com.auf.cea.navdrawerlesson.helpers

import kotlin.random.Random

class QuoteRandomizerHelper {

    companion object{
        private val quotes = mapOf(
            Pair(1,"What we have once enjoyed we can never lose. All that we love deeply becomes a part of us. - Hellen Keller"),
            Pair(2,"Of all forms of caution, caution in love is perhaps the most fatal to true happiness. - Bertrand Russell"),
            Pair(3,"Loved you yesterday, love you still, always have, always will. - Elaine Davis"),
            Pair(4,"When you have a dream, you've got to grab it and never let go. - Carol Burnett"),
            Pair(5,"Nothing is impossible. The word itself says 'I'm possible!' - Audry Hepburn"),
            Pair(6,"There is nothing impossible to they who will try. - Alexander the Great"),
            Pair(7,"We cannot solve problems with the kind of thinking we employed when we came up with them. - Albert Einstein"),
            Pair(8,"Learn as if you will live forever, live like you will die tomorrow. - Mahatma Gandhi"),
            Pair(9,"When you give joy to other people, you get more joy in return. You should give a good thought to happiness that you can give out. - Eleanor Roosevelt")
        )

        fun getRandomQuote(): String? {
            val random = Random.nextInt(1, quotes.size + 1)
            return quotes[random]
        }
    }
}