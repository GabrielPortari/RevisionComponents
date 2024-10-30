package com.gabrielportari.revisioncomponents.activity

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.gabrielportari.revisioncomponents.R
import com.gabrielportari.revisioncomponents.databinding.ActivitySharedPreferencesRoomdatastoreFilesBinding

class SharedPreferencesROOMDatastoreFilesActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySharedPreferencesRoomdatastoreFilesBinding
    private var sharedPreferences: SharedPreferences? = null

    private val MY_PREFERENCES = "myPreferences"
    private val NAME = "name"
    private val LAST_NAME = "last_name"
    private val EMAIL = "email"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_shared_preferences_roomdatastore_files)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding = ActivitySharedPreferencesRoomdatastoreFilesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSaveSharedPref.setOnClickListener {
            /* salvar dados na shared preferences */
            val name = binding.textInputName.text.toString()
            sharedPreferences = getSharedPreferences(MY_PREFERENCES, MODE_PRIVATE) // carrega a shared preferences do celular
            val lastName = binding.textInputLastName.text.toString()
            val email = binding.textInputEmail.text.toString()
            val editor = sharedPreferences!!.edit() // abre o editor da shared preferences
            editor.putString(NAME, name)
            editor.putString(LAST_NAME, lastName) // salva os dados da shared preferences
            editor.putString(EMAIL, email)
            editor.commit() // guarda os dados no celular

            /* recuperar os dados da shared preferences
            sharedPreferences = getSharedPreferences(MY_PREFERENCES, MODE_PRIVATE) // carrega a shared preferences do celular
            if(sharedPreferences!!.contains(NAME) && sharedPreferences!!.contains(LAST_NAME) && sharedPreferences!!.contains(EMAIL)){
                val nameSaved = sharedPreferences!!.getString(NAME, "")
                val lastNameSaved = sharedPreferences!!.getString(LAST_NAME, "")
                val emailSaved = sharedPreferences!!.getString(EMAIL, "")
            }
             */



        }

        binding.buttonSaveRoom.setOnClickListener {
        }

        binding.buttonSaveDatastore.setOnClickListener{
        }

        binding.buttonSaveFiles.setOnClickListener {
        }

    }
}