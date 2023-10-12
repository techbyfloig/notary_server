package com.techbyfloig.cubannotary.android.network.models.response.track.ok

data class Activity(
    val date: String,
    val location: Location,
    val status: Status,
    val time: String
)