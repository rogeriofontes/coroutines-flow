package br.com.flowexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        basicFlow.setOnClickListener {
            startActivity(Intent(this, BasicFlowActivity::class.java))
        }
        flowOf.setOnClickListener {
            startActivity(Intent(this, FlowOfActivity::class.java))
        }
        asFlow.setOnClickListener {
            startActivity(Intent(this, AsFlowActivity::class.java))
        }
        zipFlow.setOnClickListener {
            startActivity(Intent(this, ZipFlowActivity::class.java))
        }
    }
}
