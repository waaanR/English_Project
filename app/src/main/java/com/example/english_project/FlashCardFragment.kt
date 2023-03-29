package com.example.english_project

import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import androidx.core.os.HandlerCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.english_project.dataModel.Global
import com.example.english_project.dataModel.Global.prefix
import com.example.english_project.dataModel.WordsManager
import com.example.english_project.dataModel.wordTrad
import com.example.english_project.databinding.FragmentFlashCardBinding
import java.util.concurrent.TimeUnit


class FlashCardFragment : Fragment() {

    lateinit var binding: FragmentFlashCardBinding
    var dejaClique: Boolean = false
    var vient_d_apparaitre: Int = -1 // rang du mot qui vient d'apparaitre pour ne pas l'avoir $
    // deux fois de suite

    // durées d'animation
    val DUREE_APPARITION: Long = 1000
    val DUREE_DISPARITION: Long = 500
    val DUREE_ANIMATION_FLASHCARD: Long = 200

    // valeurs animations
    val TRANSLATION_ANIMAION_FLASHCARD: Float = 400f
    val ROTATION_ANIMAION_FLASHCARD: Float = 20f

    // valeurs d'ajout et de soustraction multiplier des mots
    val AJOUT: Int = 3
    val SOUSTRACTION: Int = 2
    val VAL_MAX_AJOUT: Int = 200
    val VAL_MAX_SOUSTRACTION: Int = 1

    // info générales du mots actuel
    var rang: Int = -1
    var multiplierMotActuel: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_flash_card, container, false)


        // bouton de retour
        binding.floattingBackButton.setOnClickListener {
            val action = FlashCardFragmentDirections.actionFlashCardFragmentToMenuFragment()
            findNavController().navigate(action)
        }
        // mot actuel de la vue
        selectionMotAleatoire()

        // Listener de la flashCard
        // handler pour ne pas pouvoir spammer :
        val handler = HandlerCompat.createAsync(Looper.getMainLooper())
        binding.flashCardCardview.setOnClickListener {

            Log.d("random", rang.toString() + " multiplier : " + multiplierMotActuel.toString())
            for (word in WordsManager.allwords) {
                Log.d("Bonjiur", word.french + " " + word.multiplier)
            }


            if (!dejaClique) { // premier click

                // permet de rendre incliquable la flashcard pendant X ms
                binding.flashCardCardview.isClickable = false
                handler.postDelayed({
                    binding.flashCardCardview.isClickable = true
                }, TimeUnit.MILLISECONDS.toMillis(DUREE_APPARITION))

                apparitionPremierClick()

                dejaClique = true

            } else { // deuxième click

                // permet de rendre incliquable la flashcard pendant X ms
                binding.flashCardCardview.isClickable = false
                handler.postDelayed({
                    binding.flashCardCardview.isClickable = true
                }, TimeUnit.MILLISECONDS.toMillis(DUREE_DISPARITION))

                deuxiemeClick()

                // la sélection aléatoire du prochain mot se fait à la fin
                dejaClique = false

            }
        }

        // modifier l'attribut multiplier si facile ou difficile
        binding.noProbaButton.setOnClickListener {
            // permet de rendre incliquable le bouton pendant X ms
            binding.flashCardCardview.isClickable = false
            handler.postDelayed({
                binding.flashCardCardview.isClickable = true
            }, TimeUnit.MILLISECONDS.toMillis(DUREE_DISPARITION))

            // permet de rendre incliquable le bouton pendant X ms
            binding.noProbaButton.isClickable = false
            handler.postDelayed({
                binding.noProbaButton.isClickable = true
            }, TimeUnit.MILLISECONDS.toMillis(DUREE_DISPARITION))

            // modification du multiplier dans la base de données
            if (multiplierMotActuel + AJOUT < VAL_MAX_AJOUT) {
                WordsManager.multiplierActualize(rang, multiplierMotActuel + AJOUT)
            } else {
                WordsManager.multiplierActualize(rang, VAL_MAX_AJOUT)
            }

            // couleur flashcard


            //animation flashcard
            flashcardAnimationGauche()
            handler.postDelayed({
                flashcardAnimationDroite()
            }, TimeUnit.MILLISECONDS.toMillis(250))

            // comme si on cliquait une deuxième fois sur la flashcard
            handler.postDelayed({
                deuxiemeClick()
            }, TimeUnit.MILLISECONDS.toMillis(DUREE_ANIMATION_FLASHCARD * 2))

            dejaClique = false
        }

        binding.yesProbaButton.setOnClickListener {
            // permet de rendre incliquable le bouton pendant X ms
            binding.flashCardCardview.isClickable = false
            handler.postDelayed({
                binding.flashCardCardview.isClickable = true
            }, TimeUnit.MILLISECONDS.toMillis(DUREE_DISPARITION))

            // permet de rendre incliquable le bouton pendant X ms
            binding.yesProbaButton.isClickable = false
            handler.postDelayed({
                binding.yesProbaButton.isClickable = true
            }, TimeUnit.MILLISECONDS.toMillis(DUREE_DISPARITION))

            // modification du multiplier dans la base de données
            if (multiplierMotActuel - SOUSTRACTION > VAL_MAX_SOUSTRACTION) {
                WordsManager.multiplierActualize(rang, multiplierMotActuel - SOUSTRACTION)
            } else {
                WordsManager.multiplierActualize(rang, VAL_MAX_SOUSTRACTION)
            }

            //animation flashcard
            flashcardAnimationDroite()
            handler.postDelayed({
                flashcardAnimationGauche()
            }, TimeUnit.MILLISECONDS.toMillis(250))

            // comme si on cliquait une deuxième fois sur la flashcard
            handler.postDelayed({
                deuxiemeClick()
            }, TimeUnit.MILLISECONDS.toMillis(DUREE_ANIMATION_FLASHCARD * 2))
            dejaClique = false
        }


        // pour attribuer une contrainte dynamique
        //constraintSet.applyTo(binding.flashCardConstraintLayout)


        return binding.root
    }


    // fait just un random
    fun selectionMotAleatoire() {

        //récupération de l'index aléatoire
        var random: Int = myRand()
        while (random == vient_d_apparaitre) { //pour ne pas avoir deux fois le meme random de suite
            random = myRand()
        }
        vient_d_apparaitre = random

        // random * (max - min + 1) + min
        var motActuel: wordTrad = WordsManager.allwords[random]

        // actualisation des variables globales
        rang = motActuel.id
        multiplierMotActuel = motActuel.multiplier

        // mot français ou anglais en fonction du mode
        if (Global.mode.equals("French")) {
            binding.topText.text = motActuel.french
            binding.bottomText.text = motActuel.english
        } else {
            binding.topText.text = motActuel.english
            binding.bottomText.text = motActuel.french
        }

    }

    // random amélioré avec probabilités
    // findCeil permet de connaitre la case qu'il faudra prendre dans le allwords
    fun findCeil(r: Int, l: Int, h: Int): Int {
        var mid: Int
        var l1 = l
        var h1 = h
        while (l1 < h1) {
            mid = ((l1 + h1) / 2)
            if (r > prefix[mid]) l1 = mid + 1
            else h1 = mid
        }
        if (prefix[l1] >= r) return l1
        else return -1
    }

    // myRand est le générateur du nombre random en fonction du tableau des fréquences d'apparition
    fun myRand(): Int {

        var taille: Int = WordsManager.allwords.size

        var random: Int = (((Math.random() * Global.NOMBRE_MAX_MOTS) % prefix[taille - 1])).toInt()

        var indexc: Int = findCeil(random, 0, taille - 1)

        return indexc
    }


    fun apparitionPremierClick() {
        // animation de fondu quand un texte arrive
        val fadeIn = AlphaAnimation(0f, 1f)
        fadeIn.duration = DUREE_APPARITION
        fadeIn.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                binding.bottomText.visibility = View.VISIBLE
                binding.divider.visibility = View.VISIBLE
                binding.noProbaButton.visibility = View.VISIBLE
                binding.yesProbaButton.visibility = View.VISIBLE
            }

            override fun onAnimationEnd(animation: Animation?) {}
            override fun onAnimationRepeat(animation: Animation?) {}

        })

        val fadeInSlow = AlphaAnimation(0f, 1f)
        fadeInSlow.duration = DUREE_APPARITION * 2
        fadeInSlow.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                binding.noProbaButton.visibility = View.VISIBLE
                binding.yesProbaButton.visibility = View.VISIBLE
            }

            override fun onAnimationEnd(animation: Animation?) {
            }

            override fun onAnimationRepeat(animation: Animation?) {}

        })

        binding.divider.animate().apply {
            binding.divider.startAnimation(fadeIn)
        }

        binding.topText.animate().apply {
            duration = DUREE_APPARITION
            translationYBy(-(binding.flashCardCardview.height.toFloat()) / 4)
        }

        binding.bottomText.animate().apply {
            binding.bottomText.startAnimation(fadeIn)
        }

        binding.noProbaButton.animate().apply {
            duration = DUREE_APPARITION
            translationYBy(((binding.flashCardCardview.height / 2) - (binding.flashCardCardview.height / 16)).toFloat())
            binding.noProbaButton.startAnimation(fadeInSlow)
        }
        binding.yesProbaButton.animate().apply {
            duration = DUREE_APPARITION
            translationYBy(((binding.flashCardCardview.height / 2) - (binding.flashCardCardview.height / 16)).toFloat())
            binding.yesProbaButton.startAnimation(fadeInSlow)
        }
    }

    fun deuxiemeClick() {
        val fadeOut = AlphaAnimation(1f, 0f)
        fadeOut.duration = DUREE_DISPARITION
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

        val fadeOutSpeed = AlphaAnimation(1f, 0f)
        fadeOutSpeed.duration = DUREE_DISPARITION / 2
        fadeOutSpeed.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                binding.noProbaButton.visibility = View.GONE
                binding.yesProbaButton.visibility = View.GONE
            }

            override fun onAnimationEnd(animation: Animation?) {
            }

            override fun onAnimationRepeat(animation: Animation?) {}

        })

        binding.divider.animate().apply {
            binding.divider.startAnimation(fadeOut)
        }

        binding.topText.animate().apply {
            duration = DUREE_DISPARITION
            translationYBy((binding.flashCardCardview.height.toFloat()) / 4)
            binding.divider.startAnimation(fadeOut)
        }

        binding.bottomText.animate().apply {
            binding.bottomText.startAnimation(fadeOut)
        }
        binding.noProbaButton.animate().apply {
            duration = DUREE_DISPARITION
            binding.noProbaButton.startAnimation(fadeOutSpeed)
            translationYBy(-((binding.flashCardCardview.height / 2) - (binding.flashCardCardview.height / 16)).toFloat())

        }
        binding.yesProbaButton.animate().apply {
            duration = DUREE_DISPARITION
            binding.yesProbaButton.startAnimation(fadeOutSpeed)
            translationYBy(-((binding.flashCardCardview.height / 2) - (binding.flashCardCardview.height / 16)).toFloat())

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

    fun flashcardAnimationDroite() {
        binding.flashCardCardview.animate().apply {
            duration = DUREE_ANIMATION_FLASHCARD
            translationXBy(TRANSLATION_ANIMAION_FLASHCARD)
            rotationBy(ROTATION_ANIMAION_FLASHCARD)
        }
    }

    fun flashcardAnimationGauche() {
        binding.flashCardCardview.animate().apply {
            duration = DUREE_ANIMATION_FLASHCARD
            translationXBy(-TRANSLATION_ANIMAION_FLASHCARD)
            rotationBy(-ROTATION_ANIMAION_FLASHCARD)
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