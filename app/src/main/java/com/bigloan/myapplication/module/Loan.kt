package com.bigloan.myapplication.module

import android.content.Context
import com.bigloan.myapplication.module.AlertMzg
import java.util.Date
import java.util.UUID

class Loan(
    loanAmount: Int,
    interestRate: Int,
    loanType: String,
    loanName: String,
    loanStatus: String,
    loanDuration: Int,
    loanDate: String,
    loanTo: String
) {
    private var loanAmount: Int? = loanAmount
    private var interestRate: Int? = interestRate
    private var loanId: String? = null
    private var loanName: String? = loanName
    private var loanType: String? = loanType
    private var loanStatus: String? = loanStatus
    private var loanDuration: Int? = loanDuration
    private var loanDate: String? = loanDate
    private var loanEndDate: String? = null
    private var loanFrom: String? = null
    private var loanTo: String? = loanTo
    private var createAt: Date? = null
    private var updateAt: List<Date>? = null

    private lateinit var session: Session


    init {
        this.loanId = UUID.randomUUID().toString()
        this.loanFrom = session.getSession()?.getSessionDescription().toString()
        this.createAt = Date()
        this.updateAt = listOf(Date())
    }



    fun getLoanAmount(): Int? {
        return loanAmount
    }

    fun setLoanAmount(loanAmount: Int) {
        this.loanAmount = loanAmount
    }

    fun getInterestRate(): Int? {
        return interestRate
    }

    fun setInterestRate(interestRate: Int) {
        this.interestRate = interestRate
    }

    fun getLoanId(): String? {
        return loanId
    }

    fun setLoanId(loanId: String) {
        this.loanId = loanId
    }

    fun getLoanType(): String? {
        return loanType
    }

    fun setLoanType(loanType: String) {
        this.loanType = loanType
    }

    fun getLoanStatus(): String? {
        return loanStatus
    }

    fun setLoanStatus(loanStatus: String) {
        this.loanStatus = loanStatus
    }

    fun getLoanDuration(): Int? {
        return loanDuration
    }

    fun setLoanDuration(loanDuration: Int) {
        this.loanDuration = loanDuration
    }

    fun getLoanDate(): String? {
        return loanDate
    }

    fun setLoanDate(loanDate: String) {
        this.loanDate = loanDate
    }

    fun getLoanEndDate(): String? {
        return loanEndDate
    }

    fun setLoanEndDate(loanEndDate: String) {
        this.loanEndDate = loanEndDate
    }

    fun getLoanFrom(): String? {
        return loanFrom
    }

    fun setLoanFrom(loanFrom: String) {
        this.loanFrom = loanFrom
    }

    fun getLoanTo(): String? {
        return loanTo
    }

    fun setLoanTo(loanTo: String) {
        this.loanTo = loanTo
    }

    fun getCreateAt(): Date? {
        return createAt
    }

    fun setCreateAt(createAt: Date) {
        this.createAt = createAt
    }

    fun getUpdateAt(): List<Date>? {
        return updateAt
    }

    fun setUpdateAt(updateAt: List<Date>) {
        this.updateAt = updateAt
    }

    fun getLoanName(): String? {
        return loanName
    }


}