package com.elijah.androidlearning.datapersist

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.elijah.androidlearning.R
import kotlinx.android.synthetic.main.activity_data_persist.*
import java.io.*

class DataPersistActivity : AppCompatActivity() {

    private val listener = View.OnClickListener { v ->
        when (v.id) {
            R.id.saveDataToFile -> saveToFile()
            R.id.loadFromFile -> loadFromFile()
            R.id.saveDataToSP -> saveToSP()
            R.id.loadFromSP -> loadFormSP()
            R.id.createDatabase -> createDatabase()
            //R.id.addData -> addDataToDB()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_persist)
        findViewById<View>(R.id.saveDataToFile).setOnClickListener(listener)
        findViewById<View>(R.id.loadFromFile).setOnClickListener(listener)
        findViewById<View>(R.id.saveDataToSP).setOnClickListener(listener)
        findViewById<View>(R.id.loadFromSP).setOnClickListener(listener)
        findViewById<View>(R.id.createDatabase).setOnClickListener(listener)
        findViewById<View>(R.id.addData).setOnClickListener(listener)
    }

    override fun onDestroy() {
        super.onDestroy()
    }


    private fun saveToFile() {
        try {
            val inputText = editText.text.toString()
            val output = openFileOutput("data", Context.MODE_PRIVATE)
            val writer = BufferedWriter(OutputStreamWriter(output))
            //use函数，保证在Lambda表达式中的代码全部执行完之后自动将外层的流关闭，省去在写finally语句手动关闭流
            writer.use {
                it.write(inputText)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun loadFromFile() {
        val content = StringBuilder()
        try {
            val input = openFileInput("data")
            val reader = BufferedReader(InputStreamReader(input))
            reader.use {
                reader.forEachLine {
                    content.append(it)
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        val ret = content.toString()
        if (ret.isNotEmpty()) {
            editText.setText(ret)
            editText.setSelection(ret.length) //光标后移到文字末尾
            Toast.makeText(this, "Restoring succeeded", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveToSP() {
        val editor = getSharedPreferences("SpData", Context.MODE_PRIVATE).edit()
        editor.putString("name", "Tom")
        editor.putInt("age", 28)
        editor.putBoolean("married", false)
        editor.apply()
    }

    private fun loadFormSP() {
        val prefs = getSharedPreferences("SpData", Context.MODE_PRIVATE)
        val name = prefs.getString("name", "")
        val age = prefs.getInt("age", 0)
        val married = prefs.getBoolean("married", false)
        val content = StringBuilder().append(name).append(age.toString()).append(married.toString())
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show()
    }

    private fun createDatabase() {
        val dbHelper = MyDatabaseHelper(this, "BookStore.db", 1)
        //dbHelper.writableDatabase
    }

//    private fun adaddDataToDB() {
//        val db = dbH
//    }

    class MyDatabaseHelper(private val context: Context, name: String, version: Int) :
        SQLiteOpenHelper(context, name, null, version) {

        private val createBook = "create table Book ( " +
                "id integer primary key autoincrement," +
                "author text," +
                "price real," +
                "pages integer," +
                "name text)"

        override fun onCreate(db: SQLiteDatabase) {
            db.execSQL(createBook)
            Toast.makeText(context, "Create succeeded", Toast.LENGTH_SHORT).show()
        }

        override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
            TODO("Not yet implemented")
        }
    }
}