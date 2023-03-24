package com.example.english_project

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.english_project.dataModel.WordsManager
import com.example.english_project.databinding.FragmentMenuBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class MenuFragment : Fragment() {

    lateinit var binding : FragmentMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_menu,container,false)

        binding.buttonIntoSetting.setOnClickListener {
            val action=MenuFragmentDirections.actionMenuFragmentToSettingsFragment()
            findNavController().navigate(action)
        }


        binding.buttonIntoDatabase.setOnClickListener {
            val action=MenuFragmentDirections.actionMenuFragmentToDatabaseFragment()
            findNavController().navigate(action)
        }

        binding.buttonIntoFlashCards.setOnClickListener {
            if (!WordsManager.allwords.isEmpty()) {
                val action=MenuFragmentDirections.actionMenuFragmentToFlashCardFragment()
                findNavController().navigate(action)
            } else {
                // dialog box qui dit que pas de mots
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Warning")
                    .setMessage("You have no words in your list")
                    .setPositiveButton("Ok", object : DialogInterface.OnClickListener{
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                        }
                    })
                    .show()
            }

        }

        return binding.root
    }



}