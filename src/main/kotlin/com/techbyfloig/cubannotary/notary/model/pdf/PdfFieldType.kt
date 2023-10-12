package com.techbyfloig.cubannotary.notary.model.pdf

sealed class PdfFieldType(
    val key: String,
) {

    abstract fun values(): String


    class Text(
        key: String,
        private val value: String,
        val fontSize: Int? = null
    ) : PdfFieldType(key) {
        override fun values(): String = value
    }

    class CheckBok(
        key: String,
        private val value: PdfCheckBoxValue
        ) : PdfFieldType(key) {
        override fun values(): String = value.str
    }
}