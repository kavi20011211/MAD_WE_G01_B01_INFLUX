package com.example.influxapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class GraphicalDashboard : AppCompatActivity() {
    private lateinit var graphBtn: Button
    private lateinit var listBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graphical_dashboard)

        graphBtn = findViewById(R.id.btn_gGraph)

        graphBtn.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        listBtn = findViewById(R.id.btn_gList)

        listBtn.setOnClickListener {
            val intent = Intent(this, GraphicalDashboard::class.java)
        }
    }
}