package com.techbyfloig.cubannotary.notary.model.ups.request.rate

import com.google.gson.annotations.SerializedName

//
//00 - Unknown: Package type is unknown or not specified.
//01 - UPS Letter: A letter or document sent through UPS.
//02 - Package: A general package that is not specifically categorized.
//03 - Tube: A cylindrical tube-shaped package used for shipping.
//04 - Pak: A UPS branded packaging option, typically a padded envelope or soft pack.
//21 - Express Box: A UPS branded box for express shipments.
//24 - 10KG Box: A UPS branded box with a weight capacity of up to 10 kilograms.
//25 - 25KG Box: A UPS branded box with a weight capacity of up to 25 kilograms.
//30 - Pallet: A wooden or plastic platform used for shipping multiple packages together.
//2a - Small Express Box: A UPS branded small box for express shipments.
//2b - Medium Express Box: A UPS branded medium box for express shipments.
//2c - Large Express Box: A UPS branded large box for express shipments.
data class PackagingType(
    @SerializedName("Code")
    val code: String,
    @SerializedName("Description")
    val description: String
)