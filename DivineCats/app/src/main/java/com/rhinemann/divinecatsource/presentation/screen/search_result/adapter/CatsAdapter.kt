package com.rhinemann.divinecatsource.presentation.screen.search_result.adapter

import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.rhinemann.divinecatsource.domain.model.cat.Cat
import com.rhinemann.divinecatsource.presentation.image.IImageLoader

/**
 * Created by dronpascal on 15.11.2021.
 */
class CatsAdapter(
    private val imageLoader: IImageLoader<ImageView>
) : PagingDataAdapter<Cat, CatViewHolder>(CAT_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        return CatViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val catItem = getItem(position)
        catItem?.let {
            holder.bind(it, imageLoader)
        }
    }

    companion object {
        private val CAT_COMPARATOR = object : DiffUtil.ItemCallback<Cat>() {
            override fun areItemsTheSame(oldItem: Cat, newItem: Cat): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Cat, newItem: Cat): Boolean =
                oldItem == newItem
        }
    }
}
