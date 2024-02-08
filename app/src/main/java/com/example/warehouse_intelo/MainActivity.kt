package com.example.warehouse_intelo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonDocuments = findViewById<Button>(R.id.button_documentsview)
        val buttonContractors = findViewById<Button>(R.id.button_contractorsview)

        buttonDocuments.setOnClickListener{
            val intent = Intent(this, DocumentsViewActivity::class.java)
            startActivity(intent);
            Toast.makeText(this, "Documents view", Toast.LENGTH_SHORT).show()
        }
        buttonContractors.setOnClickListener{
            val intent = Intent(this, ContractorsViewActivity::class.java)
            startActivity(intent);
            Toast.makeText(this, "Contractors view", Toast.LENGTH_SHORT).show()

        }
    }
}