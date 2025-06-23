package com.fintech.contexts.customers.domain

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class CustomerTest {

    @Test
    fun `should register a new customer just if all the required field were defined` () {
        // setup
        val customerLocation: Address = Address(
            countryCode = "ARG",
            city = "Buenos Aires",
            street = "Bonpland 2895",
            zipCode = "1405"
        )
        val customer: Customer = Customer(
            name = "Lucas",
            lastname = "Vega",
            address = customerLocation,
            dni = "44324489",
            phone = "1158837671",
            email = "lucas@pomelo.la"
        )

        // exercise & assert
        assertTrue(customer.hasThisDNI("44324489"))
    }


    // TODO: Should reject registration if Name field is invalid (not empty - just chars - max 50 )
    @Test


    // TODO: Should reject registration if Lastname field is invalid ( not empty - just chars - max 50 )

    // TODO: Should reject registration if DNI field is invalid (just numbers)

    // TODO: Should register a new customer with status ACTIVE

    // TODO: Should register a new customer with cero accounts related

    // TODO: Should desactivate a customer just if he/she is ACTIVE

    // TODO: Should reject desactivation if the customer has active accounts

    // TODO: Should reject registration if there's a user already registered with the same DNI
}
