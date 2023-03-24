package com.example.english_project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        // bouton de Reset
        binding.butReset.setOnClickListener {
            //databaseManager.reset()
            WordsManager.reset()
            tableauDeMots = InitArray()
            wordAdapter.filterList(null)
        }

        // bouton d'ajout d'un mot
        val fragment = AddingPageFragment() // Créer une instance de votre fragment
        val fragmentManager =
            requireActivity().supportFragmentManager // Obtenir le FragmentManager
        binding.floatingAddingButton.setOnClickListener {
            if (!dejaClique) {
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
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.setCustomAnimations(
                    R.anim.from_left,
                    R.anim.to_right
                )
                fragmentTransaction.remove(fragment).commit()
                dejaClique = false
            }

        }


        return binding.root
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

}