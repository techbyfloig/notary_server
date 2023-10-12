package com.techbyfloig.cubannotary.notary.model.ups.request.shipping



data class Package(
    val Description: String,
    val Dimensions: Dimensions,
    val PackageWeight: PackageWeight,
    val Packaging: Packaging
)