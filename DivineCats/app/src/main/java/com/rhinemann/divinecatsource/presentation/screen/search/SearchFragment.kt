package com.rhinemann.divinecatsource.presentation.screen.search

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageView
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.chip.Chip
import com.rhinemann.divinecatsource.R
import com.rhinemann.divinecatsource.core.util.showSystemMessage
import com.rhinemann.divinecatsource.core.util.toDp
import com.rhinemann.divinecatsource.core.util.toGone
import com.rhinemann.divinecatsource.core.util.toVisible
import com.rhinemann.divinecatsource.core.wrappers.LoadingState
import com.rhinemann.divinecatsource.core.wrappers.UIComponent
import com.rhinemann.divinecatsource.databinding.FragmentSearchCatBinding
import com.rhinemann.divinecatsource.presentation.image.IImageLoader
import com.rhinemann.divinecatsource.presentation.navigation.INavigator
import com.rhinemann.divinecatsource.presentation.navigation.SearchResultScreen
import com.rhinemann.divinecatsource.presentation.screen.search.model.CheckableBreed
import com.rhinemann.divinecatsource.presentation.screen.search.model.CheckableCategory
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


/**
 * Created by dronpascal on 07.10.2021.
 */
class SearchFragment : Fragment(R.layout.fragment_search_cat) {

    private val binding by viewBinding(FragmentSearchCatBinding::bind)

    private val viewModel: SearchViewModel by viewModel()

    private val activityScope = getKoin().getScope("MainActivity")
    private val navigator: INavigator by activityScope.inject { parametersOf(requireActivity()) }

    private val imageLoader: IImageLoader<ImageView> by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadingState.observe(viewLifecycleOwner, ::handleLoadingState)
        viewModel.responseState.observe(viewLifecycleOwner, ::handleResponseState)
        viewModel.categoriesState.observe(viewLifecycleOwner, ::handleCategoriesState)
        viewModel.breedsState.observe(viewLifecycleOwner, ::handleBreedsState)
        viewModel.events.observe(viewLifecycleOwner, ::handleEvent)
        initView()
    }

    private fun initView() {
        setupListeners()
    }

    private fun setupListeners() {
        binding.fab.setOnClickListener { viewModel.onWatchCatsClicked() }
    }

    private fun handleLoadingState(loadingState: LoadingState?) =
        when (loadingState) {
            LoadingState.Loading -> binding.loader.toVisible()
            else -> binding.loader.toGone()
        }

    private fun handleResponseState(uiComponent: UIComponent?) =
        when (uiComponent) {
            is UIComponent.Toast -> this@SearchFragment.showSystemMessage(uiComponent.message)
            else -> Unit
        }

    private fun handleCategoriesState(list: List<CheckableCategory>?) {
        list?.forEach { addChipToCategoryGroup(it) }
    }

    private fun handleBreedsState(checkableBreeds: List<CheckableBreed>?) {
        binding.cgSelectedBreeds.removeAllViews()
        binding.cgAllBreeds.removeAllViews()
        checkableBreeds?.forEach {
            addChipToBreedGroup(it)
            if (it.isChecked) addChipToSelectedBreedsGroup(it)
        }
        initBreedAutoCompleteTextView()
    }

    private fun handleEvent(event: SearchEvent) =
        when (event) {
            is SearchEvent.OpenScreen.SearchResult -> {
                navigator.navigateTo(SearchResultScreen())
            }
        }

    private fun addChipToCategoryGroup(category: CheckableCategory) {
        val chip = layoutInflater.inflate(
            R.layout.chip_choice,
            binding.cgAllCategories,
            false
        ) as Chip
        chip.text = category.name
        chip.isChecked = category.isChecked
        chip.setOnClickListener { viewModel.onCategoryCheckChanged(name = category.name) }
        binding.cgAllCategories.addView(chip as View)
    }

    private fun addChipToBreedGroup(breed: CheckableBreed) {
        val chip = layoutInflater.inflate(
            R.layout.chip_choice,
            binding.cgAllBreeds,
            false
        ) as Chip
        chip.text = breed.name
        chip.isChecked = breed.isChecked
        chip.setOnClickListener { viewModel.onBreedCheckChanged(name = breed.name) }
        binding.cgAllBreeds.addView(chip as View)
    }

    private fun addChipToSelectedBreedsGroup(breed: CheckableBreed) {
        binding.cgSelectedBreeds.removeAllViews()
        val chip = layoutInflater.inflate(
            R.layout.chip_action,
            binding.cgSelectedBreeds,
            false
        ) as Chip
        chip.text = breed.name
        chip.setOnCloseIconClickListener { viewModel.onBreedCheckChanged(name = breed.name) }
        chip.isChipIconVisible = true
        chip.chipIconSize = chip.height.toDp.toFloat()
        imageLoader.loadDrawable(
            breed.imageUrl,
            cornerRadiusPx = 50,
            width = 100,
            height = 100
        ) { chip.chipIcon = it }
        binding.cgSelectedBreeds.addView(chip as View)
    }

    private fun initBreedAutoCompleteTextView() {
        viewModel.breedsState.value?.let { breeds ->
            val adapter =
                ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_dropdown_item_1line,
                    breeds.map { it.name }
                )
            with(binding.autoCompleteTextView) {
                setAdapter<ArrayAdapter<String>>(adapter)
                setOnItemClickListener { parent, _, position, _ ->
                    text = null
                    val breedName = parent.getItemAtPosition(position) as String
                    viewModel.onBreedCheckChanged(name = breedName)
                }
            }
        }
    }
}
