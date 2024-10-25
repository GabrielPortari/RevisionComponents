package com.gabrielportari.revisioncomponents

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.gabrielportari.revisioncomponents.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        /*Button para ir para a Activity de Activities e Fragments*/
        binding.buttonActivityAndFragment.setOnClickListener {
            val intent = Intent(this, ActivitiesAndFragmentActivity::class.java)
            startActivity(intent)
        }

        /*Button para ir para a Activity de Interface Elements*/
        binding.buttonInterfaceElements.setOnClickListener {
            val intent = Intent(this, InterfaceElementsActivity::class.java)
            startActivity(intent)
        }
    }
}