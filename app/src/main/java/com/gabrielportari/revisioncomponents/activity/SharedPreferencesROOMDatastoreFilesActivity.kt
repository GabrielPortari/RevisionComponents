package com.gabrielportari.revisioncomponents.activity

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.gabrielportari.revisioncomponents.R
import com.gabrielportari.revisioncomponents.databinding.ActivitySharedPreferencesRoomdatastoreFilesBinding
import com.gabrielportari.revisioncomponents.model.UserROOM
import com.gabrielportari.revisioncomponents.repository.ROOMDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class SharedPreferencesROOMDatastoreFilesActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySharedPreferencesRoomdatastoreFilesBinding

    private val MY_PREFERENCES = "my_preferences"
    private var sharedPreferences: SharedPreferences? = null
    private val dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    /* criar chaves no datastore */
    private val NAME = "name"
    private val LAST_NAME = "last_name"
    private val EMAIL = "email"
    val prefName = stringPreferencesKey(NAME)
    val prefLastName = stringPreferencesKey(LAST_NAME)
    val prefEmail = stringPreferencesKey(EMAIL)

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
            sharedPreferences = getSharedPreferences(MY_PREFERENCES, MODE_PRIVATE) // carrega a shared preferences do celular
            /* salvar dados na shared preferences */
            val name = binding.textInputName.text.toString()
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

            /* salvar dados no ROOM */
            val name = binding.textInputName.text.toString()
            val lastName = binding.textInputLastName.text.toString()
            val email = binding.textInputEmail.text.toString()

            /* criação do banco de dados*/
            val db = Room.databaseBuilder(
                applicationContext,
                ROOMDatabase::class.java, "database-name"
            ).build()

            /* inserção de dados no banco de dados*/
            val userDAO = db.userDAO()
            val user = UserROOM(1, name, lastName, email)
            userDAO.insertAll(user)

            /* recuperar dados no banco de dados */
            val users = userDAO.getAll()

            /* excluir dados no banco de dados */
            userDAO.delete(user)

        }
        binding.buttonSaveDatastore.setOnClickListener{
            /* recuperar dado dos inputs de texto */
            val name = binding.textInputName.text.toString()
            val lastName = binding.textInputLastName.text.toString()
            val email = binding.textInputEmail.text.toString()

            /* salvar dados no datastore */
            lifecycleScope.launch {
                saveDatastore()
            }

            /* recuperar dados do datastore */
            val recoverNameFlow: Flow<String> = dataStore.data
                .map { preferencesDataStore ->
                preferencesDataStore[prefName] ?: ""
            }
        }
        binding.buttonSaveFiles.setOnClickListener {
            /* recuperar dado dos inputs de texto */
            val name = binding.textInputName.text.toString()
            val lastName = binding.textInputLastName.text.toString()
            val email = binding.textInputEmail.text.toString()

            
        }
    }
    suspend fun saveDatastore(){
        dataStore.edit { settings ->
            val user_name = settings[prefName] ?: "erro ao recuperar name"
            val user_last_name = settings[prefLastName] ?: "erro ao recuperar user last name"
            val user_email = settings[prefEmail] ?: "erro ao recuperar email"
        }
    }
}