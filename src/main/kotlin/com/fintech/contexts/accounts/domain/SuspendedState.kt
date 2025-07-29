package com.fintech.contexts.accounts.domain

class SuspendedState : AccountState {
    override fun addTransaction(account: Account, transaction: Transaction) {
        throw IllegalStateException("Tu cuenta esta suspendida. No tienes permitido transaccionar hasta que vuelva a estar activa.")
    }

}
