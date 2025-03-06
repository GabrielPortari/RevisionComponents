package com.gabrielportari.revisioncomponents.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.gabrielportari.revisioncomponents.R
import com.gabrielportari.revisioncomponents.databinding.ActivityThreadsAndCoroutinesBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Thread.sleep

class ThreadsAndCoroutines : AppCompatActivity() {
    private lateinit var binding: ActivityThreadsAndCoroutinesBinding

    private var executeThread: Boolean = false

    private var counterThread: Int = 0
    private var counterCoroutine: Int = 0

    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_threads_and_coroutines)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding = ActivityThreadsAndCoroutinesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /* Iniciar a thread */
        binding.buttonThreadStart.setOnClickListener {
            executeThread = true
            Thread {
                while(executeThread){

                    if(!executeThread){
                        return@Thread // Condicional para parar a thread
                    }

                    val str =  "Thread Counter: $counterThread"
                    runOnUiThread {
                        binding.textThreadCounter.text = str
                        binding.buttonThreadStart.isEnabled = false
                    }

                    counterThread++
                    sleep(1000)
                }
            }.start() //inicia uma função asíncrona
        }

        /* Parar a thread */
        binding.buttonThreadStop.setOnClickListener {
            executeThread = false
            if(!binding.buttonThreadStart.isEnabled){
                binding.buttonThreadStart.isEnabled = true
            }
        }


        /*O uso de coroutines é recomendado sempre, pois são mais eficientes
         e menos custosas em termos de recursos do dispositivo que as threads*/

        /* Iniciar a coroutine */
        binding.buttonCoroutineStart.setOnClickListener {
            job = CoroutineScope(Dispatchers.IO).launch {
                execute()
            }
        }

        /* Parar a coroutine */
        binding.buttonCoroutineStop.setOnClickListener {
            job?.cancel()
            if(!binding.buttonCoroutineStart.isEnabled){
                binding.buttonCoroutineStart.isEnabled = true
            }
        }
    }

    private suspend fun execute(){
        while(true){
            val str =  "Coroutine Counter: $counterCoroutine"
            withContext(Dispatchers.Main) {
                binding.textCoroutineCounter.text = str
                binding.buttonCoroutineStart.isEnabled = false
            }
            counterCoroutine++
            delay(1000)
        }
    }
}