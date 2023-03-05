package com.example.english_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.english_project.databinding.FragmentMenuBinding


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

        binding.buttonIntoAddingPage.setOnClickListener {
            val action=MenuFragmentDirections.actionMenuFragmentToAddingPageFragment()
            findNavController().navigate(action)
        }

        binding.buttonIntoDatabase.setOnClickListener {
            val action=MenuFragmentDirections.actionMenuFragmentToDatabaseFragment()
            findNavController().navigate(action)
        }

        binding.buttonIntoFlashCards.setOnClickListener {
            val action=MenuFragmentDirections.actionMenuFragmentToFlashCardFragment()
            findNavController().navigate(action)
        }

        return binding.root
    }



}