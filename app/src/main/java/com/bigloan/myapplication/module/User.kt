package com.bigloan.myapplication.module

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import com.bigloan.myapplication.module.AlertMzg
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore


class User {
    private var name: String? = null
    private var email: String? = null
    private var mobile: String? = null
    private var nic: String? = null
    private var password: String? = null
    private var address: String? = null
    private var city: String? = null
    private var state: String? = null
    private var dob: String? = null

    private var isLoaded: Boolean = false


    constructor(email: String, password: String) {
        this.email = email
        this.password = password
        // get user details from database

        val db = Firebase.firestore
        val auth = Firebase.auth
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    val y:User = User(user?.uid.toString())
                    AlertMzg<User?>("SUCCESS", "SUCCESS", y)
                } else {
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    AlertMzg<User?>("ERROR", "ERROR", null)
                }
            }

    }

    constructor(
        name: String, email: String, mobile: String, nic: String, password: String,
        address: String, city: String, state: String, dob: String,
    ) {
        this.name = name
        this.email = email
        this.mobile = mobile
        this.password = password
        this.address = address
        this.city = city
        this.state = state
        this.dob = dob
        this.nic = nic
    }

    constructor(uid: String) {

        // get user details from database
        val db = Firebase.firestore
        val docRef = db.collection("users")
            .document(uid)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d(TAG, "DocumentSnapshot data: ${document.data}, ${document.data?.get("name").toString()}")
                    this.name = document.data?.get("name").toString()
                    this.email = document.data?.get("email").toString()
                    this.mobile = document.data?.get("mobile").toString()
                    this.password = document.data?.get("password").toString()
                    this.address = document.data?.get("address").toString()
                    this.city = document.data?.get("city").toString()
                    this.state = document.data?.get("state").toString()
                    this.dob = document.data?.get("dob").toString()
                    this.nic = document.data?.get("nic").toString()
                    this.isLoaded = true
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }

    }


    fun getName(): String? {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getEmail(): String? {
        return email
    }

    fun setEmail(email: String) {
        this.email = email
    }

    fun getMobile(): String? {
        return mobile
    }

    fun setMobile(mobile: String) {
        this.mobile = mobile
    }

    fun getPassword(): String? {
        return password
    }

    fun setPassword(password: String) {
        this.password = password
    }

    fun getAddress(): String? {
        return address
    }

    fun setAddress(address: String) {
        this.address = address
    }

    fun getCity(): String? {
        return city
    }

    fun setCity(city: String) {
        this.city = city
    }

    fun getState(): String? {
        return state
    }

    fun setState(state: String) {
        this.state = state
    }


    fun getDob(): String? {
        return dob
    }

    fun setDob(dob: String) {
        this.dob = dob
    }

    fun getNic(): String? {
        return nic
    }

    fun setNic(nic: String) {
        this.nic = nic
    }

    fun login(email: String, password: String): User {
        val x: AlertMzg<User?>? = null
        if (x != null) {
            x.showToster(x.getMessage().toString(), this as Context)
        }
        val session: Session = Session("LOGIN", "login", "active", 60, "User login session")
        return x?.getNext() as User
    }


    fun register(): User? {
        // insert user details into database
        val x: AlertMzg<User?>? = null
        if (x != null) {
            x.showToster(x.getMessage().toString(), this as Context)
        }
        return x?.getNext()
    }

    fun update(): User? {
        // update user details into database
        val x: AlertMzg<User?>?= null
        if (x != null) {
            x.showToster(x.getMessage().toString(), this as Context)
        }
        return x?.getNext()
    }

    fun delete() {
        // delete user details from database
        val x: AlertMzg<Boolean>? =null
        if (x != null) {
            x.showToster(x.getMessage().toString(), this as Context)
        }
    }

    fun isLoaded(): Boolean {
        return isLoaded
    }

    fun setLoaded(loaded: Boolean) {
        isLoaded = loaded
    }


}
