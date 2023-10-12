package com.techbyfloig.cubannotary.notary.model.passport.response

import com.techbyfloig.cubannotary.notary.model.pdf.PdfFieldType
import com.techbyfloig.cubannotary.notary.utils.longToDateParsed

data class CubaLastAddressDto(
    val address: String,
    val fromDate: Long,
    val toDate: Long
){
    fun getFirstFields(): List<PdfFieldType> {
        val dateFromOne = longToDateParsed(fromDate)
        val dateToOne = longToDateParsed(toDate)
        return listOf(
            PdfFieldType.Text(
                "Lugar de Residencia en Cuba dos últimas direcciones Desde HastaRow1",
                address, 12
            ),
            PdfFieldType.Text(
                "Desde",
                dateFromOne[0] + "/" + dateFromOne[1] + "/" + dateFromOne[2], 12
            ),
            PdfFieldType.Text(
                "Hasta",
                dateToOne[0] + "/" + dateToOne[1] + "/" + dateToOne[2], 12
            )
        )
    }

    fun getSecondFields(): List<PdfFieldType> {
        val dateFromTwo = longToDateParsed(fromDate)
        val dateToTwo = longToDateParsed(toDate)
        return listOf(
            PdfFieldType.Text(
                "Lugar de Residencia en Cuba dos últimas direcciones Desde HastaRow2",
                address, 12
            ),
            PdfFieldType.Text(
                "Desde1",
                dateFromTwo[0] + "/" + dateFromTwo[1] + "/" + dateFromTwo[2], 12
            ),
            PdfFieldType.Text(
                "Hasta1",
                dateToTwo[0] + "/" + dateToTwo[1] + "/" + dateToTwo[2], 12
            )
        )
    }
}