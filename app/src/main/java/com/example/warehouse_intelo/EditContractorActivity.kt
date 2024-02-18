package com.example.warehouse_intelo


import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class EditContractorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editcontractorview)
        var tekst = findViewById<EditText>(R.id.editTextText_conNameEditor)

        val name = intent.getStringExtra("name")

        tekst.setText(name)
    }
}