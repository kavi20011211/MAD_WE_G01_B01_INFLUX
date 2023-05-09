package com.example.influxapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var addBtn:Button
    private lateinit var updateBtn:Button
    private lateinit var graphBtn:Button
    private lateinit var listBtn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addBtn=findViewById(R.id.btn_Add)
        updateBtn = findViewById(R.id.btn_Edit)
        graphBtn = findViewById(R.id.btn_Graph)
        listBtn = findViewById(R.id.btn_List)

        addBtn.setOnClickListener {
            val intent = Intent(this,AddExpense::class.java)
            startActivity(intent)
            finish()
        }

        updateBtn.setOnClickListener {
            val intent = Intent(this,Edit::class.java)
            startActivity(intent)
            finish()
        }

        graphBtn.setOnClickListener {
            val intent = Intent(this,GraphicalDashboard::class.java)
            startActivity(intent)
            finish()
        }

        listBtn.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}