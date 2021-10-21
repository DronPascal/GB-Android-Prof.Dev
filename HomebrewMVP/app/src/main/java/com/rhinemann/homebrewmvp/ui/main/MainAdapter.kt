package com.rhinemann.homebrewmvp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rhinemann.homebrewmvp.data.remote.WordModel

class MainAdapter(
    private val itemClickListener: (WordModel) -> Unit
) : ListAdapter<WordModel, MainAdapter.MainViewHolder>(MainCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(parent)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class MainViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        ItemWordBinding.inflate(parent.inflater(), parent, false).root
    ) {

        fun bind(data: WordModel) {
            val binder = ItemWordBinding.bind(itemView)

            binder.headerTextviewRecyclerItem.text = data.text
            binder.descriptionTextviewRecyclerItem.text =
                data.meaning?.firstOrNull()?.translation?.translation

            binder.root.setOnClickListener { itemClickListener(data) }
        }
    }

    private fun ViewGroup.inflater() = LayoutInflater.from(context)
}

object MainCallback : DiffUtil.ItemCallback<WordModel>() {

    override fun areItemsTheSame(oldItem: WordModel, newItem: WordModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: WordModel, newItem: WordModel): Boolean {
        return oldItem == newItem
    }
}