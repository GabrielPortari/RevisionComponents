package com.gabrielportari.revisioncomponents

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.gabrielportari.revisioncomponents.databinding.ActivityInterfaceElementsBinding
import com.google.android.material.snackbar.Snackbar

class InterfaceElementsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInterfaceElementsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_interface_elements)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding = ActivityInterfaceElementsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.buttonUpdateText.setOnClickListener {
            /* este button altera o texto do TextView com o texto do EditText */
            val text = binding.editTextInsert.text.toString()
            binding.textMyText.text = text
        }
        binding.buttonToast.setOnClickListener{
            /* este button abre um toast */
            Toast.makeText(this, "Showing a toast", Toast.LENGTH_SHORT).show()
        }
        binding.buttonSnackbar.setOnClickListener {
            /* este button abre um snackbar */
            Snackbar.make(it, "Showing a snackbar", Snackbar.LENGTH_SHORT).show()
        }
        binding.buttonDialog.setOnClickListener {
            /* este button cria e abre um dialog */
            val snackBuilder: AlertDialog.Builder = AlertDialog.Builder(this)
            snackBuilder.setTitle("Dialog title")
            snackBuilder.setMessage("Dialog message")
            snackBuilder.setPositiveButton("OK") { dialog, which ->
                //
            }
            snackBuilder.setNegativeButton("Cancel") { dialog, which ->
                //
            }
            val dialog: AlertDialog = snackBuilder.create()
            dialog.show()
        }
    }
}