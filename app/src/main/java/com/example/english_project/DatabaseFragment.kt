package com.example.english_project

import android.os.Bundle
import android.util.Log
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
import java.util.*


class DatabaseFragment : Fragment() {

    lateinit var binding: FragmentDatabaseBinding
    lateinit var myRecycler: RecyclerView
    lateinit var wordAdapter: Adapter
    lateinit var wordArray: List<wordTrad>
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

        binding.butReset.setOnClickListener {
            //databaseManager.reset()
            WordsManager.reset()
            tableauDeMots = InitArray()
            wordAdapter.filterList(tableauDeMots)
        }

        return binding.root
    }

    // fonction de filtre de la searchview
    fun ListFilter(newText: String?) {
        if (newText.isNullOrBlank()) wordAdapter.filterList(wordArray)
        else {
            val wordArrayFiltered = mutableListOf<wordTrad>()
            for (word in wordArray) {
                if (word.french.lowercase(Locale.ROOT)
                        .contains(newText.lowercase(Locale.ROOT))
                    || word.english.lowercase(Locale.ROOT)
                        .contains(newText.lowercase(Locale.ROOT))
                ) {
                    wordArrayFiltered.add(word)
                }
            }
            wordAdapter.filterList(wordArrayFiltered)
        }
    }

    fun InitArray(): List<wordTrad> {
        /*databaseManager = WordsManager(requireContext())
        wordArray = databaseManager.allwords
        return wordArray*/
        wordArray = WordsManager.allwords
        return wordArray
    }

}