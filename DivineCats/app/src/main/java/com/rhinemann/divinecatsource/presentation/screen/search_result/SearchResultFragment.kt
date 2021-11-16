package com.rhinemann.divinecatsource.presentation.screen.search_result

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rhinemann.divinecatsource.R
import com.rhinemann.divinecatsource.core.util.toGone
import com.rhinemann.divinecatsource.core.util.toVisible
import com.rhinemann.divinecatsource.core.wrappers.LoadingState
import com.rhinemann.divinecatsource.databinding.FragmentSearchResultCatsBinding
import com.rhinemann.divinecatsource.presentation.image.IImageLoader
import com.rhinemann.divinecatsource.presentation.navigation.BackButtonListener
import com.rhinemann.divinecatsource.presentation.navigation.INavigator
import com.rhinemann.divinecatsource.presentation.navigation.SearchScreen
import com.rhinemann.divinecatsource.presentation.screen.search_result.adapter.CatsAdapter
import kotlinx.coroutines.flow.*
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

/**
 * Created by dronpascal on 08.10.2021.
 */
class SearchResultFragment : Fragment(R.layout.fragment_search_result_cats), BackButtonListener {

    private val binding by viewBinding(FragmentSearchResultCatsBinding::bind)

    private val viewModel: SearchResultViewModel by viewModel()

    private val imageLoader: IImageLoader<ImageView> by inject()

    private val activityScope = getKoin().getScope("MainActivity")
    private val navigator: INavigator by activityScope.inject { parametersOf(requireActivity()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadingState.observe(viewLifecycleOwner, ::handleLoadingState)
        viewModel.events.observe(viewLifecycleOwner, ::handleEvent)
        initView()
    }

    private fun initView() {
        initList()
        setupListeners()
    }

    private fun setupListeners() {
    }

    private fun handleLoadingState(loadingState: LoadingState?) =
        when (loadingState) {
            LoadingState.Loading -> binding.loader.toVisible()
            else -> binding.loader.toGone()
        }

    private fun handleEvent(event: SearchResultEvent) =
        when (event) {
            is SearchResultEvent.OpenScreen.CatFullscreen -> {

            }
        }

    private fun initList() {
        val catAdapter = CatsAdapter(imageLoader)
        binding.rvCats.apply {
            adapter = catAdapter
        }

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.searchCats().collectLatest { pagingData ->
                catAdapter.submitData(pagingData)
            }
        }
    }

    override fun onBackPressed() {
        navigator.backTo(SearchScreen.TAG)
    }
}
