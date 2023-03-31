package com.example.english_project.dataModel

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import java.util.*

object WordsManager {

    var words: WordsDB? = null
    var bdd: SQLiteDatabase? = null
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
                    WordsDB.COL_MULTIPLIER,
                    WordsDB.COL_DATE
                ), null, null, null, null, WordsDB.COL_ENGLISH
            )

            if (c.count > 0) {
                while (c.moveToNext()) {
                    retval.add(
                        wordTrad(
                            c.getString(WordsDB.NUM_COL_FRENCH),
                            c.getString(WordsDB.NUM_COL_ENGLISH),
                            c.getInt(WordsDB.NUM_COL_MULTIPLIER),
                            c.getLong(WordsDB.NUM_COL_DATE),
                            c.getInt(WordsDB.NUM_COL_ID)
                        )
                    )
                }
            }

            close()

            return retval
        }


    fun init(cxt: Context) {
        words = WordsDB(cxt, "words", null, 1)
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

    fun containsWordTrad(word: wordTrad): Boolean {
        allwords.forEach {
            if (it.english.lowercase(Locale.ROOT)
                    .equals(word.english.lowercase(Locale.ROOT)) && it.french.lowercase(Locale.ROOT)
                    .equals(word.french.lowercase(Locale.ROOT))
            ) {
                return true
            }
        }
        return false
    }

    fun insertWords(words: wordTrad): Long {

        openForWrite()

        val cv = ContentValues()
        cv.put(WordsDB.COL_FRENCH, words.french)
        cv.put(WordsDB.COL_ENGLISH, words.english)
        cv.put(WordsDB.COL_MULTIPLIER, words.multiplier)
        cv.put(WordsDB.COL_DATE, words.addingDate)

        val retval = bdd!!.insert(WordsDB.TABLE_WORDS, null, cv)

        close()
        Log.d("DATABASE", "Insertion BDD")


        return retval
    }

    fun reset() {

        openForWrite()

        bdd!!.delete(WordsDB.TABLE_WORDS, null, null)

        close()

        Log.d("DATABASE", "Reset BDD")
    }

    fun getWord(id: Int): wordTrad? {

        openForRead()

        var word: wordTrad? = null

        val c = bdd!!.query(
            WordsDB.TABLE_WORDS,
            arrayOf(
                WordsDB.COL_ID,
                WordsDB.COL_FRENCH,
                WordsDB.COL_ENGLISH,
                WordsDB.COL_MULTIPLIER,
                WordsDB.COL_DATE
            ),
            WordsDB.COL_ID + "=$id", null, null, null, WordsDB.COL_ID,
        )

        if (c.count > 0) {
            while (c.moveToNext()) {
                word = wordTrad(
                    c.getString(WordsDB.NUM_COL_FRENCH),
                    c.getString(WordsDB.NUM_COL_ENGLISH),
                    c.getInt(WordsDB.NUM_COL_MULTIPLIER),
                    c.getLong(WordsDB.NUM_COL_DATE),
                    c.getInt(WordsDB.NUM_COL_ID)
                )
            }
        }

        close()

        return word
    }

    fun deleteWord(id: Int) {

        openForWrite()

        bdd!!.delete(WordsDB.TABLE_WORDS, WordsDB.COL_ID + "=$id", null)

        close()

    }

    // pour actualiser un multiplier
    fun multiplierActualize(id: Int, newMultiplier: Int) {

        openForWrite()

        val cv = ContentValues()
        cv.put(WordsDB.COL_MULTIPLIER, newMultiplier)
        bdd!!.update(
            WordsDB.TABLE_WORDS,
            cv,
            WordsDB.COL_ID + "=$id",
            null
        )

        close()

    }

    fun restartDB() {
        openForWrite()
        bdd!!.execSQL("drop table " + WordsDB.TABLE_WORDS)
        Log.d("DATABASE", "supression")
        bdd!!.execSQL(WordsDB.CREATE_BDD)
        Log.d("DATABASE", "création new BDD")

    }

}
