package com.rhinemann.divinecatsource.presentation

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.rhinemann.divinecatsource.R
import com.rhinemann.divinecatsource.core.wrappers.LoadingState
import com.rhinemann.divinecatsource.databinding.MainActivityBinding
import com.rhinemann.divinecatsource.presentation.image.IImageLoader
import dagger.android.AndroidInjection
import javax.inject.Inject

/**
 * Created by dronpascal on 28.10.2021.
 */
class MainActivity : AppCompatActivity(R.layout.main_activity) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>

    private lateinit var binding: MainActivityBinding

    val viewModel: MainViewModel by lazy {
        viewModelFactory.create(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        viewModel.viewState.observe(this, ::handleViewState)
    }

    private fun initView() {
        val listener = View.OnClickListener { viewModel.getCat() }
        binding.btnDivine.setOnClickListener(listener)
        binding.btnBase.setOnClickListener(listener)
    }

    private fun handleViewState(state: MainViewState?) {
        state?.cat?.let { imageLoader.loadTo(it.url, binding.imgCat) }
        when (state?.loadingState) {
            LoadingState.Loading -> binding.loader.visibility = View.VISIBLE
            else -> binding.loader.visibility = View.GONE
        }
    }

}