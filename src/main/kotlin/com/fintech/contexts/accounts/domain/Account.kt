package com.fintech.contexts.accounts.domain

import com.fintech.contexts.customers.domain.CustomerId
import java.util.Currency

class Account (
    val currency: Currency,
    val holder: CustomerId
){
    val accountId = AccountId.generate()
    private val transactions: MutableList<Transaction> = mutableListOf()
}
