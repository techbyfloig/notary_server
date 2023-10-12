package com.techbyfloig.cubannotary.notary.model

enum class ApplicationType(val value: Int) {
    PASSPORT(1),
    RESIDENCY(2),
    WORK_PERMIT(3),
    UNKNOWN(-1);

    companion object {
        fun fromValue(value: Int): ApplicationType {
            return values().find { it.value == value } ?: UNKNOWN
        }
    }
}