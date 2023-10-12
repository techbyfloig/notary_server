package com.techbyfloig.cubannotary.notary.model.passport.response

import com.techbyfloig.cubannotary.notary.model.pdf.PdfCheckBoxValue
import com.techbyfloig.cubannotary.notary.model.pdf.PdfFieldType
import com.techbyfloig.cubannotary.notary.model.pdf.constantTables.CubaExitType
import com.techbyfloig.cubannotary.notary.utils.longToDateParsed

data class CubaExitInfoDto(
    val type: String,
    val exitDate: Long
){

    private fun cubaExitToEnum() : CubaExitType {
        return CubaExitType.values().find { it.constant == type } ?:
        throw IllegalStateException("Cuba exit type is out of range from allowed values")
    }
    fun fields(): List<PdfFieldType> {
        val list: MutableList<PdfFieldType> = mutableListOf()
        val extiDate = longToDateParsed(exitDate)

        list.add(PdfFieldType.Text("DíaRow1_2", extiDate[0], 10))
        list.add(PdfFieldType.Text("MesRow1_2", extiDate[1], 10))
        list.add(PdfFieldType.Text("AñoRow1", extiDate[2], 10))
        list.add(PdfFieldType.CheckBok(cubaExitToEnum().pdfFieldKey, PdfCheckBoxValue.On))

        return list
    }
}