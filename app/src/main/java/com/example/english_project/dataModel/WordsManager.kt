package com.example.english_project.dataModel

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.UserDictionary.Words
import android.util.Log

class WordsManager (val cxt: Context) {

    private var words: WordsDB? = null
    private var bdd: SQLiteDatabase? = null
    val allwords: ArrayList<wordTrad>
        get() {
            val retval = ArrayList<wordTrad>()

            openForRead()

            val c = bdd!!.query(
                WordsDB.TABLE_WORDS,
                arrayOf(
                    WordsDB.COL_ID,
                    WordsDB.COL_FRENCH,
                    WordsDB.COL_ENGLISH,
                    WordsDB.COL_MULTIPLIER
                ), null, null, null, null, WordsDB.COL_ENGLISH
            )

            if(c.count > 0){
                while(c.moveToNext()){
                    retval.add(
                        wordTrad(
                            c.getString(WordsDB.NUM_COL_FRENCH),
                            c.getString(WordsDB.NUM_COL_ENGLISH),
                            c.getInt(WordsDB.NUM_COL_ID),
                            c.getInt(WordsDB.NUM_COL_MULTIPLIER)
                        )
                    )
                }
            }

            close()

            return retval
        }

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

    fun insertWords(words: wordTrad): Long {

        openForWrite()

        val cv = ContentValues()
        cv.put(WordsDB.COL_FRENCH, words.french)
        cv.put(WordsDB.COL_ENGLISH, words.english)
        cv.put(WordsDB.COL_MULTIPLIER, words.multiplier)

        val retval = bdd!!.insert(WordsDB.TABLE_WORDS,null,cv)

        close()
        Log.d("DATABASE", "Insertion BDD")


        return retval
    }

    fun reset(){

        openForWrite()

        bdd!!.delete(WordsDB.TABLE_WORDS,null,null)

        close()

        Log.d("DATABASE", "Reset BDD")
    }

    companion object{
        var NOM_BDD = "words"
    }


}
