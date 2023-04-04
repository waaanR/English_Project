package com.example.english_project

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.english_project.dataModel.WordsManager
import com.example.english_project.dataModel.wordTrad
import com.example.english_project.databinding.FragmentDatabaseBinding


class DatabaseFragment : Fragment() {

    lateinit var binding: FragmentDatabaseBinding
    lateinit var myRecycler: RecyclerView
    lateinit var wordAdapter: Adapter
    lateinit var wordArray: List<wordTrad>
    var dejaClique: Boolean = false
    //private lateinit var databaseManager : WordsManager
    var modeFilter: Int = 0 // 0 : filtrage par ordre alphabétique croissant sur anglais
    // 1 : filtrage par ordre alphabétique décroissant sur anglais
    // 2 : filtrage par ordre chronologique croissant d'ajout (du - récent au +)
    // 3 : filtrage par ordre chronologique décroissant d'ajout (du + récent au -)

    // durées d'animation
    val DUREE_APPARITION: Long = 500
    val DUREE_DISPARITION: Long = 500


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_database, container, false)

        // liste arbitraire de mots pour les tests
        var tableauDeMots: List<wordTrad> = InitArray()

        // initialisation du recycler view
        myRecycler = binding.recyclerViewDatabase
        myRecycler.layoutManager = LinearLayoutManager(context)
        // listener de chaque item cliqué + initialisation de l'adapter
        wordAdapter = Adapter(tableauDeMots) {
            val action = DatabaseFragmentDirections.actionDatabaseFragmentToModifStatsFragment(it)
            findNavController().navigate(action)
        }
        myRecycler.adapter = wordAdapter

        // bouton de retour
        binding.floattingBackButton.setOnClickListener {
            val action = DatabaseFragmentDirections.actionDatabaseFragmentToMenuFragment()
            findNavController().navigate(action)
        }

        // SearchView
        binding.editTextSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                ListFilter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                ListFilter(newText)
                return false
            }
        })

        // bouton de filtre
        binding.butFiltre.text = "Recent +" //valeur par défaut
        filtreChronologiqueDecroissant()
        // listener
        binding.butFiltre.setOnClickListener {
            Log.d("modeFilter", modeFilter.toString())
            if (modeFilter == 0) { // ordre alphabétique croissant
                filtreAlphabetiqueCroissant()
                binding.butFiltre.text = "A-Z"
                modeFilter ++
            } else if (modeFilter == 1) { // ordre alphabétique décroissant
                filtreAlphabetiqueDecroissant()
                binding.butFiltre.text = "Z-A"
                modeFilter ++
            }else if (modeFilter == 2) { // ordre croissant d'ajout
                filtreChronologiqueCroissant()
                binding.butFiltre.text = "Old +"
                modeFilter ++
            } else if (modeFilter == 3) { // ordre décroissant d'ajout
                filtreChronologiqueDecroissant()
                binding.butFiltre.text = "Recent +"
                modeFilter ++
            } else if (modeFilter == 4) { // ordre difficulté croissante
                filtreDifficulteCroissante()
                binding.butFiltre.text = "Easy"
                modeFilter ++
            } else if (modeFilter == 5) { // ordre difficulté décroissante
                filtreDifficulteDecroissante()
                binding.butFiltre.text = "Hard"
                modeFilter = 0
            }

        }

        // bouton d'ajout d'un mot
        val fragment = AddingPageFragment() // Créer une instance de votre fragment
        val fragmentManager =
            requireActivity().supportFragmentManager // Obtenir le FragmentManager
        binding.floatingAddingButton.setOnClickListener {

            binding.backgroundViewBlur.setOnClickListener {
                animationAddingPageOut()
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.setCustomAnimations(
                    R.anim.from_left,
                    R.anim.to_right
                )
                fragmentTransaction.remove(fragment).commit()
                binding.backgroundViewBlur.visibility = View.GONE
                dejaClique = false
            }
            if (!dejaClique) {
                animationAddingPageComing()
                val fragmentTransaction =
                    fragmentManager.beginTransaction() // Commencer une transaction de fragment
                        .setCustomAnimations(
                            R.anim.from_right,
                            R.anim.to_left
                        )
                fragmentTransaction.replace(
                    R.id.fragment_container_view,
                    fragment
                ) // Remplacer le contenu du FragmentContainerView par votre fragment
                fragmentTransaction.addToBackStack(null) // Ajouter la transaction à la pile de retour pour permettre à l'utilisateur de revenir en arrière
                fragmentTransaction.commit() // Terminer la transaction
                dejaClique = true
            } else {
                animationAddingPageOut()
                binding.backgroundViewBlur.visibility = View.GONE
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.setCustomAnimations(
                    R.anim.from_left,
                    R.anim.to_right
                )
                fragmentTransaction.remove(fragment).commit()
                dejaClique = false
            }

        }

        /*var mavue = binding.fragmentContainerView[0]
        var binding2 = FragmentAddingPageBinding.bind(mavue)
        binding2.butaddword.setOnClickListener {
            Log.d("salut", "wesh")
        }*/

        return binding.root
    }

    private fun filtreAlphabetiqueCroissant() {
        wordAdapter.filtreAlphabetiqueCroissant()
    }

    private fun filtreAlphabetiqueDecroissant() {
        wordAdapter.filtreAlphabetiqueDecroissant()
    }

    private fun filtreChronologiqueCroissant() {
        wordAdapter.filtreChronologiqueCroissant()
    }

    private fun filtreChronologiqueDecroissant() {
        wordAdapter.filtreChronologiqueDecroissant()
    }

    private fun filtreDifficulteCroissante() {
        wordAdapter.filtreDifficulteCroissante()
    }

    private fun filtreDifficulteDecroissante() {
        wordAdapter.filtreDifficulteDecroissante()
    }

    // fonction de filtre de la searchview
    fun ListFilter(newText: String?) {
        wordAdapter.filterList(newText)
    }

    fun InitArray(): List<wordTrad> {
        /*databaseManager = WordsManager(requireContext())
        wordArray = databaseManager.allwords
        return wordArray*/
        wordArray = WordsManager.allwords
        return wordArray
    }

    fun animationAddingPageComing() {
        val fadeIn = AlphaAnimation(0f, 1f)
        fadeIn.duration = DUREE_APPARITION
        fadeIn.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                binding.backgroundViewBlur.visibility = View.VISIBLE
            }

            override fun onAnimationEnd(animation: Animation?) {}
            override fun onAnimationRepeat(animation: Animation?) {}

        })

        binding.backgroundViewBlur.startAnimation(fadeIn)
    }


    fun animationAddingPageOut() {
        val fadeOut = AlphaAnimation(1f, 0f)
        fadeOut.duration = DUREE_DISPARITION
        fadeOut.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                binding.backgroundViewBlur.visibility = View.GONE
            }

            override fun onAnimationEnd(animation: Animation?) {}
            override fun onAnimationRepeat(animation: Animation?) {}

        })

        binding.backgroundViewBlur.startAnimation(fadeOut)
    }

}