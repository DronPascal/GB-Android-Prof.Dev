package com.rhinemann.divinecatsource.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rhinemann.divinecatsource.R
import com.rhinemann.divinecatsource.databinding.MainActivityBinding
import com.rhinemann.divinecatsource.presentation.navigation.BackButtonListener
import com.rhinemann.divinecatsource.presentation.navigation.INavigator
import com.rhinemann.divinecatsource.presentation.navigation.RandomCatScreen
import com.rhinemann.divinecatsource.presentation.navigation.SearchScreen
import com.rhinemann.divinecatsource.presentation.screen.randomcat.RandcatFragment
import com.rhinemann.divinecatsource.presentation.screen.search.SearchFragment
import org.koin.android.ext.android.getKoin
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


/**
 * Created by dronpascal on 28.10.2021.
 */
class MainActivity : AppCompatActivity(R.layout.main_activity) {

    private val viewModel: MainViewModel by viewModel()

    private lateinit var binding: MainActivityBinding

    private val scope = getKoin().getOrCreateScope<MainActivity>("MainActivity")

    val navigator: INavigator by scope.inject<INavigator> { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()

        if (savedInstanceState == null) {
            navigator.navigateTo(
                screen = RandomCatScreen()
            )
        }
    }

    private fun initView() {
        with(binding.bottomNavigationView) {
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.miHome -> navigator.backTo(RandomCatScreen.TAG)
                    R.id.miSearch -> when (navigator.topFragment) {
                        is SearchFragment -> Unit
                        else -> {
                            navigator.backTo(RandomCatScreen.TAG)
                            navigator.navigateTo(SearchScreen())
                        }
                    }
                    else -> TODO()
                }
                true
            }
        }
    }

    override fun onBackPressed() {
        when (val fragment = navigator.topFragment) {
            // if fragment is home -> close app
            is RandcatFragment -> moveTaskToBack(true)
            is BackButtonListener -> fragment.onBackPressed()
            else -> {
                navigator.backTo(RandomCatScreen.TAG)
                binding.bottomNavigationView.selectedItemId = R.id.miHome
            }
        }
        supportFragmentManager.backStackEntryCount.also { println("COUNT = $it") }
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.close()
    }
}
