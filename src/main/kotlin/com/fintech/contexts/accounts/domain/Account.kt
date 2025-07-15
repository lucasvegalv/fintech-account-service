package com.fintech.contexts.accounts.domain

import com.fintech.contexts.customers.domain.CustomerId
import java.math.BigDecimal
import java.math.BigInteger
import kotlin.reflect.typeOf


class Account (
    val currency: Currency,
    val holder: CustomerId
){
    private var balance: BigDecimal = BigDecimal.ZERO
    private val accountNumber: String = "123456789112"
    private val accountId = AccountId.generate()
    private val transactions: MutableList<Transaction> = mutableListOf()



    fun hasValidUUID(): Boolean = this.accountId.value.startsWith("acc")
    fun hasAValidAccountNumber(): Boolean = this.accountNumber.length == 12 && this.accountNumber.all { it.isDigit() }
    fun hasTransactions(): Boolean = this.transactions.isNotEmpty()

    fun addTransaction(transaction: Transaction) {
        if(transaction.incrementsBalance()) {
            this.balance += transaction.amount // Rompemos encapsulamiento con getAmount para no acoplar de responsabilidad innecesaria a Transaction.
        } else {
            val potentialNewBalance = this.balance.subtract(transaction.amount)
            check(potentialNewBalance >= BigDecimal.ZERO) { "Balance can not be a negative amount" }

            this.balance = potentialNewBalance
        }

        transactions.add(transaction)
    }

    fun hasThisBalance(balanceToCompareWith: Double): Boolean = this.balance.compareTo(BigDecimal.valueOf(balanceToCompareWith)) == 0
}
