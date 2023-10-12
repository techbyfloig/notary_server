package com.techbyfloig.cubannotary.notary.model.ups.response.addressvalidation


data class Candidate(
    val AddressClassification: AddressClassification,
    val AddressKeyFormat: AddressKeyFormat
)