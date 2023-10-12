package com.techbyfloig.cubannotary.notary.model.ups.request.shipping



/**
 * UPS Shipping Request
 * Code Table
01: UPS Next Day Air
02: UPS Second Day Air
03: UPS Ground
07: UPS Worldwide Express
08: UPS Worldwide Expedited
11: UPS Standard
12: UPS Three-Day Select
13: UPS Next Day Air Saver
14: UPS Next Day Air Early A.M.
54: UPS Worldwide Express Plus
59: UPS Second Day Air A.M.
65: UPS Saver
82: UPS Today Standard
83: UPS Today Dedicated Courier
84: UPS Today Intercity
85: UPS Today Express
86: UPS Today Express Saver
 */
data class Service(
    val Code: String,
    val Description: String
)