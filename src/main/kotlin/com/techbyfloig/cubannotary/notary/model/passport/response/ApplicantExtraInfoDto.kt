package com.techbyfloig.cubannotary.notary.model.passport.response

import com.techbyfloig.cubannotary.notary.model.pdf.PdfCheckBoxValue
import com.techbyfloig.cubannotary.notary.model.pdf.PdfFieldType
import com.techbyfloig.cubannotary.notary.model.pdf.constantTables.EyeColor
import com.techbyfloig.cubannotary.notary.model.pdf.constantTables.Sex
import com.techbyfloig.cubannotary.notary.model.pdf.constantTables.SkinColor
import com.techbyfloig.cubannotary.notary.model.pdf.constantTables.HairColor
import com.techbyfloig.cubannotary.notary.model.pdf.constantTables.MaritalStatus


data class ApplicantExtraInfoDto(
    val eyesColor: EyesColor,
    val sex: com.techbyfloig.cubannotary.notary.model.passport.response.Sex,
    val maritalStatus: com.techbyfloig.cubannotary.notary.model.passport.response.MaritalStatus,
    val skinColor: com.techbyfloig.cubannotary.notary.model.passport.response.SkinColor,
    val hairColor: com.techbyfloig.cubannotary.notary.model.passport.response.HairColor
){

    private fun eyesToEnum(): EyeColor {
        return EyeColor.values().find { it.constant == eyesColor.color}
            ?: throw IllegalStateException("Eyes Color is out of range from allowed values")
    }

    private fun sexToEnum(): Sex {
        return Sex.values().find { it.constant == sex.sex}
            ?: throw IllegalStateException("Sex is out of range from allowed values")
    }

    private fun skinToEnum(): SkinColor {
        return SkinColor.values().find { it.constant == skinColor.color }
            ?: throw IllegalStateException("Skin Color is out of range from allowed values")
    }

    private fun hairToEnum(): HairColor {
        return HairColor.values().find { it.constant == hairColor.color }
            ?: throw IllegalStateException("Hair Color is out of range from allowed values")
    }

    private fun maritalStatusToEnum(): MaritalStatus {
        return MaritalStatus.values().find { it.constant == maritalStatus.status }
            ?: throw IllegalStateException("Hair Color is out of range from allowed values")
    }

    fun fields(): List<PdfFieldType> {
        return mutableListOf(
            PdfFieldType.CheckBok(eyesToEnum().pdfFieldKey, PdfCheckBoxValue.On),
            PdfFieldType.CheckBok(sexToEnum().pdfFieldKey, PdfCheckBoxValue.On),
            PdfFieldType.CheckBok(skinToEnum().pdfFieldKey, PdfCheckBoxValue.On),
            PdfFieldType.CheckBok(hairToEnum().pdfFieldKey, PdfCheckBoxValue.On),
            PdfFieldType.Text(maritalStatusToEnum().pdfFieldKey, maritalStatus.status, 12)
        )
    }
}


