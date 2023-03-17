package com.example.english_project.dataModel

import android.content.Context
import android.database.sqlite.SQLiteDatabase

class WordsManager (val cxt: Context) {

    private var words: WordsDB? = null
    private var bdd: SQLiteDatabase? = null
    /*val allwords: ArrayList<WordsClass>
        get() {





        }*/

    init {
        words = WordsDB(cxt, NOM_BDD, null, 1)
    }

    fun openForWrite() {
        bdd = words!!.writableDatabase
    }

    fun openForRead() {
        bdd = words!!.readableDatabase
    }

    fun close() {
        words!!.close()
    }

/*    fun insertWords(words: WordsClass): Long {




    }*/

    companion object{
        var NOM_BDD = "words"
    }


}
