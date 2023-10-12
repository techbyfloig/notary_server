package com.techbyfloig.cubannotary.notary.model.ups.response.rate

data class TransactionReference(
    val CustomerContext: String,
    val TransactionIdentifier: String
)