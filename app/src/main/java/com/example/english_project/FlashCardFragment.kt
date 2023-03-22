package com.example.english_project

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.isVisible
import androidx.core.view.size
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.english_project.databinding.FragmentFlashCardBinding


class FlashCardFragment : Fragment() {

    lateinit var binding: FragmentFlashCardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_flash_card, container, false)

        // animate text
        val constraintSet = ConstraintSet()
        constraintSet.clone(binding.flashCardConstraintLayout)
        constraintSet.connect(
            binding.topText.id,
            ConstraintSet.BOTTOM,
            binding.divider.id,
            ConstraintSet.TOP
        )

        binding.divider.animate().translationY(2000f)
        val location = IntArray(2)
        binding.topText.getLocationOnScreen(location)
        Log.d("taille de la flash card", location[1].toString())


        binding.flashCardCardview.setOnClickListener {
            binding.divider.animate().apply {
                duration = 1000
                translationYBy(-2000f)
            }

            binding.topText.animate().apply {
                duration = 1000
                translationYBy(500f)
            }
            constraintSet.applyTo(binding.flashCardConstraintLayout)
            binding.divider.visibility = View.VISIBLE
            binding.bottomText.visibility = View.VISIBLE
        }
        return binding.root
    }

}