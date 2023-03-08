package com.example.english_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.english_project.dataModel.wordTrad
import com.example.english_project.databinding.FragmentDatabaseBinding
import com.example.english_project.databinding.FragmentMenuBinding


class DatabaseFragment : Fragment() {

    lateinit var binding : FragmentDatabaseBinding
    lateinit var myRecycler : RecyclerView
    lateinit var wordAdapter : Adapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_database,container,false)

        // liste arbitraire de mots pour les tests
        var tableauDeMots : Array<wordTrad> = initArray()

        // initialisation du recycler view
        myRecycler = binding.recyclerViewDatabase
        myRecycler.layoutManager = LinearLayoutManager(context)
        // listener de chaque item cliqué + initialisation de l'adapter
        wordAdapter = Adapter(tableauDeMots) {
            val action = DatabaseFragmentDirections.actionDatabaseFragmentToModifStatsFragment(it)
            findNavController().navigate(action)
        }
        myRecycler.adapter = wordAdapter

        /*// SearchView
        binding.editTextSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val searchText = newText!!

            }

        })*/


        return binding.root
    }

    fun initArray() : Array<wordTrad> {
        var mot1 : wordTrad = wordTrad(0,"Bonjour","Hello")
        var mot2 : wordTrad = wordTrad(1,"Bonjour","Hello")
        var mot3 : wordTrad = wordTrad(2,"Bonjour","Hello")
        var mot4 : wordTrad = wordTrad(3,"Bonjour","Hello")
        var mot5 : wordTrad = wordTrad(4,"Bonjour","Hello")
        var mot6 : wordTrad = wordTrad(5,"Bonjour","Hello")
        val tableauMots = arrayOf(mot1,mot2,mot3,mot4,mot5,mot6)
        return tableauMots
    }

}