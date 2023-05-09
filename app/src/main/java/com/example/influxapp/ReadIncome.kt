package com.example.influxapp

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import android.content.Intent
import com.example.influxapp.Edit_income


class ReadIncome : AppCompatActivity() {

    private lateinit var cate: TextView
    private lateinit var typ: TextView
    private lateinit var amou: TextView
    private var recordId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_income)

        cate = findViewById(R.id.r_category)
        typ = findViewById(R.id.r_type)
        amou = findViewById(R.id.r_amount)

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("income").limitToLast(1)

        myRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val lastchild = dataSnapshot.children.last() // get the first child node
                recordId = lastchild.key // store the record ID as a class-level variable
                val category = lastchild.child("category").value?.toString()
                val incomeType = lastchild.child("incomeType").value?.toString()
                val amount = lastchild.child("amount").value?.toString()

                cate.text = category
                typ.text = incomeType
                amou.text = amount
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })


        val deleteButton: Button = findViewById(R.id.delete_btn)
        deleteButton.setOnClickListener {
            val databaseReference = FirebaseDatabase.getInstance().getReference("income")
            val recordReference = databaseReference.child(recordId ?: "")

            Log.d("DeleteIncome", "Deleting record with ID: $recordId")

            // Remove the record from Firebase
            recordReference.removeValue()

            val intent = Intent(this@ReadIncome, ReadIncome::class.java)

        }

        val editButton: Button = findViewById(R.id.edit_btn)
        editButton.setOnClickListener {
            val intent = Intent(this@ReadIncome, Edit_income::class.java)
            intent.putExtra("recordId", recordId)
            startActivity(intent)
        }

    }
}
