package com.techbyfloig.cubannotary.notary.model.ups.response.rate

data class RateResponse(
    val RatedShipment: List<RatedShipment>,
    val Response: Response
)