package com.techbyfloig.cubannotary.notary.model.passport.response

import com.techbyfloig.cubannotary.notary.model.pdf.PdfFieldType
import com.techbyfloig.cubannotary.notary.utils.longToDateParsed


data class BirthInfoDto(
    val country: String,
    val city: String,
    val state: String,
    val tomo: String,
    val folio: String,
    val register: String,
    val dateOfBirth: Long
){
    fun fields(): List<PdfFieldType> {
        val date = longToDateParsed(dateOfBirth)
        return listOf(
            PdfFieldType.Text("País", country, 12),
            PdfFieldType.Text("Provincia", state, 12),
            PdfFieldType.Text("Municipio Ciudad", city, 12),
            PdfFieldType.Text("DíaRow1_3", date[0], 10),
            PdfFieldType.Text("MesRow1_3", date[1], 10),
            PdfFieldType.Text("AñoRow1_2", date[2], 10),
        )
    }
}