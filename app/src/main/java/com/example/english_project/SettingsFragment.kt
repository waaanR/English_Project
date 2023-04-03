package com.example.english_project

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
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

        // bouton de retour
        binding.floattingBackButton.setOnClickListener {
            val action = SettingsFragmentDirections.actionSettingsFragmentToMenuFragment()
            findNavController().navigate(action)
        }

        binding.materialButtonReset.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Delete?")
                .setMessage("Do you really want to delete the database?")
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

        binding.buttonAboutUs.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("About us")
                .setMessage("This application was created by 2 students of INSA Rennes:\nJules Laplace-Treyture\nErwan Le Boulaire")
                .setNeutralButton("The bests", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        MaterialAlertDialogBuilder(requireContext())
                            .setTitle("Thank you")
                            .setMessage("By having download our app, you support us a lot.\nWe thank you for that.\nWe love you <3")
                            .setNeutralButton("Me too", object : DialogInterface.OnClickListener {
                                override fun onClick(dialog: DialogInterface?, which: Int) {
                                    MaterialAlertDialogBuilder(requireContext())
                                        .setTitle("<3")
                                        .setMessage("The last one?\nGood luck!")
                                        .setNeutralButton("Cancel", object : DialogInterface.OnClickListener {
                                            override fun onClick(dialog: DialogInterface?, which: Int) {

                                            }
                                        })
                                        .show()
                                }
                            })

                            .setPositiveButton("Ok", object : DialogInterface.OnClickListener {
                                override fun onClick(dialog: DialogInterface?, which: Int) {
                                }
                            })
                            .show()
                    }
                })

                .setPositiveButton("Ok", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
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