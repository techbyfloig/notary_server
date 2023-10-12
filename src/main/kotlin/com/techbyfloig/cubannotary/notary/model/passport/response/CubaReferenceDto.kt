package com.techbyfloig.cubannotary.notary.model.passport.response

import com.techbyfloig.cubannotary.notary.model.pdf.PdfFieldType

data class CubaReferenceDto(
    val personFullName: String,
    val personPhone: String,
    val address: String,
){

    fun getFirstFields(): List<PdfFieldType> {
        return listOf(
            PdfFieldType.Text("Nombre y apellidos de la referencia en Cuba persona que puede ser contactada " +
                    "para verificar sus generales añadir de ser posible el número telefónico",
                "$personFullName $personPhone", 14),
            PdfFieldType.Text("Dirección de la Referencia incluir la provincia", address, 14)
        )
    }

    fun getSecondFields(): List<PdfFieldType> {
        return listOf(
            PdfFieldType.Text("Nombre y apellidos de la referencia en Cuba persona que puede ser contactada " +
                    "para verificar sus generales añadir de ser posible el número telefónico",
                "$personFullName $personPhone", 14),
            PdfFieldType.Text("Dirección de la Referencia incluir la provincia", address, 14)
        )
    }
}