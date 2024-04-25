package com.bigloan.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class LoanProfileActivity : AppCompatActivity() {

    private lateinit var LoanRegName: TextView
    private lateinit var LoanRegType: TextView
    private lateinit var LoanRegAmount: TextView
    private lateinit var LoanRegInterestRate: TextView
    private lateinit var nic: TextView
    private lateinit var LoanRegDuration: TextView
    private lateinit var LoanRegDate: TextView

    private lateinit var edit: Button
    private lateinit var back: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_loan_profile)

        LoanRegName = findViewById<TextView>(R.id.LoanName)
        LoanRegType = findViewById<TextView>(R.id.loanType)
        LoanRegAmount = findViewById<TextView>(R.id.loanAmount)
        LoanRegInterestRate = findViewById<TextView>(R.id.loanInterestRate)
        LoanRegDuration = findViewById<TextView>(R.id.loanDuration)
        LoanRegDate = findViewById<TextView>(R.id.loanDate)
        nic = findViewById<TextView>(R.id.nic)

        val db = Firebase.firestore
        val loanId = intent.getStringExtra("loanId")
        LoanRegName.text = loanId.toString()
        db.collection("loans").document(loanId.toString())
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    LoanRegName.text = document.data?.get("name").toString()
                    LoanRegType.text = document.data?.get("type").toString()
                    LoanRegAmount.text = document.data?.get("amount").toString()
                    LoanRegInterestRate.text = document.data?.get("interestRate").toString()
                    LoanRegDuration.text = document.data?.get("duration").toString()
                    LoanRegDate.text = document.data?.get("date").toString()
                }
            }
    back = findViewById<Button>(R.id.buttonBack)
    back.setOnClickListener {
        val intent = Intent(this, LoanListActivity::class.java)
        startActivity(intent)
    }


    }
}