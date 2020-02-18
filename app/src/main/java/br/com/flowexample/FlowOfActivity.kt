package br.com.flowexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_flow_of.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

class FlowOfActivity : AppCompatActivity() {
    private val TAG = "FLOW2"
    lateinit var flowOne: Flow<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow_of)

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
            val output = flowOne.onStart { emit("Start Of Flow 2") }.toList()
            Log.i(TAG, output.toString())
            //textView.text = output.toString() //["Start Of Flow","Himanshu", "Amit", "Janishar"]

        }
    }

    private fun setupFlow() {
        flowOne = flowOf("Ana", "Maria", "Zé").onEach { delay(5000) }.flowOn(Dispatchers.Default)
    }
}
