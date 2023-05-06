package com.example.influxapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.influxapp.database.models.ConfidentialDataClass
import com.example.influxapp.database.models.ConfidentialDataModel

class DashboardActivity : AppCompatActivity() {

    private lateinit var cardNumberPrev:TextView
    private lateinit var achievementBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        val confiData = ConfidentialDataModel()
        cardNumberPrev = findViewById(R.id.cardNumberPrev)
        cardNumberPrev.text = confiData.cardNumber
        achievementBtn = findViewById(R.id.achievementBtn)

        achievementBtn.setOnClickListener {
            val intent = Intent(this,AchievmentActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}