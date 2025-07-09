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

    @Test
    fun `should reject address creation if city field is blank`() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            val address = Address(
                countryCode = CountryCodes.ARG,
                city = "",
                street = "Calle Ejemplo 2356",
                zipCode = "1040"
            )
        }

        assertEquals(exception.message, "City can not be blank")
    }

    @Test
    fun `should reject address creation if street field is blank`() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            val address = Address(
                countryCode = CountryCodes.ARG,
                city = "Buenos Aires",
                street = "",
                zipCode = "1040"
            )
        }

        assertEquals(exception.message, "Street can not be blank")
    }

    @Test
    fun `should reject address creation if zip code field is blank`() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            val address = Address(
                countryCode = CountryCodes.ARG,
                city = "Buenos Aires",
                street = "Av. Corrientes",
                zipCode = ""
            )
        }

        assertEquals(exception.message, "Zip Code can not be blank")
    }

    @Test
    fun `should reject address creation if city field has numbers`() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            val address = Address(
                countryCode = CountryCodes.ARG,
                city = "Buenos Aires2025",
                street = "Av. Corrientes",
                zipCode = "1050"
            )
        }

        assertEquals(exception.message, "City should not include numbers")
    }

}