package com.fintech.contexts.customers.domain

import java.util.*

data class CustomerId (val value: String) {
    companion object {
        fun generate(): CustomerId {
            val id = UUID.randomUUID().toString()
            return CustomerId("cus-$id")
        }
    }
}
