package com.techbyfloig.cubannotary.notary.model.passport.response

import com.techbyfloig.cubannotary.notary.model.pdf.PdfFieldType
import com.techbyfloig.cubannotary.notary.model.pdf.constantTables.USState

data class AddressInfoDto(
    val street: String ,
    val apt: String,
    val city: String,
    val zip: String,
    val state: State
){

    private fun stateToEnum(): USState {
        return USState.values().find { it.abbreviation == state.code }
            ?: throw IllegalStateException("AppType is out of range from allowed values")
    }

    fun fields(): List<PdfFieldType> {
        return mutableListOf(
            PdfFieldType.Text(
                "Lugar de Residencia actual Dirección Calle Ave Nr Apto entre calles",
                "$street $apt", 16),
            PdfFieldType.Text("Código Postal", zip, 9),
            PdfFieldType.Text("Provincia  Estado  Región", "$city ${stateToEnum().fullName}", 14),
            PdfFieldType.Text("País_2", "US", 14)
        )
    }

    // figure out this for job address
    fun jobFields(): List<PdfFieldType> {
        return mutableListOf(
            PdfFieldType.Text("Dirección calle ave nr apto entre calles", "$street $apt", 16),
            PdfFieldType.Text( "Provincia  Estado  Región_2", "$city ${stateToEnum().fullName}", 14),
            PdfFieldType.Text("Código Postal_2" , zip, 10),
            PdfFieldType.Text("País_3" , "US", 14),
        )
    }

}