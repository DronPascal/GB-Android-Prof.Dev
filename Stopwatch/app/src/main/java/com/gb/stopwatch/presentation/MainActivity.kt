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

    private val stopwatch1ViewModel: StopwatchViewModel by viewModel(StopwatchVMQualifier(key = "first"))

    private val stopwatch2ViewModel: StopwatchViewModel by viewModel(StopwatchVMQualifier(key = "second"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        init1Ticker()
        init2Ticker()
    }

    private fun init1Ticker() {
        CoroutineScope(Dispatchers.Main + SupervisorJob()).launch {
            stopwatch1ViewModel.ticker.collect {
                binding.textTime.text = it
            }
        }
        binding.buttonStart.setOnClickListener {
            stopwatch1ViewModel.start()
        }
        binding.buttonPause.setOnClickListener {
            stopwatch1ViewModel.pause()
        }
        binding.buttonStop.setOnClickListener {
            stopwatch1ViewModel.stop()
        }
    }

    private fun init2Ticker() {
        CoroutineScope(Dispatchers.Main + SupervisorJob()).launch {
            stopwatch2ViewModel.ticker.collect {
                binding.textTime2.text = it
            }
        }
        binding.buttonStart2.setOnClickListener {
            stopwatch2ViewModel.start()
        }
        binding.buttonPause2.setOnClickListener {
            stopwatch2ViewModel.pause()
        }
        binding.buttonStop2.setOnClickListener {
            stopwatch2ViewModel.stop()
        }
    }
}
