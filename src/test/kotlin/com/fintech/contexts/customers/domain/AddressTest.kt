package com.fintech.contexts.customers.domain

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class AddressTest {

    @Test
    fun `should create an address if it has all the required values`() {
        val address = Address(
            countryCode = CountryCodes.ARG,
            city = "Buenos Aires",
            street = "Calle Ejemplo 2356",
            zipCode = "1040"
        )

        assertNotNull(address)
    }

    // TODO: Should reject address creation if country code field is blank

    // TODO: Should reject address creation if city field is blank

    // TODO: Should reject address creation if street is blank

    // TODO: Should reject address creation if city field is blank

    // TODO: Should reject address creation if city field has numbers

    // TODO: Should reject address creation if zipCode field is blank

    // TODO: Should reject address creation if city field has characters

}