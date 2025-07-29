package com.fintech.contexts.accounts.domain

class BlockedState : AccountState {

    override fun addTransaction(account: Account, transaction: Transaction) {
        throw IllegalStateException("Tu cuenta esta bloqueada. No tienes permitido transaccionar hasta que vuelva a estar activa.")
    }

}
