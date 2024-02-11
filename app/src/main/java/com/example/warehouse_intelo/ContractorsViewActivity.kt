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

class ContractorsViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contractorsviewactivity)

        val dbHelper = dbhelper(this)
        val contractorsList = findViewById<ListView>(R.id.listView_AllContractorser)
        val documents = dbHelper.getAllContractors()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, documents)
        contractorsList.adapter = adapter

        val buttonAddNewContractor = findViewById<Button>(R.id.button_addContractor)
        buttonAddNewContractor.setOnClickListener {
            val intent = Intent(this, AddNewContractorActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "Contractors", Toast.LENGTH_SHORT).show()
        }
        val buttonGoBackMA = findViewById<Button>(R.id.button_goBack3)
        buttonGoBackMA.setOnClickListener{
            finish()
        }

    }
}