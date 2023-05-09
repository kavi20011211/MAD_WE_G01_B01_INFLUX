package com.example.influxapp

import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase

class Edit_income : AppCompatActivity() {


    private lateinit var cate: TextView
    private lateinit var typ: TextView
    private lateinit var amou: TextView
    private var recordId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_income)

        // Initialize the TextViews
        cate = findViewById(R.id.ecategory)
        typ = findViewById(R.id.eincometype)
        amou = findViewById(R.id.eamount)

        // Initialize the Buttons
        var submitButton = findViewById<Button>(R.id.esubmit)
        var cancelButton = findViewById<Button>(R.id.esubmit)

        // Get a reference to your Firebase database
        val database = FirebaseDatabase.getInstance().reference

        // Query the database to retrieve the last row of data
        database.child("income").orderByKey().limitToLast(1).get()
            .addOnSuccessListener { dataSnapshot ->
                // Update the values of that row with the new data
                // Get the first child node
                val lastChild = dataSnapshot.children.last()
                // Store the record ID as a class-level variable
                recordId = lastChild.key
                // Get the values of the child nodes and convert them to strings
                val category = lastChild.child("category").value?.toString()
                val incomeType = lastChild.child("incomeType").value?.toString()
                val amount = lastChild.child("amount").value?.toString()

                // Set the values of the TextViews
                cate.text = category
                typ.text = incomeType
                amou.text = amount

                // Set up the submit button onClick listener
                submitButton.setOnClickListener {
                    // Get the updated input values
                    // Get the updated input values
                    val category = cate.text.toString()
                    val incomeType = typ.text.toString()
                    val amount = amou.text.toString()

                    // Update the income record with the new values
                    lastChild.ref.updateChildren(
                        mapOf(
                            "amount" to amount,
                            "category" to category,
                            "amount" to amount,
                            "date" to incomeType
                        )
                    )
                    // Show a toast message indicating that the record was updated
                    Toast.makeText(this@Edit_income, "Record updated successfully", Toast.LENGTH_SHORT).show()
                    // Finish the activity and return to the previous screen
                    finish()
                }

            }.addOnFailureListener { exception ->
                // Handle any errors that occur
            }
    }
}
