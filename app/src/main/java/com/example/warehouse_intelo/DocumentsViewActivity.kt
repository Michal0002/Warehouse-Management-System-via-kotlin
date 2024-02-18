package com.example.warehouse_intelo

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dbase.dbhelper

 class DocumentsViewActivity : AppCompatActivity() {
     lateinit var symbol: String
      lateinit var contractor: String
      lateinit var productName: String
      lateinit var unit: String

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_documentsviewactivity)

        val dbHelper = dbhelper(this)
        val documentsList = findViewById<ListView>(R.id.listView_AllDocuments)
        val documents = dbHelper.getAllDocuments()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, documents)
        documentsList.adapter = adapter

        val buttonAddNewDocument = findViewById<Button>(R.id.button_addDocument2)
        buttonAddNewDocument.setOnClickListener {
            val intent = Intent(this, AddNewDocumentActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "Add", Toast.LENGTH_SHORT).show()
        }

        val buttonGoBackMA = findViewById<Button>(R.id.button_goBack2)
        buttonGoBackMA.setOnClickListener{
            finish()
        }

        documentsList.setOnItemClickListener { parent, view, position, id ->
            val selectedDocument = documents[position]
            Toast.makeText(this, "Edit ${selectedDocument}", Toast.LENGTH_SHORT).show()
        }
    }
}
