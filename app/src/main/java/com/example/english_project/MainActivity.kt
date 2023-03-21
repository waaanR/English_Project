package com.example.english_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.english_project.dataModel.WordsManager

class MainActivity : AppCompatActivity() {

    //private lateinit var databaseManager : WordsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        WordsManager.init(this)

    }



}