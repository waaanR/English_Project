package com.example.english_project.dataModel

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class WordsDB(context: Context, name: String, factory: SQLiteDatabase.CursorFactory?, version: Int) :
    SQLiteOpenHelper(context, name, factory, version){

    private var db: SQLiteDatabase? = null

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        db = sqLiteDatabase
        db!!.execSQL(CREATE_BDD)
        Log.d("DATABASE", "Creation BDD")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("drop table " + TABLE_WORDS)
        onCreate(db!!)
        Log.d("DATABASE", "Upgrade BDD")
    }

    fun reset(){
        db!!.execSQL("delete from " + TABLE_WORDS)
    }

    companion object{

        val TABLE_WORDS = "table_words"

        val COL_ID = "id"
        val NUM_COL_ID = 0

        val COL_FRENCH = "french"
        val NUM_COL_FRENCH = 1

        val COL_ENGLISH = "english"
        val NUM_COL_ENGLISH = 2

        val COL_MULTIPLIER = "multiplier"
        val NUM_COL_MULTIPLIER = 3

        private val CREATE_BDD = "create table " + TABLE_WORDS +
                "(" + COL_ID + " integer primary key autoincrement, " +
                COL_FRENCH + " text not null, " +
                COL_ENGLISH + " text not null, " +
                COL_MULTIPLIER + " integer not null)"
    }

}