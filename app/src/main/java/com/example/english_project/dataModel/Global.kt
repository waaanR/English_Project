package com.example.english_project.dataModel

object Global {

    // mode de jeu
    var mode : String = "French"

    // nombre max de mots
    val NOMBRE_MAX_MOTS = 10000

    // initialisation des tableaux pour les flashcards
    // permet de le faire qu'une seule fois pour gagner en performances
    lateinit var freq : IntArray
    lateinit var prefix : IntArray
    fun initPrefix() {
        var taille = WordsManager.allwords.size
        freq = IntArray(taille)

        // création du tableau préfix
        var i = 0;
        for (word in WordsManager.allwords){
            freq[i] = word.multiplier
            i++
        }

        prefix = IntArray(taille)
        prefix[0] = freq[0]

        for (i in (1 until taille)){
            prefix[i] = prefix[i-1] + freq[i]
        }
    }
}