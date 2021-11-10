package com.rhinemann.divinecatsource.presentation.screen.search_result

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rhinemann.divinecatsource.R
import com.rhinemann.divinecatsource.core.util.toGone
import com.rhinemann.divinecatsource.core.util.toVisible
import com.rhinemann.divinecatsource.core.wrappers.LoadingState
import com.rhinemann.divinecatsource.databinding.FragmentSearchResultCatsBinding
import com.rhinemann.divinecatsource.presentation.navigation.BackButtonListener
import com.rhinemann.divinecatsource.presentation.navigation.INavigator
import com.rhinemann.divinecatsource.presentation.navigation.SearchScreen
import org.koin.android.ext.android.getKoin
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

/**
 * Created by dronpascal on 08.10.2021.
 */
class SearchResultFragment : Fragment(R.layout.fragment_search_result_cats), BackButtonListener {

    private val binding by viewBinding(FragmentSearchResultCatsBinding::bind)

    private val viewModel: SearchResultViewModel by viewModel()

    private val activityScope = getKoin().getScope("MainActivity")
    private val navigator: INavigator by activityScope.inject { parametersOf(requireActivity()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.state.observe(viewLifecycleOwner, ::handleState)
        viewModel.events.observe(viewLifecycleOwner, ::handleEvent)
        initView()
    }

    private fun initView() {

    }

    private fun handleState(state: SearchResultState) =
        when (state) {
            is SearchResultState.Loading -> handleLoading(state)
            is SearchResultState.Result -> handleResult(state)
            is SearchResultState.Response -> handleResponse(state)
        }

    private fun handleLoading(state: SearchResultState.Loading) =
        when (state.loadingState) {
            LoadingState.Loading -> binding.loader.toVisible()
            else -> binding.loader.toGone()
        }

    private fun handleResult(state: SearchResultState.Result) =
        when (state) {
            else -> Unit
        }

    private fun handleResponse(state: SearchResultState.Response) =
        when (val component = state.uiComponent) {
            else -> Unit
        }

    private fun handleEvent(event: SearchResultEvent) =
        when (event) {
            else -> Unit
        }

    override fun onBackPressed() {
        navigator.backTo(SearchScreen.TAG)
    }

}
