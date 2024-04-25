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

class SubscriptionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_subscription)

        // Get the user inputs from the previous activity
        val subscriptionType = intent.getStringExtra("subscriptionType")

        // Display the user inputs
        val subscriptionTypeTextView = findViewById<TextView>(R.id.disType)

        subscriptionTypeTextView.text = subscriptionType

        val btn = findViewById<Button>(R.id.button2)
        btn.setOnClickListener {
            val x: AlertMzg<String> = AlertMzg("Subscription", "You have successfully subscribed to $subscriptionType subscription.", "OK")
            x.showToster("You have successfully subscribed to $subscriptionType subscription.", this)
            // Create an Intent to navigate to the second activity
             val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
        }

    }
}