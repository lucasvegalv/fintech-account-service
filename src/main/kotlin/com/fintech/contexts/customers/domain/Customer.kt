package com.fintech.contexts.customers.domain

import com.fintech.contexts.accounts.domain.Account

class Customer(
    private val name: String,
    private val lastname: String,
    private val address: Address,
    private val dni: String,
    private val phone: String,
    private val email: String
) {

    private val customerId = CustomerId.generate()
    private val accounts: MutableSet<Account> = mutableSetOf()
    private var status: CustomerStatus

    init {
        require(name.isNotBlank()) {"Name must not be blank."}
        require(!name.contains(Regex("\\d"))) {"Name must not contain any alphanumeric characters."}
        require(name.length <= 20){"Name must not have more than 20 characters."}
        require(lastname.isNotBlank()) {"Last name must not be blank."}
        require(!lastname.contains(Regex("\\d"))) {"Last Name must not contain any alphanumeric characters."}
        require(lastname.length <= 30){"Last Name must not have more than 30 characters."}
        require(dni.isNotBlank()) {"DNI must not be blank."}
        require(dni.all { it.isDigit() }) {"DNI must not contain just digits."}
        require(dni.length <= 15) {"DNI must not contain more than 15 digits."}

        status = CustomerStatus.ACTIVE
    }


    fun hasThisDNI(dniToCompareWith: String): Boolean = this.dni == dniToCompareWith

    fun isActive(): Boolean = this.status == CustomerStatus.ACTIVE

    fun deactivate() {
        require(this.isActive()) {"Can not deactivate a customer if it is not ACTIVE"}
        this.status = CustomerStatus.DEACTIVATED
    }

    fun isDeactivated(): Boolean = this.status == CustomerStatus.DEACTIVATED

    fun hasAnyAccounts(): Boolean = this.accounts.size > 0

    fun hasValidUUID(): Boolean = this.customerId.value.startsWith("cus")
}
