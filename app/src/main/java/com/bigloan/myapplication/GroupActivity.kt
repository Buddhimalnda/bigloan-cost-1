package com.bigloan.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bigloan.myapplication.module.AlertMzg
import com.bigloan.myapplication.module.User
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class GroupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_group)

        val x: AlertMzg<String> = AlertMzg("Group", "Coming soon", "OK")

        x.showToster("Coming soon", this)

        val auth = Firebase.auth
        val db = Firebase.firestore

        var user: User? = null

        auth.addAuthStateListener { a ->
            if (a.currentUser == null) {
                // Create an Intent to navigate to the second activity
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
            else{
                db.collection("users").document(a.currentUser?.uid.toString())
                    .get()
                    .addOnSuccessListener { document ->
                        if (document != null) {
                            findViewById<TextView>(R.id.textView14).text = "Name: "+ document.data?.get("name").toString()
                            findViewById<TextView>(R.id.textView).text = "Email: "+ document.data?.get("email").toString()
                            findViewById<TextView>(R.id.textView16).text = "Phone: "+ document.data?.get("mobile").toString()
                            findViewById<TextView>(R.id.textView17).text = "Nic: "+ document.data?.get("nic").toString()
                        }
                    }
            }
        }

        findViewById<Button>(R.id.button3).setOnClickListener{
                val x: AlertMzg<String> = AlertMzg("Group", "Coming soon", "OK")
                x.showToster("Coming soon", this)
        }
        findViewById<Button>(R.id.button2).setOnClickListener{
                val x: AlertMzg<String> = AlertMzg("Group", "Coming soon", "OK")
                x.showToster("Coming soon", this)
        }
        findViewById<Button>(R.id.button4).setOnClickListener{
                val x: AlertMzg<String> = AlertMzg("Group", "Coming soon", "OK")
                x.showToster("Coming soon", this)
        }


    }
}