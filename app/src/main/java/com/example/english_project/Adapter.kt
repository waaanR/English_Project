package com.example.english_project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.english_project.dataModel.wordTrad
import com.example.english_project.databinding.ItemTraductionBinding
import java.util.*

class Adapter(
    private var datalist: List<wordTrad>,
    private val onItemClick: ((Int) -> Unit)
) : RecyclerView.Adapter<Adapter.Holder>() {

    // pour la searchview, on est obligé d'init un sous tableau
    var dataFilterList: List<wordTrad>

    init {
        dataFilterList = datalist
    } // c'est bien de toujours garder cette structure car quand il y aura des choses plus
    // compliquées, ça pourrait se mettre dans le init aussi, meme si android studio nous dit de
    // faire "var dataFilterList : Array<wordTrad> = datalist

    // holder
    inner class Holder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val binding = ItemTraductionBinding.bind(itemview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val wordView = inflater.inflate(R.layout.item_traduction, parent, false)
        return Holder(wordView)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val word = dataFilterList[position]

        holder.binding.frenchWord.text = word.french
        holder.binding.englishWord.text = word.english

        // listener holder
        holder.itemView.setOnClickListener { onItemClick(word.id) }
    }

    override fun getItemCount(): Int = dataFilterList.size


    // fonction de filtre pour la searchview
    fun filterList(newText: String?) {
        val filteredWordList = mutableListOf<wordTrad>()
        if (newText.isNullOrBlank()) {
            this.dataFilterList = filteredWordList
        }
        else {
            for (word in datalist) {
                if (word.french.lowercase(Locale.ROOT)
                        .contains(newText.lowercase(Locale.ROOT)) || word.english.lowercase(Locale.ROOT)
                        .contains(newText.lowercase(Locale.ROOT))
                ) {
                    filteredWordList.add(word)
                }
            }
            this.dataFilterList = filteredWordList
        }
        notifyDataSetChanged()


    }
}


