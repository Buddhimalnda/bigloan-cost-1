package com.bigloan.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class LoginActivity : AppCompatActivity() {

    private lateinit var inputEmail: EditText
    private lateinit var inputPassword: EditText

    private lateinit var login: Button
    private lateinit var register: Button

    private lateinit var checkBox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        inputEmail = findViewById<EditText>(R.id.username)
        inputPassword = findViewById<EditText>(R.id.password)

        checkBox = findViewById<CheckBox>(R.id.checkBox)

        login = findViewById<Button>(R.id.login_btn)
        register = findViewById<Button>(R.id.register)

        val auth = Firebase.auth
        auth.addAuthStateListener { a ->
            if (a.currentUser == null) {
                // Create an Intent to navigate to the second activity
                login.setOnClickListener(this::login)
                register.setOnClickListener(this::register)
            }
            else{
                // Create an Intent to navigate to the second activity
                val intent = Intent(this, MainActivity::class.java)
                val bundle = Bundle()
                bundle.putString("uid", a.currentUser?.uid.toString())
                startActivity(intent)
            }
        }
    }

    private fun register(view: View) {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    private fun login(view: View) {
        val auth = Firebase.auth
        val u = auth.signInWithEmailAndPassword(inputEmail.text.toString(), inputPassword.text.toString())
        u.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                val user = auth.currentUser
                // Create an Intent to navigate to the second activity
                val bundle = Bundle()
                bundle.putString("uid", auth.currentUser?.uid.toString())
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtras(bundle)
                startActivity(intent)
            } else {
                // If sign in fails, display a message to the user.
                // ...
            }
        }

    }
}