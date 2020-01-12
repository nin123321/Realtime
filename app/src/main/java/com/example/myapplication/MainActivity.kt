package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.view.plusAssign
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity : AppCompatActivity() {

    lateinit var inputCity: EditText
    lateinit var saveBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)





        inputCity = findViewById(R.id.inputCity)
        saveBtn = findViewById(R.id.saveBtn)


        saveBtn.setOnClickListener{

            val intent = Intent(this, Main2Activity::class.java)
            startActivity(intent)
            saveData()

            val myData = FirebaseDatabase.getInstance().getReference("Citys")
            val cityId = myData.push().key
            val input = inputCity.text.toString().trim()
            val citys = City(cityId, input)

            myData.setValue(citys)

            val cityss = arrayOf<String>()

            val listview = findViewById<ListView>(R.id.showCity)

            listview.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1)

            cityss.plusElement(input)




        }


    }

    private fun saveData() {

        val input = inputCity.text.toString().trim()

        if (input.isEmpty()){

            inputCity.error = "შეავსეთ გრაფა"
            return
        }

    }
}
