package com.fintech.contexts.accounts.domain

import com.fintech.contexts.customers.domain.CustomerId
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.util.*

class AccountTest {

    @Test
    fun `should be created with an account number of twelve digits`() {
        val customerIdForTests = CustomerId.generate()
        val account = Account(
            currency = Currency.USD,
            holder = customerIdForTests
        )

        assertTrue(account.hasAValidAccountNumber())
    }

    @Test
    fun `should be created with a UUID that starts with 'acc'`() {
        val customerIdForTests = CustomerId.generate()
        val account = Account(
            currency = Currency.USD,
            holder = customerIdForTests
        )

        assertTrue(account.hasValidUUID())
    }

    //    transactions
    // TODO -> que al crearse la cuenta tenga un listado de 0 transacciones
    @Test
    fun `should have no transactions when just created`() {
        val customerIdForTests = CustomerId.generate()
        val account = Account(
            currency = Currency.USD,
            holder = customerIdForTests
        )

        assertFalse(account.hasTransactions())
    }

    // TODO -> si la transaccion es de tipo DEPOSIT, REFUND o REVERSAL, el balance debe incrementarse

    // TODO -> si la transaccion es de tipo WITHDRAWAL, PURCHASE, FEE, el balance debe reducirse

    // TODO -> nunca puede ser con un monto negativo

    //    balance
    // TODO -> al momento de crearse debe ser 0

    // TODO -> nunca puede ser un monto negativo



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

    // TODO -> que se cree correctamente si esta completo (se ingresan todos los datos)
}