package com.example.english_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.english_project.databinding.FragmentAddingPageBinding
import com.example.english_project.databinding.FragmentMenuBinding


class AddingPageFragment : Fragment() {

    lateinit var binding : FragmentAddingPageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_adding_page,container,false)
        return binding.root
    }


}