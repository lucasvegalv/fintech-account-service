package com.fintech.contexts.accounts.domain

import com.fintech.contexts.customers.domain.CustomerId
import java.util.*

data class TransactionId(val value: String) {
    companion object {
        fun generate(): TransactionId {
            val id = UUID.randomUUID().toString()
            return TransactionId("txn-$id")
        }
    }
}
