package com.example.english_project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.english_project.dataModel.Global
import com.example.english_project.dataModel.WordsManager
import com.example.english_project.dataModel.wordTrad
import com.example.english_project.databinding.FragmentFlashCardBinding


class FlashCardFragment : Fragment() {

    lateinit var binding: FragmentFlashCardBinding
    var dejaClique: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_flash_card, container, false)

        // mot actuel de la vue
        selectionMotAleatoire()

        // Listener de la flashCard
        binding.flashCardCardview.setOnClickListener {
            if (!dejaClique) {
                apparitionPremierClick()
                dejaClique = true

            } else {
                deuxiemeClick()
                // la sélection aléatoire du prochain mot se fait à la fin
                dejaClique = false
            }
        }

        // pour attribuer une contrainte dynamique
        //constraintSet.applyTo(binding.flashCardConstraintLayout)


        return binding.root
    }



    // fait just un random
    fun selectionMotAleatoire() {
        var taille: Int = WordsManager.allwords.size
        var random: Int = (Math.random() * (taille - 1 + 1) + 1).toInt()
        // random * (max - min + 1) + min
        var motActuel: wordTrad = WordsManager.allwords[random - 1]

        // mot français ou anglais en fonction du mode
        if (Global.mode.equals("French")) {
            binding.topText.text = motActuel.french
            binding.bottomText.text = motActuel.english
        } else {
            binding.topText.text = motActuel.english
            binding.bottomText.text = motActuel.french
        }

    }

    fun apparitionPremierClick() {
        // animation de fondu quand un texte arrive
        val fadeIn = AlphaAnimation(0f, 1f)
        fadeIn.duration = 1000
        fadeIn.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                binding.bottomText.visibility = View.VISIBLE
                binding.divider.visibility = View.VISIBLE
            }

            override fun onAnimationEnd(animation: Animation?) {}
            override fun onAnimationRepeat(animation: Animation?) {}

        })

        binding.divider.animate().apply {
            binding.divider.startAnimation(fadeIn)
        }

        binding.topText.animate().apply {
            duration = 1000
            translationYBy(-(binding.flashCardCardview.height.toFloat()) / 4)
        }

        binding.bottomText.animate().apply {

            binding.bottomText.startAnimation(fadeIn)
        }
    }

    fun deuxiemeClick() {
        val fadeOut = AlphaAnimation(1f, 0f)
        fadeOut.duration = 500
        fadeOut.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                binding.bottomText.visibility = View.GONE
                binding.divider.visibility = View.GONE
            }

            override fun onAnimationEnd(animation: Animation?) {
                selectionMotAleatoire()
            }
            override fun onAnimationRepeat(animation: Animation?) {}

        })

        binding.divider.animate().apply {
            binding.divider.startAnimation(fadeOut)
        }

        binding.topText.animate().apply {
            duration = 500
            translationYBy((binding.flashCardCardview.height.toFloat()) / 4)
            binding.divider.startAnimation(fadeOut)
        }

        binding.bottomText.animate().apply {

            binding.bottomText.startAnimation(fadeOut)
        }
    }

    // permet juste de faire réapparaitre le texte après avoir cliqué une 2ème fois
    private fun reapparitionTopText() {
        val changeTopText = AlphaAnimation(0f, 1f)
        changeTopText.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                binding.topText.visibility = View.VISIBLE
            }

            override fun onAnimationEnd(animation: Animation?) {
            }
            override fun onAnimationRepeat(animation: Animation?) {}
        })

        binding.flashCardCardview.setOnClickListener {
            binding.divider.startAnimation(changeTopText)
        }
    }

}

// créer une contrainte à la main dynamiquement
/*val constraintSet = ConstraintSet()
constraintSet.clone(binding.flashCardConstraintLayout)
constraintSet.connect(
    binding.topText.id,
    ConstraintSet.BOTTOM,
    binding.divider.id,
    ConstraintSet.TOP
)*/