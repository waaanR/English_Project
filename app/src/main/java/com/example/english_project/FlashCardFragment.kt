package com.example.english_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.english_project.databinding.FragmentFlashCardBinding
import com.example.english_project.databinding.FragmentMenuBinding


class FlashCardFragment : Fragment() {

    lateinit var binding : FragmentFlashCardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_flash_card,container,false)
        return binding.root
    }

}