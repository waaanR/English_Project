package com.example.english_project

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.english_project.dataModel.Global
import com.example.english_project.dataModel.WordsManager
import com.example.english_project.databinding.FragmentSettingsBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class SettingsFragment : Fragment() {
    lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false)

        binding.materialButtonReset.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Delete ?")
                .setMessage("Do you really want to delete the database ?")
                .setNeutralButton("Cancel", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                    }
                })

                .setPositiveButton("Yes", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        WordsManager.reset()
                    }
                })
                .show()
        }

        // sélection du mode
        binding.frenchSelection.setOnClickListener {
            Global.mode = "French"
        }
        binding.englishSelection.setOnClickListener {
            Global.mode = "English"
        }

        // toggle actif en fonction du mode
        if (Global.mode.equals("French")) {
            binding.frenchSelection.isChecked = true
            binding.englishSelection.isChecked = false
        } else {
            binding.frenchSelection.isChecked = false
            binding.englishSelection.isChecked = true
        }

        // bouton de Reset
        return binding.root
    }

}