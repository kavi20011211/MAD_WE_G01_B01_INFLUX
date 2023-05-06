package com.example.influxapp

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.influxapp.database.models.ConfidentialDataModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ConfidentialDataPage : AppCompatActivity() {
    private lateinit var cardNumber:EditText
    private lateinit var cvvNumber:EditText
    private lateinit var bankName:EditText
    private lateinit var submitBtn:Button
    private lateinit var clearBtn:Button
    private lateinit var dbRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confidential_data_page)

//        val firebase:DatabaseReference = FirebaseDatabase.getInstance().getReference()

        cardNumber = findViewById(R.id.cardNumber)
        cvvNumber = findViewById(R.id.cvvNumber)
        bankName = findViewById(R.id.bankName)
        submitBtn = findViewById(R.id.submitBtnForConfidential)
        clearBtn = findViewById(R.id.clearBtnForConfidential)

//        dbRef = FirebaseDatabase.getInstance().getReference("ConfidentialData")  Database name

        submitBtn.setOnClickListener {
            if (TextUtils.isEmpty(cardNumber.text.toString())||TextUtils.isEmpty(cvvNumber.text.toString())||
                    TextUtils.isEmpty(bankName.text.toString())){
                cardNumber.error = "Please fill this field!"
                cvvNumber.error = "Please fill thi field!"
                bankName.error = "Please fill this field!"
                Toast.makeText(this@ConfidentialDataPage,"Enter Data To Required Fields!",Toast.LENGTH_SHORT).show()
            }else{
                getConfidentialData()
            }
        }
        clearBtn.setOnClickListener {
            cardNumber.text.clear()
            cvvNumber.text.clear()
            bankName.text.clear()
        }
    }

    private fun getConfidentialData() {
        val cardNum = cardNumber.text.toString()

        val dataID = cardNum.toString()

        val dataIDRef = Firebase.database.reference.child("ConfidentialData").child(dataID)
        dataIDRef.get().addOnCompleteListener {
            if (it.isSuccessful){
                val confidentialData = it.result.getValue(ConfidentialDataModel::class.java)
                Log.d(TAG,"${confidentialData?.dataID}/${confidentialData?.balance}")
                if (confidentialData?.dataID==null || confidentialData?.cardNumber == null){
                    Toast.makeText(this@ConfidentialDataPage,"Wrong Data!",Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this@ConfidentialDataPage,"Step Completed!",Toast.LENGTH_LONG).show()
                    val intent = Intent(this,DashboardActivity::class.java)
                    startActivity(intent)
                    finish()
                }

            }else{
                Log.d(TAG, "${it.exception?.message}")
                Toast.makeText(this@ConfidentialDataPage,"Something Went Wrong!",Toast.LENGTH_LONG).show()
            }
        }
    }

//    private fun saveConfidentialData(){
//        val cardNum = cardNumber.text.toString()
//        val cvvNum = cvvNumber.text.toString()
//        val bankName = bankName.text.toString()
//
//        val datID = cardNum.toString()
//
//        val dataConfidential = ConfidentialDataModel(datID,cardNum,cvvNum,bankName)
//
//        dbRef.child(datID).setValue(dataConfidential)
//            .addOnCompleteListener {
//                Toast.makeText(this@ConfidentialDataPage,"Step Completed Successfully!",Toast.LENGTH_LONG).show()
//                val intent = Intent(this,DashboardActivity::class.java)
//                startActivity(intent)
//                finish()
//            }.addOnFailureListener {err->
//                Toast.makeText(this@ConfidentialDataPage,"Something Went Wrong! Error${err.message}",Toast.LENGTH_LONG).show()
//            }
//    }
}