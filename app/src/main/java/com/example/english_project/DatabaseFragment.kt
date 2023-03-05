package com.example.english_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.english_project.databinding.FragmentDatabaseBinding
import com.example.english_project.databinding.FragmentMenuBinding


class DatabaseFragment : Fragment() {

    lateinit var binding : FragmentDatabaseBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_database,container,false)

        binding.buttonIntoModifStats.setOnClickListener {
            val action=DatabaseFragmentDirections.actionDatabaseFragmentToModifStatsFragment()
            findNavController().navigate(action)
        }

        return binding.root
    }

}