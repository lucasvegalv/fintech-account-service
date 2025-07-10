package com.fintech.contexts.accounts.domain

class AccountTest {

    //    account id
    // TODO -> account id valid que empiece con 'acc-'

    //    account number
    // TODO -> account number no sea null
    // TODO -> account number sean solo numeros
    // TODO -> account number tenga una long de 12 digitos

    //    currency
    // TODO -> currency sea ARS/BRA/USD

    //    holder (customer id)
    // TODO -> holder id comience con 'cus'

    //    transactions
    // TODO -> que al crearse la cuenta tenga un listado de 0 transacciones

    // TODO -> si la transaccion es de tipo DEPOSIT, REFUND o REVERSAL, el balance debe incrementarse

    // TODO -> si la transaccion es de tipo WITHDRAWAL, PURCHASE, FEE, el balance debe reducirse

    // TODO -> nunca puede ser con un monto negativo

    //    balance
    // TODO -> al momento de crearse debe ser 0

    // TODO -> nunca puede ser un monto negativo



    //    status (PENDING_APPROVAL, ACTIVE, SUSPENDED, BLOCKED, CLOSED)
    // TODO -> validar los posibles state-transitions

        // PENDING_APPROVAL -> ACTIVE
        // PENDING_APPROVAL -> CLOSED

        // ACTIVE -> SUSPENDED
        // ACTIVE -> BLOCKED
        // ACTIVE -> CLOSED

        // SUSPENDED -> ACTIVE
        // SUSPENDED -> BLOCKED
        // SUSPENDED -> CLOSED

        // BLOCKED -> ACTIVE
        // BLOCKED -> SUSPENDED
        // BLOCKED -> CLOSED

        // CLOSED -> * (a nada, es estado final)

    // TODO -> para pasar a CLOSED debe tener balance 0

    // TODO -> que se cree correctamente si esta completo (se ingresan todos los datos)
}