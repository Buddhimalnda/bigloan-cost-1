package com.bigloan.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class MainActivity : AppCompatActivity() {

    private lateinit var disUser: TextView

    private lateinit var logoutBtn: Button
    private lateinit var groupBtn: Button
    private lateinit var loanBtn: Button
    private lateinit var subscriptionBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        disUser = findViewById(R.id.username)
        logoutBtn = findViewById(R.id.logoutBtn)
        groupBtn = findViewById(R.id.groupBtn)
        loanBtn = findViewById(R.id.loanBtn)

        subscriptionBtn = findViewById(R.id.subscriptionBtn)

        // get session data
        val auth = Firebase.auth
        val db = Firebase.firestore
        auth.addAuthStateListener { a ->
            if (a.currentUser == null) {
                // Create an Intent to navigate to the second activity
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            } else {
                db.collection("users").document(a.currentUser?.uid.toString()).get()
                    .addOnSuccessListener { document ->
                        if (document != null) {
                            disUser.text = document.data?.get("name").toString()
                        }
                    }
                    .addOnFailureListener { exception ->
                        disUser.text = "No data"
                    }
            }
        }

        logoutBtn.setOnClickListener {
            auth.signOut()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        groupBtn.setOnClickListener {
            val intent = Intent(this, GroupActivity::class.java)
            startActivity(intent)
        }

        loanBtn.setOnClickListener {
            val intent = Intent(this, LoanListActivity::class.java)
            startActivity(intent)
        }

        subscriptionBtn.setOnClickListener {
            val intent = Intent(this, SubscriptionList::class.java)
            startActivity(intent)
        }


    }
}