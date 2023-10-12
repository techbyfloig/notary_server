package com.techbyfloig.cubannotary.notary.model.ups.request.shipping



data class PackageWeight(
    val UnitOfMeasurement: UnitOfMeasurement,
    val Weight: String
)