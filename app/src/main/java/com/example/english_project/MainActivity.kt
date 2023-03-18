package com.example.english_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import com.example.english_project.dataModel.WordsManager
import com.example.english_project.dataModel.wordTrad
import com.example.english_project.databinding.FragmentDatabaseBinding

class MainActivity : AppCompatActivity() {

    private lateinit var databaseManager : WordsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        databaseManager = WordsManager(this)

        val wordTrad1 = wordTrad(1,"Bonjour", "Hello")
        databaseManager.insertWords(wordTrad1)

        Log.d("DATABASE", "BDD =" + databaseManager.allwords.toString())


        databaseManager.close()
    }



}