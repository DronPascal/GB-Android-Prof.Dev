package com.gb.stopwatch.presentation

/**
 * Created by dronpascal on 01.11.2021.
 */
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gb.stopwatch.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by dronpascal on 01.11.2021.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val stopwatchListViewModel: StopwatchListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        CoroutineScope(Dispatchers.Main + SupervisorJob()).launch {
            stopwatchListViewModel.ticker.collect {
                binding.textTime.text = it
                println(it)
            }
        }
        binding.buttonStart.setOnClickListener {
            stopwatchListViewModel.start()
        }
        binding.buttonPause.setOnClickListener {
            stopwatchListViewModel.pause()
        }
        binding.buttonStop.setOnClickListener {
            stopwatchListViewModel.stop()
        }
    }
}
