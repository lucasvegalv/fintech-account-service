package com.fintech.contexts.accounts.domain

import com.fintech.contexts.customers.domain.CustomerId


class Account (
    val currency: Currency,
    val holder: CustomerId
){
    private val accountNumber: String = "123456789112"
    private val accountId = AccountId.generate()
    private val transactions: MutableList<Transaction> = mutableListOf()



    fun hasValidUUID(): Boolean = this.accountId.value.startsWith("acc")
    fun hasAValidAccountNumber(): Boolean = this.accountNumber.length == 12 && this.accountNumber.all { it.isDigit() }
    fun hasTransactions(): Boolean = this.transactions.isNotEmpty()
}
