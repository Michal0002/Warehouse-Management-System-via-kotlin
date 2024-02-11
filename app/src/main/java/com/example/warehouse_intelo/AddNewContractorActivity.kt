package com.example.warehouse_intelo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dbase.dbhelper

class AddNewContractorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addnewcontractor)

        val buttonAddNewContractor = findViewById<Button>(R.id.button_addNewCont)

        buttonAddNewContractor.setOnClickListener{
            val contName = findViewById<EditText>(R.id.editTextText_contName)
            val contNameText = contName.text.toString()
            val contSurname = findViewById<EditText>(R.id.editTextText_contSurname)
            val contSurnameText = contSurname.text.toString()


            val dbHelper = dbhelper(this)
            val newContractor = dbHelper.addContractor( contNameText,  contSurnameText)

            if(newContractor){
                Toast.makeText(this, "New contractor has been added successfully:  $contNameText", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Something went wrong.", Toast.LENGTH_SHORT).show()
            }
            val buttonGoBackMA = findViewById<Button>(R.id.button_goBack4)
            buttonGoBackMA.setOnClickListener{
                finish()
            }

        }
    }
}