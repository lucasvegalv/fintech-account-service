package com.fintech.contexts.accounts.domain

class PendingApprovalState : AccountState {
    override fun addTransaction(account: Account, transaction: Transaction) {
        throw IllegalStateException("Tu cuenta esta pendiente de aprobacion. Para poder transaccionar debe estar activa.")
    }

}
