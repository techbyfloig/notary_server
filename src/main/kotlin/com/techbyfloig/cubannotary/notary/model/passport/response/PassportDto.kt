package com.techbyfloig.cubannotary.notary.model.passport.response

import com.techbyfloig.cubannotary.notary.model.ApplicationType
import com.techbyfloig.cubannotary.notary.model.pdf.PdfCheckBoxValue
import com.techbyfloig.cubannotary.notary.model.pdf.PdfFieldType
import com.techbyfloig.cubannotary.notary.model.pdf.constantTables.ServiceType
import com.techbyfloig.cubannotary.notary.utils.longToDateParsed

data class PassportDto(
    val appId: Long,
    var userId: Long,
    val name: String,
    val middleName: String,
    val lastName: String,
    val secondLastName: String,
    val applicantEmail: String,
    val serviceType: com.techbyfloig.cubannotary.notary.model.passport.response.ServiceType,
    val requestDay: Long,
    var addressInfo: AddressInfoDto?,
    var passportInfo: PassportInfoDto?,
    var birthInfo: BirthInfoDto?,
    var applicantInfo: ApplicantInfoDto?,
) {

    private fun appTypeToEnum(): ServiceType {
        return ServiceType.values().find { it.constant == serviceType.name }
            ?: throw IllegalStateException("AppType is out of range from allowed values")
    }

    fun fields(): List<PdfFieldType> {
        val list = mutableListOf<PdfFieldType>()

        // section 1 -- service requested/ date/ passport number
        //date
        val dateList = longToDateParsed(requestDay)
        list.add(PdfFieldType.Text("DíaRow1", dateList[0], 10))
        list.add(PdfFieldType.Text("MesRow1", dateList[1], 10))
        list.add(PdfFieldType.Text("AñoRow", dateList[2], 10))
        // ID
        // service requested
        list.add(PdfFieldType.CheckBok(appTypeToEnum().pdfFieldKey, PdfCheckBoxValue.Yes))

        // section 2 - general data
        list.add(PdfFieldType.Text("Primer nombre", name, 12))
        list.add(PdfFieldType.Text("Segundo nombre", middleName, 12))
        list.add(PdfFieldType.Text("Primer apellido", lastName, 12))
        list.add(PdfFieldType.Text("Segundo apellido", secondLastName, 12))
        list.add(PdfFieldType.Text("Email", applicantEmail, 12))

        addressInfo?.fields()?.forEach {
            list.add(it)
        }

        passportInfo?.fields()?.forEach {
            list.add(it)
        }

        birthInfo?.fields()?.forEach {
            list.add(it)
        }

        applicantInfo?.fields()?.forEach {
            list.add(it)
        }

        return list
    }
}