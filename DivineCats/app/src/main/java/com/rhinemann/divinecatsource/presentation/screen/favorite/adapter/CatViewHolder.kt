package com.rhinemann.divinecatsource.presentation.screen.favorite.adapter

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.rhinemann.divinecatsource.databinding.ItemCatBinding
import com.rhinemann.divinecatsource.domain.model.cat.Cat
import com.rhinemann.divinecatsource.presentation.image.IImageLoader

/**
 * Created by dronpascal on 09.11.2021.
 */
class CatViewHolder(
    private val binding: ItemCatBinding,
    private val imageLoader: IImageLoader<ImageView>,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(cat: Cat, onCatClick: (Cat) -> Unit) {
        setThumbnail(cat.imageUrl)
        setClickListener(onCatClick, cat)
    }

    private fun setThumbnail(imageUrl: String) {
        imageLoader.loadTo(url = imageUrl, target = binding.imgCat)
    }

    // todo: do we need to set click listener
    private fun setClickListener(
        onCatClick: (Cat) -> Unit,
        cat: Cat,
    ) {
        itemView.setOnClickListener { onCatClick(cat) }
    }
}
