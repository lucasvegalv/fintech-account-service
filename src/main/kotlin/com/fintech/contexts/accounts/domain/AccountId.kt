package com.fintech.contexts.accounts.domain

import java.util.*

data class AccountId(val value: String) {
    companion object {
        fun generate(): AccountId {
          val value = UUID.randomUUID().toString()
          return AccountId("acc-$value")

        }
    }
}
