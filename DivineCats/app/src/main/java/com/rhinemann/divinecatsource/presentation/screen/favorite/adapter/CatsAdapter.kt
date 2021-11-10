package com.rhinemann.divinecatsource.presentation.screen.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.rhinemann.divinecatsource.databinding.ItemCatBinding
import com.rhinemann.divinecatsource.domain.model.cat.Cat
import com.rhinemann.divinecatsource.presentation.image.IImageLoader

/**
 * Created by dronpascal on 09.11.2021.
 */
class CatsAdapter(
    private val onCatClick: (Cat) -> Unit,
    private val imageLoader: IImageLoader<ImageView>
) : ListAdapter<Cat, CatViewHolder>(CatsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCatBinding.inflate(layoutInflater, parent, false)
        return CatViewHolder(binding, imageLoader)
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        holder.bind(getItem(position), onCatClick)
    }
}

private class CatsDiffCallback : DiffUtil.ItemCallback<Cat>() {

    override fun areItemsTheSame(oldItem: Cat, newItem: Cat): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Cat, newItem: Cat): Boolean =
        oldItem == newItem
}
