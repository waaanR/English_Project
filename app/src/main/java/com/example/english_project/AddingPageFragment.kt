package com.example.english_project

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.english_project.dataModel.WordsManager
import com.example.english_project.dataModel.wordTrad
import com.example.english_project.databinding.FragmentAddingPageBinding
import com.example.english_project.databinding.FragmentMenuBinding


class AddingPageFragment : Fragment() {

    lateinit var binding : FragmentAddingPageBinding
    //private lateinit var databaseManager : WordsManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_adding_page,container,false)
        //databaseManager = WordsManager(requireContext())

        binding.butaddword.setOnClickListener {
            val word : wordTrad
            if(!(binding.etfrench.text.isNullOrBlank()) && !(binding.etenglish.text.isNullOrBlank())){
                word = wordTrad(binding.etfrench.text.toString(), binding.etenglish.text.toString())
                //databaseManager.insertWords(word)
                WordsManager.insertWords(word)
            }
            binding.etfrench.text.clear()
            binding.etenglish.text.clear()

        }



        return binding.root
    }


}