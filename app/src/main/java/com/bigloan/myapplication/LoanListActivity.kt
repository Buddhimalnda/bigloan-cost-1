package com.bigloan.myapplication

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.bigloan.myapplication.module.Loan
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.Firebase
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.firestore
import javax.annotation.Nonnull


class LoanListActivity : AppCompatActivity() {

    private lateinit var listViewLoans: ListView
    private lateinit var btnAddLoan: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_loan_list)
// Initialize views
        listViewLoans = findViewById(R.id.listViewLoans)
        btnAddLoan = findViewById(R.id.btnAddLoan)

        // Set onClickListener for btnAddLoan
        btnAddLoan.setOnClickListener {
            // Navigate to MainActivity (Loan Registration UI)
            val intent = Intent(this, LoanRegisterActivity::class.java)
            startActivity(intent)
        }

        val db = Firebase.firestore
        db.collection("loans").get()
            .addOnCompleteListener { task ->
                val mMissionsList: MutableList<String> = ArrayList<String>()
                if (task.isSuccessful) {
                    for (document in task.result) {
                        mMissionsList.add(
                            "Name: ${document.data["loanName"]}\n" +
                                    "Membership ID: ${document.data["loanTo"]}\n" +
                                    "Loan Type: ${document.data["loanType"]}\n" +
                                    "Amount: ${document.data["loanAmount"]}"
                        )
                    }
                    val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mMissionsList)
                    listViewLoans.adapter = adapter

                } else {
                    Log.d("MissionActivity", "Error getting documents: ", task.exception)
                }
            }

        // go to loan profile
        listViewLoans.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, LoanProfileActivity::class.java)
            // Get the selected item
            val selectedItem = parent.getItemAtPosition(position) as String
            // Get the loan ID from the selected item
            val loanId = selectedItem.split("\n")[1].split(":")[1].trim()
            // Pass the loan ID to the LoanProfileActivity
            val bundle = Bundle()
            bundle.putString("loanId", loanId)
            intent.putExtras(bundle)
            startActivity(intent)
        }
//        db.collection("loans")
//            .get()
//            .addOnSuccessListener { result ->
//                for (document in result) {
//                    Log.d(ContentValues.TAG, "${document.id} => ${document.data}")
//                    loansList.add(
//                            "Name: ${document.data["loanName"]}\n" +
//                            "Membership ID: ${document.data["loanTo"]}\n" +
//                            "Loan Type: ${document.data["loanType"]}\n" +
//                            "Amount: ${document.data["loanAmount"]}")
//                }
//
//            }
//            .addOnFailureListener { exception ->
//                Log.w(ContentValues.TAG, "Error getting documents: ", exception)
//
//            }

        // Create an ArrayAdapter to populate the ListView


    }
}