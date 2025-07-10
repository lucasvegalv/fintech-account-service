package com.fintech.contexts.accounts.domain

import java.math.BigDecimal
import java.util.*

data class Transaction(
    private val amount: BigDecimal,
    private val currency: Currency,
    private val type: TransactionType
) {
    val transactionId = TransactionId.generate()
    init{
        require(this.amount >= BigDecimal(0.00)) {"Transaction amount must be positive or zero"}
    }
}
