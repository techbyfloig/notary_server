package com.techbyfloig.cubannotary.notary.model.ups.request.rate

import com.google.gson.annotations.SerializedName


data class Package(
    @SerializedName("Dimensions")
    val dimensions: Dimensions,
    @SerializedName("PackageWeight")
    val packageWeight: PackageWeight,
    @SerializedName("PackagingType")
    val packagingType: PackagingType,
    @SerializedName("SimpleRate")
    val simpleRate: SimpleRate // XS, S, M, L, XL
)