package com.example.warehouse_intelo

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dbase.dbhelper
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AddNewDocumentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addnewdocument)
//        (date: String, symbol: String, contractor: String, product_name: String, unit: String, quantity: Int): Boolean {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val currentDate = dateFormat.format(Date())

        val dbHelper = dbhelper(this)
        val contractors = dbHelper.contractorsList()

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, contractors)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val contractorSpinner = findViewById<Spinner>(R.id.spinner_contractorList)
        contractorSpinner.adapter = adapter

        val buttonAddNewDocument = findViewById<Button>(R.id.button_addDocument)
        buttonAddNewDocument.setOnClickListener{
            val symbol = findViewById<EditText>(R.id.editTextText_symbol)
            val symbolText = symbol.text.toString()
            val contractor = findViewById<Spinner>(R.id.spinner_contractorList)
            val contractorText = contractor.selectedItem.toString()
            val productName = findViewById<EditText>(R.id.editTextText_productName)
            val productNameText = productName.text.toString()
            val unit = findViewById<EditText>(R.id.editTextText_unit)
            val unitText = unit.text.toString()
            val quantity = findViewById<EditText>(R.id.editTextNumber_quantity)
            val quantityText = quantity.text.toString()

            if (symbolText.isEmpty() || productNameText.isEmpty() || unitText.isEmpty() || quantityText.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show()
            } else {
                try {
                    val quantityInt = quantityText.toInt()
                    if (quantityInt <= 0) {
                        Toast.makeText(this, "Quantity must be greater than 0.", Toast.LENGTH_SHORT).show()
                    } else {
                        val dbHelper = dbhelper(this)
                        val newDocument = dbHelper.addDocument(currentDate, symbolText, contractorText, productNameText, unitText, quantityInt)

                        if (newDocument) {
                            Toast.makeText(this, "New document has been added successfully: $productNameText", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, "Something went wrong.", Toast.LENGTH_SHORT).show()
                        }
                    }
                } catch (e: NumberFormatException) {
                    Toast.makeText(this, "Invalid quantity.", Toast.LENGTH_SHORT).show()
                }
            }
        }
        val buttonGoBackMA = findViewById<Button>(R.id.button_goBack)
        buttonGoBackMA.setOnClickListener{
            finish()
        }

    }
}