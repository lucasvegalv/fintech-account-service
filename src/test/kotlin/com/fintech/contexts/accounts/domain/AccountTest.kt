package com.fintech.contexts.accounts.domain

import com.fintech.contexts.customers.domain.CustomerId
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.math.BigDecimal
import java.util.*

class AccountTest {

    private val customerId: CustomerId = CustomerId.generate()
    private val account: Account = Account (currency = Currency.USD, holder = customerId)

    @Test
    fun `should be created with an account number of twelve digits`() {
        val account = account
        assertTrue(account.hasAValidAccountNumber())
    }

    @Test
    fun `should be created with a UUID that starts with 'acc'`() {
        val account = account
        assertTrue(account.hasValidUUID())
    }


    @Test
    fun `should have no transactions when just created`() {
        val account = account
        assertFalse(account.hasTransactions())
    }

    @Test
    fun `should increment balance if transaction is a deposit, refund or reversal`() {
        val account = account
        val transaction = Transaction(
            amount = BigDecimal(100.00),
            currency = Currency.USD,
            type = TransactionType.DEPOSIT,
        )
        val activeState = ActiveState()
        account.changeStateTo(activeState)

        account.state.addTransaction(account, transaction)
        assertTrue(account.hasThisBalance(100.0))
    }

    @Test
    fun `should decrement balance if transaction is a withdrawal, purchase or fee`() {
        val account = account
        val deposit = Transaction(
            amount = BigDecimal(100.00),
            currency = Currency.USD,
            type = TransactionType.DEPOSIT,
        )
        val withdrawal = Transaction(
            amount = BigDecimal(25.00),
            currency = Currency.USD,
            type = TransactionType.WITHDRAWAL,
        )
        val activeState = ActiveState()
        account.changeStateTo(activeState)
        account.state.addTransaction(account, deposit)
        assertTrue(account.hasThisBalance(100.0))
        account.state.addTransaction(account, withdrawal)
        assertTrue(account.hasThisBalance(75.0))
    }

    @Test
    fun `should never be a negative transaction amount`() {
        val account = account
        val deposit = Transaction(
            amount = BigDecimal(50.00),
            currency = Currency.USD,
            type = TransactionType.DEPOSIT,
        )
        val activeState = ActiveState()
        account.changeStateTo(activeState)
        account.state.addTransaction(account, deposit)

        val withdrawal = Transaction(
            amount = BigDecimal(100.00),
            currency = Currency.USD,
            type = TransactionType.WITHDRAWAL,
        )

        val exception = assertThrows(IllegalStateException::class.java) {
            account.state.addTransaction(account, withdrawal)
        }

        assertEquals(exception.message, "Balance can not be a negative amount")
    }

    @Test
    fun `should be initialize with PENDING_APPROVAL state`() {
        val account = account
        assertTrue(account.isPendingApprove())
    }

    @Test
    fun `should change to ACTIVE when is PENDING_APPROVAL`() {
        val account = account
        val activeState = ActiveState()
        account.changeStateTo(activeState)

        assertTrue(account.isActive())
    }

    @Test
    fun `should change to CLOSED when is PENDING_APPROVAL`() {
        val account = account
        val closeState = ClosedState()
        account.changeStateTo(closeState)

        assertTrue(account.isClosed())
    }

    @Test
    fun `should change to SUSPENDED when is ACTIVE`() {
        val account = account
        val activeState = ActiveState()
        val suspendedState = SuspendedState()
        account.changeStateTo(activeState)
        account.changeStateTo(suspendedState)

        assertTrue(account.isSuspended())
    }

    @Test
    fun `should change to BLOCKED when is ACTIVE`() {
        val account = account
        val activeState = ActiveState()
        val blockedState = BlockedState()
        account.changeStateTo(activeState)
        account.changeStateTo(blockedState)

        assertTrue(account.isBlocked())
    }

    @Test
    fun `should change to CLOSED when is ACTIVE`() {
        val account = account
        val activeState = ActiveState()
        val closedState = ClosedState()
        account.changeStateTo(activeState)
        account.changeStateTo(closedState)

        assertTrue(account.isClosed())
    }

    @Test
    fun `should change to ACTIVE when is SUSPENDED`() {
        val account = account
        val suspendedState = SuspendedState()
        val activeState = ActiveState()
        account.changeStateTo(suspendedState)
        account.changeStateTo(activeState)

        assertTrue(account.isActive())
    }

    @Test
    fun `should change to CLOSED when is SUSPENDED`() {
        val account = account
        val suspendedState = SuspendedState()
        val closedState = ClosedState()
        account.changeStateTo(suspendedState)
        account.changeStateTo(closedState)

        assertTrue(account.isClosed())
    }

    @Test
    fun `should change to BLOCKED when is SUSPENDED`() {
        val account = account
        val suspendedState = SuspendedState()
        val blockedState = BlockedState()
        account.changeStateTo(suspendedState)
        account.changeStateTo(blockedState)

        assertTrue(account.isBlocked())
    }

    @Test
    fun `should change to ACTIVE when is BLOCKED`() {
        val account = account
        val blockedState = BlockedState()
        val activeState = ActiveState()
        account.changeStateTo(blockedState)
        account.changeStateTo(activeState)

        assertTrue(account.isActive())
    }

    @Test
    fun `should change to SUSPENDED when is BLOCKED`() {
        val account = account
        val blockedState = BlockedState()
        val suspendedState = SuspendedState()
        account.changeStateTo(blockedState)
        account.changeStateTo(suspendedState)

        assertTrue(account.isSuspended())
    }

    @Test
    fun `should change to CLOSED when is BLOCKED`() {
        val account = account
        val blockedState = BlockedState()
        val closedState = ClosedState()
        account.changeStateTo(blockedState)
        account.changeStateTo(closedState)

        assertTrue(account.isClosed())
    }

    @Test
    fun `should not be allowed to change from CLOSED to any other state`() {
        val account = account
        val closedState = ClosedState()
        val activeState = ActiveState()
        account.changeStateTo(closedState)

        val exception = assertThrows(IllegalStateException::class.java) {
            account.changeStateTo(activeState)
        }

        assertEquals(exception.message, "Cannot change from CLOSED to any other state because it is a terminal state.")
    }

    @Test
    fun `should not be allowed to change from any state to PENDING_APPROVAL`() {
        val account = account
        val suspendedState = SuspendedState()
        val pendingApprovalState = PendingApprovalState()
        account.changeStateTo(suspendedState)

        val exception = assertThrows(IllegalArgumentException::class.java) {
            account.changeStateTo(pendingApprovalState)
        }

        assertEquals(exception.message, "Cannot change to PENDING_APPROVAL because it is just an initial state.")
    }

    @Test
    fun `should have no balance to change to CLOSED`() {
        val account = account
        val activeState = ActiveState()
        val closedState = ClosedState()
        val transaction = Transaction(
            amount = BigDecimal(100.00),
            currency = Currency.USD,
            type = TransactionType.DEPOSIT,
        )
        account.changeStateTo(activeState)
        account.state.addTransaction(account, transaction)

        val exception = assertThrows(IllegalStateException::class.java) {
            account.changeStateTo(closedState)
        }

        assertEquals(exception.message, "To change to CLOSED state, the balance must be zero.")
    }

    @Test
    fun `should be allowed to transact just when is ACTIVE` () {
        val account = account
        val suspendedState = SuspendedState()
        account.changeStateTo(suspendedState)
        val transaction = Transaction(
            amount = BigDecimal(100.00),
            currency = Currency.USD,
            type = TransactionType.DEPOSIT,
        )

        val exception = assertThrows(IllegalStateException::class.java) {
            account.state.addTransaction(account, transaction)
        }

        assertEquals(exception.message, "Tu cuenta esta suspendida. No tienes permitido transaccionar hasta que vuelva a estar activa.")
    }

    // TODO -> un customer solo puede tener una cuenta

    // TODO -> dos customers con distinto customerId deben tener cuentas con accountNumber diferente
}