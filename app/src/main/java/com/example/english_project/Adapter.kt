package com.example.english_project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.english_project.dataModel.wordTrad
import com.example.english_project.databinding.ItemTraductionBinding

class Adapter(
    private val datalist : Array<wordTrad>,
    private val onItemClick : ((Int) -> Unit)
) : RecyclerView.Adapter<Adapter.Holder>() {

    // holder
    inner class Holder(itemview : View) : RecyclerView.ViewHolder(itemview){
        val binding = ItemTraductionBinding.bind(itemview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val wordView = inflater.inflate(R.layout.item_traduction, parent, false)
        return Holder(wordView)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val word = datalist[position]

        holder.binding.frenchWord.text = word.french
        holder.binding.englishWord.text = word.english

        // listener holder
        holder.itemView.setOnClickListener { onItemClick(word.id) }
    }

    override fun getItemCount(): Int = datalist.size
}