package com.fintech.contexts.accounts.domain

import com.fintech.contexts.customers.domain.Address
import com.fintech.contexts.customers.domain.CountryCodes
import com.fintech.contexts.customers.domain.Customer
import com.fintech.contexts.customers.domain.CustomerId
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.math.BigDecimal
import java.util.*

class TransactionTest {

    private val customerAddress: Address = Address(
        countryCode = CountryCodes.ARG,
        city = "Buenos Aires",
        street = "Bonpland 2895",
        zipCode = "1405"
    )

    private val customer = Customer(
        name = "Lucas",
        lastname = "Vega",
        address = customerAddress,
        dni = "44324489",
        phone = "1158837671",
        email = "lucas@pomelo.la"
    )

    private val customerId: CustomerId = CustomerId.generate()

    val account = Account(
        currency = Currency.ARS,
        holder = customerId
    )


    @Test
    fun `should be created with a UUID that starts with 'txn'`() {
        val transaction = Transaction(
            amount = BigDecimal(50.00),
            currency = Currency.ARS,
            type = TransactionType.DEPOSIT
        )

        assertTrue(transaction.transactionId.value.startsWith("txn-"))
    }

    @Test
    fun `should be always a positive amount`() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            Transaction(
                    amount = BigDecimal(-50.00),
                    currency = Currency.ARS,
                    type = TransactionType.DEPOSIT
            )
        }

        assertEquals(exception.message, "Transaction amount must be positive or zero")
    }
}
