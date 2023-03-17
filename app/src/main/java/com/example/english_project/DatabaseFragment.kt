package com.example.english_project

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.english_project.dataModel.wordTrad
import com.example.english_project.databinding.FragmentDatabaseBinding
import java.util.*


class DatabaseFragment : Fragment() {

    lateinit var binding: FragmentDatabaseBinding
    lateinit var myRecycler: RecyclerView
    lateinit var wordAdapter: Adapter
    lateinit var wordArray: List<wordTrad>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
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
        binding.editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                ListFilter(s.toString())
            }

        })
        /*binding.editTextSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                wordAdapter.dataFilterList = ListFilter(newText)
                return false
            }
        })*/

        return binding.root
    }

    // fonction de filtre
    fun ListFilter(newText: String) {
        val wordArrayFiltered = mutableListOf<wordTrad>()
        for (word in wordArray) {
            if (word.french.lowercase(Locale.ROOT)
                    .contains(newText.lowercase(Locale.ROOT))

            ) {
                wordArrayFiltered.add(word)
            }
        }
        wordAdapter.filterList(wordArrayFiltered)
    }

    fun InitArray(): List<wordTrad> {
        var mot1: wordTrad = wordTrad(0, "Bonjour", "Hello")
        var mot2: wordTrad = wordTrad(1, "Salut", "Hi")
        var mot3: wordTrad = wordTrad(2, "Manger", "Eat")
        var mot4: wordTrad = wordTrad(3, "Boire", "Drink")
        var mot5: wordTrad = wordTrad(4, "Dormir", "Sleep")
        var mot6: wordTrad = wordTrad(
            5,
            "S'asseoir sur une chaise wesh, mais toujours plus longue cette phrase ",
            "To sit down on a chair wesh"
        )
        var mot7: wordTrad = wordTrad(3, "Boire", "Drink")
        var mot8: wordTrad = wordTrad(3, "Boire", "Drink")
        var mot9: wordTrad = wordTrad(3, "Boire", "Drink")
        var mot10: wordTrad = wordTrad(3, "Boire", "Drink")
        var mot11: wordTrad = wordTrad(3, "Boire", "Drink")
        var mot12: wordTrad = wordTrad(3, "Boire", "Drink")
        var mot13: wordTrad = wordTrad(3, "Boire", "Drink")
        wordArray =
            listOf(mot1, mot2, mot3, mot4, mot5, mot6, mot7, mot8, mot9, mot10, mot11, mot12, mot13)
        return wordArray
    }

}