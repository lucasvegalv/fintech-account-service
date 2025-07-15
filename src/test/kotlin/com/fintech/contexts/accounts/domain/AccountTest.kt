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

        account.addTransaction(transaction)
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

        account.addTransaction(deposit)
        assertTrue(account.hasThisBalance(100.0))
        account.addTransaction(withdrawal)
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
        account.addTransaction(deposit)

        val withdrawal = Transaction(
            amount = BigDecimal(100.00),
            currency = Currency.USD,
            type = TransactionType.WITHDRAWAL,
        )

        val exception = assertThrows(IllegalStateException::class.java) {
            account.addTransaction(withdrawal)
        }

        assertEquals(exception.message, "Balance can not be a negative amount")
    }


    //    status (PENDING_APPROVAL, ACTIVE, SUSPENDED, BLOCKED, CLOSED)
    // TODO -> validar los posibles state-transitions

        // PENDING_APPROVAL -> ACTIVE
        // PENDING_APPROVAL -> CLOSED

        // ACTIVE -> SUSPENDED
        // ACTIVE -> BLOCKED
        // ACTIVE -> CLOSED

        // SUSPENDED -> ACTIVE
        // SUSPENDED -> BLOCKED
        // SUSPENDED -> CLOSED

        // BLOCKED -> ACTIVE
        // BLOCKED -> SUSPENDED
        // BLOCKED -> CLOSED

        // CLOSED -> * (a nada, es estado final)

    // TODO -> para pasar a CLOSED debe tener balance 0

    // TODO -> un customer solo puede tener una cuenta

    // TODO -> dos customers con distinto customerId deben tener cuentas con accountNumber diferente
}