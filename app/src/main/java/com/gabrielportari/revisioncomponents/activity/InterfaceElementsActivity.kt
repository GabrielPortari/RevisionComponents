package com.gabrielportari.revisioncomponents.activity

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.gabrielportari.revisioncomponents.R
import com.gabrielportari.revisioncomponents.databinding.ActivityInterfaceElementsBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
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

        /* este button altera o texto do TextView com o texto do EditText */
        binding.buttonUpdateText.setOnClickListener {
            val text = binding.editTextInsert.text.toString()
            binding.textMyText.text = text
        }

        /* este button abre um toast */
        binding.buttonToast.setOnClickListener{
            Toast.makeText(this, "Showing a toast", Toast.LENGTH_SHORT).show()
        }

        /* este button abre um snackbar */
        binding.buttonSnackbar.setOnClickListener {
            Snackbar.make(it, "Showing a snackbar", Snackbar.LENGTH_SHORT).show()
        }

        /* este button cria e abre um dialog */
        binding.buttonDialog.setOnClickListener {
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

        /*bottom sheet*/
        binding.buttonBottomSheet.setOnClickListener {
            showBottomSheet()
        }

    }

    /* função para criar um bottom sheet */
    private fun showBottomSheet(){
        val bottomSheetDialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.bottom_sheet_layout, null)

        val buttonCloseBottomSheet = view.findViewById<Button>(R.id.button_close_dialog)
        buttonCloseBottomSheet.setOnClickListener {
            bottomSheetDialog.dismiss()
        }
        bottomSheetDialog.setCancelable(false)
        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()
    }
}