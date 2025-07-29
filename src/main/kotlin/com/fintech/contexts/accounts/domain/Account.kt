package com.fintech.contexts.accounts.domain

import com.fintech.contexts.customers.domain.CustomerId
import java.math.BigDecimal
import org.slf4j.LoggerFactory
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

class Account (
    val currency: Currency,
    val holder: CustomerId
){
     var state: AccountState = PendingApprovalState()
     var balance: BigDecimal = BigDecimal.ZERO
    private val accountNumber: String = "123456789112"
    private val accountId = AccountId.generate()
    private val transactions: MutableList<Transaction> = mutableListOf()



    fun hasValidUUID(): Boolean = this.accountId.value.startsWith("acc")
    fun hasAValidAccountNumber(): Boolean = this.accountNumber.length == 12 && this.accountNumber.all { it.isDigit() }
    fun hasTransactions(): Boolean = this.transactions.isNotEmpty()
    fun hasThisBalance(balanceToCompareWith: Double): Boolean = this.balance.compareTo(BigDecimal.valueOf(balanceToCompareWith)) == 0


    // TODO: Refactor this repetitive code
    fun isPendingApprove(): Boolean = this.state is PendingApprovalState
    fun isActive(): Boolean = this.state is ActiveState
    fun isClosed(): Boolean = this.state is ClosedState
    fun isSuspended(): Boolean = this.state is SuspendedState
    fun isBlocked(): Boolean = this.state is BlockedState

    fun changeStateTo(newState: AccountState) {
        require(newState !is PendingApprovalState) {"Cannot change to PENDING_APPROVAL because it is just an initial state."}
        check(this.state !is ClosedState) {"Cannot change from CLOSED to any other state because it is a terminal state."}
        if (newState is ClosedState) {
            check(this.balance.compareTo(BigDecimal.ZERO) == 0) { "To change to CLOSED state, the balance must be zero." }
        }
        this.state = newState
    }

    internal fun incrementBalance(amount: BigDecimal) {
        logger.info { "Balance for account #$accountId before increase is $$balance" }
        this.balance += amount
        logger.info { "Incrementing balance for account #$accountId by $$amount" }
        logger.info { "Current balance for account #$accountId is $$balance" }

    }

    internal fun reduceBalance(amount: BigDecimal) {
        val potentialNewBalance = this.balance.subtract(amount)
        check(potentialNewBalance.compareTo(BigDecimal.ZERO) >= 0) { "Balance can not be a negative amount" }
        logger.info { "Balance for account #$accountId before subtract is $$balance" }
        this.balance = potentialNewBalance
        logger.info { "Subtracting balance for account #$accountId by $$amount" }
        logger.info { "Current balance for account #$accountId is $$balance" }
    }

    internal fun addTransaction(transaction: Transaction) {
        if(transaction.incrementsBalance()) {
            this.incrementBalance(transaction.amount)
        } else {
            this.reduceBalance(transaction.amount)
        }

        this.transactions.add(transaction)
        logger.info { "Added transaction #${transaction.transactionId} for account #$accountId" }
    }
}

