package com.bigloan.myapplication.module

import android.content.Context
import android.widget.Toast

class AlertMzg<X> {
    private var title: String? = null
    private var message: String? = null
    private var next: X? = null

    constructor(title: String, message: String, next: X) {
        this.title = title
        this.message = message
        this.next = next
    }

    fun getTitle(): String? {
        return title
    }

    fun setTitle(title: String) {
        this.title = title
    }

    fun getMessage(): String? {
        return message
    }

    fun setMessage(message: String) {
        this.message = message
    }

    fun getNext(): X? {
        return next
    }

    fun setNext(next: X) {
        this.next = next
    }

    fun show() {
        println("{Title: $title},{Message: $message}")
    }
    fun showToster(message: String, context: Context) {
        val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
        toast.show()
    }
}