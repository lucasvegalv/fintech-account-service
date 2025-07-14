package com.fintech.contexts.accounts.domain

import java.math.BigDecimal
import java.util.*

data class Transaction(
    val amount: BigDecimal,
    private val currency: Currency,
    private val type: TransactionType
) {
    val transactionId = TransactionId.generate()

    init{
        require(this.amount >= BigDecimal.ZERO) {"Transaction amount must be positive or zero"}
    }
    fun incrementsBalance(): Boolean = this.type in setOf(TransactionType.DEPOSIT, TransactionType.REFUND, TransactionType.REVERSAL)

}
