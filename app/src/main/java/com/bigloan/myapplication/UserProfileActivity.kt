package com.bigloan.myapplication

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bigloan.myapplication.module.Session
import com.bigloan.myapplication.module.User
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class UserProfileActivity : AppCompatActivity() {

    private lateinit var session: Session

    private lateinit var nic: TextView
    private lateinit var dob: TextView
    private lateinit var username: TextView
    private lateinit var email: TextView
    private lateinit var phonenumber: TextView
    private lateinit var address: TextView

    private lateinit var editBtnAct: Button
    private lateinit var logoutBtnAct: Button
    private lateinit var back: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_user_profile)

        nic = findViewById(R.id.nic)
        dob = findViewById(R.id.dob)
        username = findViewById(R.id.username)
        email = findViewById(R.id.email)
        phonenumber = findViewById(R.id.phone_number)
        address = findViewById(R.id.address)

        editBtnAct = findViewById(R.id.editBtn)
        logoutBtnAct = findViewById(R.id.logoutBtn)
        back = findViewById(R.id.back)

        // get session data
//        val emailAct: String = session.getSession().toString().split(",")[3].split(":")[1]

        // get data from intent
//        val intent = intent
//        val emailAct = intent.getStringExtra("email")
        val auth = Firebase.auth
        auth.addAuthStateListener { a ->
            if (a.currentUser == null) {
                // Create an Intent to navigate to the second activity
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
            else{
                val db = Firebase.firestore
                val docRef = db.collection("users")
                    .document(a.currentUser?.uid.toString())
                docRef.get()
                    .addOnSuccessListener { document ->
                        if (document != null) {
                            Log.d(ContentValues.TAG, "DocumentSnapshot data: ${document.data}, ${document.data?.get("name").toString()}")
                            username.text  = document.data?.get("name").toString()
                            email.text = document.data?.get("email").toString()
                            phonenumber.text = document.data?.get("mobile").toString()
                            address.text = document.data?.get("address").toString()
                            dob.text = document.data?.get("dob").toString()
                            nic.text = document.data?.get("nic").toString()
                        } else {
                            Log.d(ContentValues.TAG, "No such document")
                        }
                    }
                    .addOnFailureListener { exception ->
                        Log.w(ContentValues.TAG, "Error getting documents: ", exception)
                    }
            }
        }


        // set user data

        // Set the OnClickListener for the edit button
        editBtnAct.setOnClickListener(this::editProfile)
        logoutBtnAct.setOnClickListener(this::logout)
        back.setOnClickListener(this::backFunc)

    }

    private fun editProfile(view: View) {
        // Create an Intent to navigate to the second activity
        //add bundle
        val bundle = Bundle()
        bundle.putBoolean("isEdit", true)
        val intent = Intent(this, RegisterActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    private fun logout(view: View) {
        // Create an Intent to navigate to the second activity
        val auth = Firebase.auth
        auth.signOut()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
    private fun backFunc(view: View) {
        // Create an Intent to navigate to the second activity
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
