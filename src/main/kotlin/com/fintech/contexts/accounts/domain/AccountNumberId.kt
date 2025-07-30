package com.fintech.contexts.accounts.domain

data class AccountNumberId(val value: String) {

    companion object {
        fun generate(): AccountNumberId {
            val accountNumberList: MutableList<Char> = mutableListOf()
            val DIGITS = "0123456789"
            val ID_LENGTH = 12

            for(digit in 1..ID_LENGTH) {
                val randomIndex = DIGITS.indices.random()
                val digit = DIGITS[randomIndex]
                accountNumberList.add(digit.toChar())
            }

            val accountNumber: String = accountNumberList.joinToString("")

            return AccountNumberId(accountNumber)
        }
    }


}