package com.techbyfloig.cubannotary.notary.model.ups.response.rate

data class Response(
    val Alert: List<Alert>,
    val ResponseStatus: ResponseStatus,
    val TransactionReference: TransactionReference
)