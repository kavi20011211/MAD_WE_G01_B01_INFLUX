package com.example.influxapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.influxapp.database.models.ConfidentialDataClass
import com.example.influxapp.database.models.ConfidentialDataModel

class DashboardActivity : AppCompatActivity() {

    private lateinit var cardNumberPrev:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        val confiData = ConfidentialDataModel()
        cardNumberPrev = findViewById(R.id.cardNumberPrev)
        cardNumberPrev.text = confiData.cardNumber

    }
}