package com.fintech.contexts.customers.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CustomerTest {

    private val customerLocation: Address = Address(
        countryCode = "ARG",
        city = "Buenos Aires",
        street = "Bonpland 2895",
        zipCode = "1405"
    )

    @Test
    fun `should register a new customer just if all the required field were defined` () {
        // setup
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

    @Test
    fun `should reject registration if Name field is blank` () {
        //exercise & assert
        val exception = assertThrows(IllegalArgumentException::class.java){
            Customer(
                name = "",
                lastname = "Vega",
                address = customerLocation,
                dni = "44324489",
                phone = "1158837671",
                email = "lucas@pomelo.la"
            )
        }

        assertEquals(exception.message, "Name must not be blank.")
    }

    // TODO: Should reject registration if Name field is not just chars
    @Test
    fun `should reject registration if Name field is not just characters` () {
        // exercise & assert
        val exception = assertThrows(IllegalArgumentException::class.java){
            Customer(
                name = "Lucas123",
                lastname = "Vega",
                address = customerLocation,
                dni = "44324489",
                phone = "1158837671",
                email = "lucas@pomelo.la"
            )
        }

        assertEquals(exception.message, "Name must not contain any alphanumeric characters.")
    }

    @Test
    fun `should reject registration if Name field is more than 50 characters length` () {
        // exercise & assert
        val exception = assertThrows(IllegalArgumentException::class.java){
            Customer(
                name = "Lucaslucaslucaslucaslucaslucas",
                lastname = "Vega",
                address = customerLocation,
                dni = "44324489",
                phone = "1158837671",
                email = "lucas@pomelo.la"
            )
        }

        assertEquals(exception.message, "Name must not have more than 20 characters.")
    }

    @Test
    fun `should reject registration if Lastname field is blank` () {
        // exercise & assert
        val exception = assertThrows(IllegalArgumentException::class.java) {
            Customer(
                name = "Lucas",
                lastname = "",
                address = customerLocation,
                dni = "44324489",
                phone = "1158837671",
                email = "lucas@pomelo.la"
            )
        }

        assertEquals(exception.message, "Last name must not be blank.")
    }

    @Test
    fun `should reject registration if Lastname field is not just characters` () {
        // exercise & assert
        val exception = assertThrows(IllegalArgumentException::class.java) {
            Customer(
                name = "Lucas",
                lastname = "Vega123",
                address = customerLocation,
                dni = "44324489",
                phone = "1158837671",
                email = "lucas@pomelo.la"
            )
        }

        assertEquals(exception.message, "Last Name must not contain any alphanumeric characters.")
    }

    @Test
    fun `should reject registration if Lastname field is more than 50 characters length` () {
        // exercise & assert
        val exception = assertThrows(IllegalArgumentException::class.java) {
            Customer(
                name = "Lucas",
                lastname = "VegavegavegavegaotroapellidounpellidoVegavegavegavegaotroapellidounpellido",
                address = customerLocation,
                dni = "44324489",
                phone = "1158837671",
                email = "lucas@pomelo.la"
            )
        }

        assertEquals(exception.message, "Last Name must not have more than 30 characters.")
    }

    // TODO: Should reject registration if DNI field is blank
    // TODO: Should reject registration if DNI field is not just numbers
    // TODO: Should reject registration if DNI field is more than 15 digits length


    // TODO: Should register a new customer with status ACTIVE

    // TODO: Should register a new customer with cero accounts related

    // TODO: Should desactivate a customer just if he/she is ACTIVE

    // TODO: Should reject desactivation if the customer has active accounts

    // TODO: Should reject registration if there's a user already registered with the same DNI
}
