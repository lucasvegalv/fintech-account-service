package com.fintech.contexts.accounts.domain

import com.fintech.contexts.customers.domain.CustomerId
import java.math.BigDecimal


class Account (
    val currency: Currency,
    val holder: CustomerId
){
    private var state: AccountState = PendingApprovalState()
    private var balance: BigDecimal = BigDecimal.ZERO
    private val accountNumber: String = "123456789112"
    private val accountId = AccountId.generate()
    private val transactions: MutableList<Transaction> = mutableListOf()



    fun hasValidUUID(): Boolean = this.accountId.value.startsWith("acc")
    fun hasAValidAccountNumber(): Boolean = this.accountNumber.length == 12 && this.accountNumber.all { it.isDigit() }
    fun hasTransactions(): Boolean = this.transactions.isNotEmpty()

    fun addTransaction(transaction: Transaction) {
        check(this.state is ActiveState) {"Cannot transact if it is not ACTIVE"}
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
    fun changeStateTo(newState: AccountState) {
        require(newState !is PendingApprovalState) {"Cannot change to PENDING_APPROVAL because it is just an initial state."}
        check(this.state !is ClosedState) {"Cannot change from CLOSED to any other state because it is a terminal state."}
        if (newState is ClosedState) {
            check(this.balance.compareTo(BigDecimal.ZERO) == 0) { "To change to CLOSED state, the balance must be zero." }
        }
        this.state = newState
    }

    fun isPendingApprove(): Boolean = this.state is PendingApprovalState
    fun isActive(): Boolean = this.state is ActiveState
    fun isClosed(): Boolean = this.state is ClosedState
    fun isSuspended(): Boolean = this.state is SuspendedState
    fun isBlocked(): Boolean = this.state is BlockedState
}
