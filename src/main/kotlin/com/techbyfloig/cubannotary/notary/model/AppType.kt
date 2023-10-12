package com.techbyfloig.cubannotary.notary.model

enum class AppType {
    PASSPORT,
    RESIDENCE;

    companion object{
        fun from(value: Int): AppType? {
          return when (value !in AppType.values().indices) {
              true -> null
              else -> AppType.values()[value]
          }
        }
    }
}