package com.fintech.contexts.customers.domain

data class Address(
    private val countryCode: CountryCodes,
    private val city: String,
    private val street: String,
    private val zipCode: String
)