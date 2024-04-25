package com.bigloan.myapplication.module

import android.app.PendingIntent
import android.content.Context
import java.io.*
import java.util.Date

class Session(
    sessionId: String,
    sessionType: String,
    sessionStatus: String,
    sessionDuration: Int,
    sessionDescription: String
) {
    private var sessionId: String? = sessionId
    private var sessionType: String? = sessionType
    private var sessionStatus: String? = sessionStatus
    private var sessionDuration: Int? = sessionDuration
    private var sessionDescription: String? = sessionDescription
    private var createAt: Date? = null

    init {
        this.createAt = Date()
    }

    fun createSession(context: Context) {
        // save in local file
        val file: String = "session.txt"
        val data: String =
            "sessionId: $sessionId, sessionType: $sessionType, sessionStatus: $sessionStatus, sessionDuration: $sessionDuration, sessionDescription: $sessionDescription, createAt: $createAt"
        try {
            val fileOutputStream = context.openFileOutput(file, Context.MODE_PRIVATE)
            fileOutputStream.write(data.toByteArray())
            fileOutputStream.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e2: IOException) {
            e2.printStackTrace()
        }
    }

    fun getSession(): Session? {
        // get from local file
        val file: String = "session.txt"
        val fileInputStream: FileInputStream
        var data: String = ""
        return try {
            fileInputStream = FileInputStream(file)
            data = fileInputStream.readBytes().toString(Charsets.UTF_8)
            val d: List<String> = data.split(",")
            this.sessionId = d.toString()[0].toString().split(":")[1]
            this.sessionType = d.toString()[1].toString().split(":")[1]
            this.sessionStatus = d.toString()[2].toString().split(":")[1]
            this.sessionDuration = d.toString()[3].toString().split(":")[1].toInt()
            this.sessionDescription = d.toString()[4].toString().split(":")[1]
            this.createAt = Date(d.toString()[5].toString().split(":")[1])
            this
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun destroySession() {
        // delete local file
        val file: String = "session.txt"
        val fileOutputStream: FileOutputStream
        try {
            fileOutputStream = FileOutputStream(file)
            fileOutputStream.write("".toByteArray())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getSessionId(): String? {
        return sessionId
    }

    fun setSessionId(sessionId: String) {
        this.sessionId = sessionId
    }

    fun getSessionType(): String? {
        return sessionType
    }

    fun setSessionType(sessionType: String) {
        this.sessionType = sessionType
    }

    fun getSessionStatus(): String? {
        return sessionStatus
    }

    fun setSessionStatus(sessionStatus: String) {
        this.sessionStatus = sessionStatus
    }

    fun getSessionDuration(): Int? {
        return sessionDuration
    }

    fun setSessionDuration(sessionDuration: Int) {
        this.sessionDuration = sessionDuration
    }

    fun getSessionDescription(): String? {
        return sessionDescription
    }

    fun setSessionDescription(sessionDescription: String) {
        this.sessionDescription = sessionDescription
    }

    fun getCreateAt(): Date? {
        return createAt
    }

    fun setCreateAt(createAt: Date) {
        this.createAt = createAt
    }


}