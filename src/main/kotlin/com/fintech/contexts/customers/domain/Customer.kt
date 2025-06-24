package com.fintech.contexts.customers.domain

class Customer(
    private val name: String,
    private val lastname: String,
    private val address: Address,
    private val dni: String,
    private val phone: String,
    private val email: String
) {

    init {
        require(name.isNotBlank()) {"Name must not be blank."}
        require(!name.contains(Regex("\\d"))) {"Name must not contain any alphanumeric characters."}
        require(name.length <= 20){"Name must not have more than 20 characters."}
        require(lastname.isNotBlank()) {"Last name must not be blank."}
        require(!lastname.contains(Regex("\\d"))) {"Last Name must not contain any alphanumeric characters."}
        require(lastname.length <= 30){"Last Name must not have more than 30 characters."}
    }


    fun hasThisDNI(dniToCompareWith: String): Boolean {
        return this.dni == dniToCompareWith
    }
}
