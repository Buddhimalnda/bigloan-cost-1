package com.bigloan.myapplication

import android.content.ContentValues
import android.content.ContentValues.TAG
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
import com.bigloan.myapplication.module.User
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class RegisterActivity : AppCompatActivity() {
    private lateinit var inputName: EditText
    private lateinit var inputUsername: EditText
    private lateinit var inputEmail: EditText
    private lateinit var inputPhone: EditText
    private lateinit var inputNic: EditText
    private lateinit var inputDob: EditText
    private lateinit var inputAddress: EditText
    private lateinit var inputPassword: EditText
    private lateinit var inputConfirmPassword: EditText

    private lateinit var register: Button
    private lateinit var back: Button

    private lateinit var checkBox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        inputName = findViewById(R.id.fname)
        inputUsername = findViewById(R.id.uname)
        inputEmail = findViewById(R.id.email)
        inputPhone = findViewById(R.id.phone_number)
        inputNic = findViewById(R.id.nic_no)
        inputDob = findViewById(R.id.dob)
        inputAddress = findViewById(R.id.address)
        inputPassword = findViewById(R.id.pass)
        inputConfirmPassword = findViewById(R.id.repass)

        checkBox = findViewById(R.id.checkBox2)

        register = findViewById(R.id.register)
        back = findViewById(R.id.back)

        register.setOnClickListener(this::registerFun)
        back.setOnClickListener(this::back)

        //get intent data
        val intent = intent
        val isEdit = intent.getBooleanExtra("isEdit", false)

        if(isEdit){
            register.setOnClickListener(this::editProfile)
            val auth = Firebase.auth
            auth.addAuthStateListener { a ->
                if (a.currentUser == null) {
                    // Create an Intent to navigate to the second activity

                    val i = Intent(this, LoginActivity::class.java)
                    startActivity(i)
                }
                else{
                    val db = Firebase.firestore
                    val docRef = db.collection("users")
                        .document(a.currentUser?.uid.toString())
                    docRef.get()
                        .addOnSuccessListener { document ->
                            if (document != null) {
//                                Log.d(ContentValues.TAG, "DocumentSnapshot data: ${document.data}, ${document.data?.get("name").toString()}")
                                inputUsername.setText(document.data?.get("name").toString())
                                inputEmail.setText(document.data?.get("email").toString())
                                inputPhone.setText(document.data?.get("mobile").toString())
                                inputNic.setText(document.data?.get("nic").toString())
                                inputDob.setText(document.data?.get("dob").toString())
                                inputAddress.setText(document.data?.get("address").toString())
                                inputPassword.setText(document.data?.get("password").toString())

                            } else {
                                Log.d(ContentValues.TAG, "No such document")
                            }
                        }
                        .addOnFailureListener { exception ->
                            Log.w(ContentValues.TAG, "Error getting documents: ", exception)
                        }

                }
            }
        }
        else{
            register.setOnClickListener(this::registerFun)

        }

    }

    private fun  registerFun(view: View){
        val user: User = User(
            inputUsername.text.toString(),
            inputEmail.text.toString(),
            inputPhone.text.toString(),
            inputNic.text.toString(),
            inputPassword.text.toString(),
            inputAddress.text.toString(),
            "colombo",
            "ACTIVE",
            inputDob.text.toString()
        )

        val db = Firebase.firestore
        val auth = Firebase.auth
        var x:AlertMzg<User?>? = null
        if(checkBox.isChecked){
            auth.createUserWithEmailAndPassword(user.getEmail().toString(), user.getPassword().toString())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "createUserWithEmail:success")
                        db.collection("users")
                            .document(task.result?.user?.uid.toString())
                            .set(user)
                            .addOnSuccessListener { documentReference ->
                                x = AlertMzg("SUCCESS", "SUCCESS, \n$documentReference", user)
                            }
                            .addOnFailureListener { e ->
                                x = AlertMzg("ERROR", "ERROR, \n$e", user)
                            }
                    }
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "createUserWithEmail:failure", e)
                    x = AlertMzg("ERROR", "ERROR, \n$e", user)
                }

            if (x?.getTitle() == "ERROR") {
                x?.showToster("ERROR REGISTRATION", applicationContext)
            }
            x?.showToster("SUCCESS REGISTRATION", applicationContext)
            val gotoNextScreen = Intent(applicationContext, UserProfileActivity::class.java)
            val bundle = Bundle()
            bundle.putString("email", user.getEmail())
            bundle.putString("password", user.getPassword())
            gotoNextScreen.putExtras(bundle)
            startActivity(gotoNextScreen)
        }
    }

    private fun back(view: View){

    }

    private fun editProfile(view: View) {
        // Create an Intent to navigate to the second activity
        val db = Firebase.firestore
        val user: User = User(
            inputUsername.text.toString(),
            inputEmail.text.toString(),
            inputPhone.text.toString(),
            inputNic.text.toString(),
            inputPassword.text.toString(),
            inputAddress.text.toString(),
            "colombo",
            "ACTIVE",
            inputDob.text.toString()
        )
        db.collection("users")
            .document(Firebase.auth.currentUser?.uid.toString())
            .update(
                "name", user.getName(),
                "email", user.getEmail(),
                "mobile", user.getMobile(),
                "nic", user.getNic(),
                "password", user.getPassword(),
                "address", user.getAddress(),
                "city", user.getCity(),
                "state", user.getState(),
                "dob", user.getDob(),
                "status", user.getState()
            )
            .addOnSuccessListener { documentReference ->
                val user: User = User(Firebase.auth.currentUser?.uid.toString())
//                user.setName(inputName.text.toString())
//                user.setEmail(inputEmail.text.toString())
//                user.setMobile(inputPhone.text.toString())
//                user.setNic(inputNic.text.toString())
//                user.setAddress(inputAddress.text.toString())
//                user.setDob(inputDob.text.toString())
//                user.setPassword(inputPassword.text.toString())
                AlertMzg("SUCCESS", "SUCCESS, \n$documentReference", user)
                // Create an Intent to navigate to the second activity
                val intent = Intent(this, UserProfileActivity::class.java)
                startActivity(intent)
            }
            .addOnFailureListener { e ->
                AlertMzg("ERROR", "ERROR, \n$e", null)
            }

    }
}