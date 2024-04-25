package com.bigloan.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bigloan.myapplication.module.AlertMzg
import com.bigloan.myapplication.module.Loan
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class LoanRegisterActivity : AppCompatActivity() {

    private lateinit var LoanRegName: EditText
    private lateinit var LoanRegId: EditText
    private lateinit var LoanRegType: EditText
    private lateinit var LoanRegAmount: EditText
    private lateinit var LoanRegInterestRate: EditText
    private lateinit var LoanRegStatus: EditText
    private lateinit var LoanRegDuration: EditText
    private lateinit var LoanRegDate: EditText
    private lateinit var checkBox2: CheckBox
    private lateinit var register: Button
    private lateinit var back: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_loan_register)

        LoanRegName = findViewById<EditText>(R.id.LoanRegName)
        LoanRegId = findViewById<EditText>(R.id.LoanRegId)
        LoanRegType = findViewById<EditText>(R.id.LoanRegType)
        LoanRegAmount = findViewById<EditText>(R.id.LoanRegAmount)
        LoanRegInterestRate = findViewById<EditText>(R.id.LoanRegInterestRate)
        LoanRegStatus = findViewById<EditText>(R.id.LoanRegStatus)
        LoanRegDuration = findViewById<EditText>(R.id.LoanRegDuration)
        LoanRegDate = findViewById<EditText>(R.id.LoanRegDate)

        checkBox2 = findViewById<CheckBox>(R.id.checkBox2)

        register = findViewById<Button>(R.id.register)
        register.setOnClickListener(this::createLoan)

        back = findViewById<Button>(R.id.back)
        back.setOnClickListener(this::back)


    }

    private fun createLoan(view: View){
        val db = Firebase.firestore
//        val loan = Loan(
//            LoanRegAmount.text.toString().toInt(),
//            LoanRegInterestRate.text.toString().toInt(),
//            LoanRegType.text.toString(),
//            LoanRegName.text.toString(),
//            LoanRegStatus.text.toString(),
//            LoanRegDuration.text.toString().toInt(),
//            LoanRegDate.text.toString(),
//            LoanRegId.text.toString(),
//        )

        db.collection("loans").add(
            hashMapOf(
                "loanAmount" to LoanRegAmount.text.toString().toInt(),
                "loanInterestRate" to LoanRegInterestRate.text.toString().toInt(),
                "loanType" to LoanRegType.text.toString(),
                "loanName" to LoanRegName.text.toString(),
                "loanStatus" to LoanRegStatus.text.toString(),
                "loanDuration" to LoanRegDuration.text.toString().toInt(),
                "loanDate" to LoanRegDate.text.toString(),
                "loanTo" to LoanRegId.text.toString(),
                "loanApproved" to checkBox2.isChecked
            )
        )
            .addOnSuccessListener {
                Log.d("LoanRegisterActivity", "DocumentSnapshot added with ID: ${it.id}")
                val x: AlertMzg<String> = AlertMzg("Loan", "Loan added successfully", "OK")
                x.showToster("Loan added successfully", this)
            }
            .addOnFailureListener {
                Log.w("LoanRegisterActivity", "Error adding document", it)

            }

    }

    private fun back(view: View){
        val intent = Intent(this, LoanListActivity::class.java)
        startActivity(intent)
    }
}