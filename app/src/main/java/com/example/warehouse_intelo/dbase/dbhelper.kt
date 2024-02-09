package dbase

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class dbhelper (private val context: Context) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    companion object {
        val DB_NAME = "Warehouse_"
        val DB_VERSION = 45
        val TABLE_DOCUMENTS = "Documents"
        val TABLE_CONTRACTORS = "Contractors"
        val COL_ID = "id"
        val COL_DATE = "date"
        val COL_SYMBOL = "symbol"
        val COL_CONTRACTOR = "contractor"
        val COL_CONTRACTOR_NAME = "contractor_name"
        val COL_CONTRACTOR_SURNAME = "contractor_surname"

        val COL_PRODUCT_NAME = "product_name"
        val COL_UNIT = "unit"
        val COL_QUANTITY = "quantity"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableDocuments = "CREATE TABLE $TABLE_DOCUMENTS (" +
                "$COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COL_DATE TEXT, " +
                "$COL_SYMBOL TEXT, " +
                "$COL_CONTRACTOR TEXT, " +
                "$COL_PRODUCT_NAME TEXT, " +
                "$COL_UNIT TEXT, " +
                "$COL_QUANTITY INTEGER)"
        db?.execSQL(createTableDocuments)

        val createTableContractors = "CREATE TABLE $TABLE_CONTRACTORS (" +
                "$COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COL_CONTRACTOR_NAME TEXT, " +
                "$COL_CONTRACTOR_SURNAME TEXT)"
        db?.execSQL(createTableContractors)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_DOCUMENTS")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_CONTRACTORS")

        onCreate(db)
    }
    @SuppressLint("Range")
    fun getAllDocuments(): ArrayList<String> {
        val documentsList = ArrayList<String>()
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_DOCUMENTS"
        val cursor: Cursor? = db.rawQuery(query, null)

        cursor?.let {
            val idIndex = cursor.getColumnIndex(COL_ID)
            val dateIndex = cursor.getColumnIndex(COL_DATE)
            val symbolIndex = cursor.getColumnIndex(COL_SYMBOL)
            val contractorIndex = cursor.getColumnIndex(COL_CONTRACTOR)
            val productNameIndex = cursor.getColumnIndex(COL_PRODUCT_NAME)
            val unitIndex = cursor.getColumnIndex(COL_UNIT)
            val quantityIndex = cursor.getColumnIndex(COL_QUANTITY)

            while (cursor.moveToNext()) {
                val id = cursor.getInt(idIndex)
                val date = cursor.getString(dateIndex)
                val symbol = cursor.getString(symbolIndex)
                val contractor = cursor.getString(contractorIndex)
                val productName = cursor.getString(productNameIndex)
                val unit = cursor.getString(unitIndex)
                val quantity = cursor.getInt(quantityIndex)

                val documentString = """
                |ID: $id
                |Date: ${date.toString()}
                |Symbol: $symbol
                |Contractor: $contractor
                |Product Name: $productName
                |Unit: $unit
                |Quantity: $quantity
            """.trimMargin()

                documentsList.add(documentString)
            }
        }
        cursor?.close()
        db.close()
        return documentsList
    }
    fun getAllContractors(): ArrayList<String> {
        val documentsList = ArrayList<String>()
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_CONTRACTORS"
        val cursor: Cursor? = db.rawQuery(query, null)

        cursor?.let {
            val idIndex = cursor.getColumnIndex(COL_ID)
            val nameIndex = cursor.getColumnIndex(COL_CONTRACTOR_NAME)
            val surnameIndex = cursor.getColumnIndex(COL_CONTRACTOR_SURNAME)


            while (cursor.moveToNext()) {
                val id = cursor.getInt(idIndex)
                val name = cursor.getString(nameIndex)
                val surname = cursor.getString(surnameIndex)
                val documentString = """
                |ID: $id
                |Name: $name
                |Surname: $surname
            """.trimMargin()

                documentsList.add(documentString)
            }
        }
        cursor?.close()
        db.close()
        return documentsList
    }
    fun addDocument(date: String, symbol: String, contractor: String, product_name: String, unit: String, quantity: String): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_DATE, date)
        values.put(COL_SYMBOL, symbol)
        values.put(COL_CONTRACTOR, contractor)
        values.put(COL_PRODUCT_NAME, product_name)
        values.put(COL_UNIT, unit)
        val result = db.insert(TABLE_DOCUMENTS, null, values)

        if (result == -1L) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }
        db.close()
        return result != -1L
    }

    fun addContractor(name: String, surname: String): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_CONTRACTOR_NAME, name)
        values.put(COL_CONTRACTOR_SURNAME, surname)

        val result = db.insert(TABLE_CONTRACTORS, null, values)

        if (result == -1L) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }
        db.close()
        return result != -1L
    }
}