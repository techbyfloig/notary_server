package com.techbyfloig.cubannotary.notary.model.ups.request.rate

import com.google.gson.annotations.SerializedName

//
//01-Bill Shipper: The shipper pays for the shipping charges.
//02-Bill Receiver: The receiver pays for the shipping charges.
//03-Bill Third Party: A third party, other than the shipper or receiver, pays for the shipping charges.
data class ShipmentCharge(
    @SerializedName("BillShipper")
    val billShipper: BillShipper,
    @SerializedName("Type")
    val type: String
)