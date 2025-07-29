package com.fintech.contexts.accounts.domain

interface AccountState {

    // por el momento es la unica operacion que depende del estado de la cuenta
    fun addTransaction(account: Account, transaction: Transaction)
}
