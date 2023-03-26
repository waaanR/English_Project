package com.example.english_project

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.english_project.dataModel.WordsManager
import com.example.english_project.dataModel.wordTrad
import com.example.english_project.databinding.FragmentAddingPageBinding
import com.example.english_project.databinding.FragmentMenuBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder


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

        // récupération de la fonction filterList
        val recyclerView = requireActivity().findViewById<RecyclerView>(R.id.recycler_view_database)
        val adapter = recyclerView.adapter as Adapter
        // bouton add
        binding.butaddword.setOnClickListener {
            val word : wordTrad
            if(!(binding.etfrench.text.isNullOrBlank()) && !(binding.etenglish.text.isNullOrBlank())){
                word = wordTrad(binding.etfrench.text.toString(), binding.etenglish.text.toString())
                if(!WordsManager.containsWordTrad(word)){
                    //databaseManager.insertWords(word)
                    WordsManager.insertWords(word)
                } else {
                    MaterialAlertDialogBuilder(requireContext())
                        .setTitle("This translation already exists.")
                        .setMessage("The translation you want to add already exists.")
                        .setNeutralButton("OK", object : DialogInterface.OnClickListener {
                            override fun onClick(dialog: DialogInterface?, which: Int) {
                            }
                        })
                        .show()
                }
            }
            binding.etfrench.text.clear()
            binding.etenglish.text.clear()
            adapter.filterList("")
        }



        return binding.root
    }


}