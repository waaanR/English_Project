package com.example.english_project

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.english_project.dataModel.WordsManager
import com.example.english_project.dataModel.wordTrad
import com.example.english_project.databinding.FragmentModifStatsBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class ModifStatsFragment : Fragment() {

    lateinit var binding : FragmentModifStatsBinding
    private val args : ModifStatsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_modif_stats,container,false)


        val wordManager = WordsManager.getWord(args.idWordTrad)

        binding.etstatfrench.hint = wordManager!!.french
        binding.etstatenglish.hint = wordManager!!.english

        binding.butdelete.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Do you really want to delete this translation?")
                .setMessage("Attention, you are about to delete this translation.")
                .setNeutralButton("Cancel", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                    }
                })
                .setPositiveButton("Delete", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        WordsManager.deleteWord(wordManager.id)
                        val action = ModifStatsFragmentDirections.actionModifStatsFragmentToDatabaseFragment()
                        findNavController().navigate(action)
                    }
                })
                .show()
        }

        binding.butmodify.setOnClickListener {
            val word : wordTrad
            if(!(binding.etstatfrench.text.isNullOrBlank()) && !(binding.etstatenglish.text.isNullOrBlank())){
                word = wordTrad(binding.etstatfrench.text.toString(), binding.etstatenglish.text.toString())
                if(!WordsManager.containsWordTrad(word)){
                    //databaseManager.insertWords(word)
                    WordsManager.deleteWord(args.idWordTrad)
                    WordsManager.insertWords(word)
                    binding.etstatfrench.hint = word.french
                    binding.etstatenglish.hint = word.english
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
            binding.etstatfrench.text.clear()
            binding.etstatenglish.text.clear()
        }


        return binding.root
    }

}