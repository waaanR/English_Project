package com.example.english_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.english_project.databinding.FragmentMenuBinding
import com.example.english_project.databinding.FragmentModifStatsBinding


class ModifStatsFragment : Fragment() {

    lateinit var binding : FragmentModifStatsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_modif_stats,container,false)
        return binding.root
    }

}