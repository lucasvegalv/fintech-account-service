package com.fintech.contexts.accounts.domain

class ActiveState : AccountState {

    override fun addTransaction(account: Account, transaction: Transaction) {
        account.addTransaction(transaction)
    }
}
