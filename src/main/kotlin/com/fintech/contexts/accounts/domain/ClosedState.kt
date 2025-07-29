package com.fintech.contexts.accounts.domain

class ClosedState : AccountState {
    override fun addTransaction(account: Account, transaction: Transaction) {
        throw IllegalStateException("Tu cuenta esta cerrada y no puede volver a estar activa ya que este es un estado terminal.")
    }

}
