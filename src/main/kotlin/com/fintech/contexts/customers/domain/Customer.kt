package com.fintech.contexts.customers.domain

class Customer(
    private val name: String,
    private val lastname: String,
    private val address: Address,
    private val dni: String,
    private val phone: String,
    private val email: String
) {
    fun hasThisDNI(dniToCompareWith: String): Boolean {
        return this.dni == dniToCompareWith
    }
}
