package com.rhinemann.divinecatsource.presentation.screen.search_result.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.rhinemann.divinecatsource.R
import com.rhinemann.divinecatsource.domain.model.cat.Cat
import com.rhinemann.divinecatsource.presentation.base.viewById
import com.rhinemann.divinecatsource.presentation.image.IImageLoader

/**
 * Created by dronpascal on 15.11.2021.
 */
class CatViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val image by view.viewById<ImageView>(R.id.img_cat)

    fun bind(cat: Cat, imageLoader: IImageLoader<ImageView>) {
        imageLoader.loadTo(
            url = cat.imageUrl,
            target = image
        )
    }

    companion object {
        fun create(parent: ViewGroup): CatViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_cat, parent, false)
            return CatViewHolder(view)
        }
    }
}
