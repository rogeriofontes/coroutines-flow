package br.com.flowexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_zip_flow.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class ZipFlowActivity : AppCompatActivity() {
    private val TAG = "FLOW4"

    lateinit var flowOne: Flow<String>
    lateinit var flowTwo: Flow<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zip_flow)

        setupFlow()
        setupClick()
    }

    private fun setupClick() {
        btnDoSomeWork.setOnClickListener {
            doSomeWork()
        }
    }

    private fun doSomeWork() {
        lifecycleScope.launchWhenCreated {
            val output = flowOne.zip(flowTwo)
            { firstString, secondString ->
                "$firstString $secondString"
            }.toList()
            Log.i(TAG, output.toString())
           // textView.text = output.toString()
        }
    }

    private fun setupFlow() {
        flowOne = flowOf("Sandra", "Maria", "Zé").flowOn(Dispatchers.Default)
        flowTwo = flowOf("Juliana", "Beatriz", "Ramalho").flowOn(Dispatchers.Default)
    }
}
