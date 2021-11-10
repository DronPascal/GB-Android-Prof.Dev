package com.rhinemann.divinecatsource.presentation.screen.randomcat

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rhinemann.divinecatsource.R
import com.rhinemann.divinecatsource.core.util.getColorFromAttr
import com.rhinemann.divinecatsource.core.util.showSystemMessage
import com.rhinemann.divinecatsource.core.util.toGone
import com.rhinemann.divinecatsource.core.util.toVisible
import com.rhinemann.divinecatsource.core.wrappers.LoadingState
import com.rhinemann.divinecatsource.core.wrappers.UIComponent
import com.rhinemann.divinecatsource.databinding.FragmentRandomCatBinding
import com.rhinemann.divinecatsource.domain.model.cat.Cat
import com.rhinemann.divinecatsource.presentation.image.IImageLoader
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by dronpascal on 04.10.2021.
 */
class RandcatFragment : Fragment(R.layout.fragment_random_cat) {

    private val binding by viewBinding(FragmentRandomCatBinding::bind)

    private val viewModel: RandcatViewModel by viewModel()

    private val imageLoader: IImageLoader<ImageView> by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadingState.observe(viewLifecycleOwner, ::handleLoadingState)
        viewModel.responseState.observe(viewLifecycleOwner, ::handleResponseState)
        viewModel.saveBtnState.observe(viewLifecycleOwner, ::handleSaveBtnState)
        viewModel.events.observe(viewLifecycleOwner, ::handleEvent)
        viewModel.currentCat.observe(viewLifecycleOwner, ::handleCurrentCat)
        initView()
    }

    private fun initView() {
        binding.fab.setOnClickListener { viewModel.addCatToFavorite() }
        val listener = View.OnClickListener {
            binding.imgCat.toGone()
            viewModel.getNextCat()
        }
        binding.btnDivine.setOnClickListener(listener)
        binding.btnBased.setOnClickListener(listener)
        binding.btnCringe.setOnClickListener(listener)
    }

    private fun handleResponseState(uiComponent: UIComponent?) =
        when (uiComponent) {
            is UIComponent.Toast -> this@RandcatFragment.showSystemMessage(uiComponent.message)
            else -> Unit
        }

    private fun handleCurrentCat(cat: Cat?) = cat?.let {
        binding.loader.toVisible()
        val (maxW, maxH) = binding.imageHolder.width to binding.imageHolder.height
        val scaling = minOf(1f * maxW / cat.width, 1f * maxH / cat.height)
        val (newW, newH) = (cat.width * scaling).toInt() to (cat.height * scaling).toInt()
        binding.imgCat.layoutParams.width = newW
        binding.imgCat.layoutParams.height = newH
        imageLoader.loadTo(
            url = cat.imageUrl,
            width = cat.width,
            height = cat.height,
            target = binding.imgCat,
            onLoaded = {
                binding.imgCat.toVisible()
                binding.loader.toGone()
            }
        )
    }

    private fun handleSaveBtnState(saveBtnState: SaveBtnState?) =
        when (saveBtnState) {
            is SaveBtnState.Saved -> {
                binding.fab.text = getString(R.string.saved)
                binding.fab.iconTint =
                    ColorStateList.valueOf(getColor(requireContext(), R.color.red))
            }
            else -> {
                binding.fab.text = getString(R.string.save)
                binding.fab.iconTint = ColorStateList.valueOf(
                    requireContext().getColorFromAttr(R.attr.colorOnPrimary)
                )
            }
        }

    private fun handleLoadingState(loadingState: LoadingState) =
        when (loadingState) {
            LoadingState.Loading -> binding.loader.toVisible()
            LoadingState.Idle -> binding.loader.toGone()
        }

    private fun handleEvent(event: RandcatEvent) =
        when (event) {
            else -> Unit
        }

}

