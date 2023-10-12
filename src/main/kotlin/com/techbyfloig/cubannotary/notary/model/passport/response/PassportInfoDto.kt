package com.techbyfloig.cubannotary.notary.model.passport.response

import com.techbyfloig.cubannotary.notary.model.pdf.PdfFieldType
import com.techbyfloig.cubannotary.notary.utils.longToDateParsed


data class PassportInfoDto(
    val number: String,
    val emissionDate: Long,
    val emissionPlace: String,
    val expirationDate: Long,
){
    fun fields(): List<PdfFieldType> {
        val date = longToDateParsed(emissionDate)
        return mutableListOf(
            PdfFieldType.Text("Número", number, 12),
            PdfFieldType.Text("Lugar", emissionPlace, 12),
            PdfFieldType.Text("Fecha de expedición", date[0]+ "/" +date[1]+ "/" +date[2], 12),
            PdfFieldType.Text("Número de Pasaporte", number, 12)
        )
    }
}