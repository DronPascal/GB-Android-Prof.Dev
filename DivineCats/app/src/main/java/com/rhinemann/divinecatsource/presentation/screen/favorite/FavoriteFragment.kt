package com.rhinemann.divinecatsource.presentation.screen.favorite

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
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

/**
 * Created by dronpascal on 08.10.2021.
 */
class FavoriteFragment : Fragment(R.layout.fragment_search_result_cats), BackButtonListener {

    private val binding by viewBinding(FragmentSearchResultCatsBinding::bind)

    private val viewModel: FavoriteViewModel by viewModel()

    private val navigator: INavigator by inject { parametersOf(requireActivity()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {

    }

    override fun onBackPressed() {
        navigator.backTo(SearchScreen.TAG)
    }

}
