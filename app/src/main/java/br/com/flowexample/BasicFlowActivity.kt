package br.com.flowexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_basic_flow.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class BasicFlowActivity : AppCompatActivity() {
    private val TAG = "FLOW1"
    lateinit var flow: Flow<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_flow)

        setupFlow()
        setupClicks()
    }

    fun setupFlow() {
        flow = flow {
            Log.d(TAG, "Start flow")
            (0..10).forEach {
                // Emit items with 500 milliseconds delay
                delay(500)
                Log.d(TAG, "Emitting $it")
                emit(it)

            }
        }.map{
            it * it
        }.flowOn(Dispatchers.Default)
    }

    private fun setupClicks() {
        btnDoSomeWork.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                flow.collect{
                    Log.d(TAG, it.toString())
                }
            }
        }
    }
}
