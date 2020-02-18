package br.com.flowexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_as_flow.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.*

class AsFlowActivity : AppCompatActivity() {
    private val TAG = "FLOW3"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_as_flow)

        setupClick()
    }

    private fun setupClick() {
        btnDoSomeWork.setOnClickListener {
            doSomeWork()
        }
    }

    private fun doSomeWork() {
        lifecycleScope.launchWhenCreated {
             drop()
             take()
             error()
        }
    }

    private suspend fun error() {
        var output2 = (1..5).asFlow().onEach {
            if (it == 3) throw RuntimeException("Error on $it")
        }.catch {
            emitAll((99..100).asFlow())
        }.toList()
        Log.i(TAG, output2.toString())
    }

    private suspend fun take() {
        var output1 = (1..5)
            .asFlow()
            .take(2) //takes first two emitted integers
            .toList()

        Log.i(TAG, output1.toString())
    }

    private suspend fun drop() {
        var output = (1..5)
            .asFlow()
            .drop(2) //skips first two emitted integers
            .toList()
        Log.i(TAG, output.toString())
    }
}
