package com.techbyfloig.cubannotary.android.network.models.request.shippingtime

data class ShippingTimeReq(
    val avvFlag: Boolean,
    val billType: String,
    val destinationCityName: String,
    val destinationCountryCode: String,
    val destinationPostalCode: String,
    val destinationStateProvince: String,
    val destinationTownName: String,
    val numberOfPackages: String,
    val originCityName: String,
    val originCountryCode: String,
    val originPostalCode: String,
    val originStateProvince: String,
    val originTownName: String,
    val residentialIndicator: String,
    val shipDate: String,
    val shipTime: String,
    val shipmentContentsCurrencyCode: String,
    val shipmentContentsValue: String,
    val weight: String,
    val weightUnitOfMeasure: String
)