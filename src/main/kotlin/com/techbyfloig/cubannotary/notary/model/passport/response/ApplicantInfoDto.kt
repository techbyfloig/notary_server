package com.techbyfloig.cubannotary.notary.model.passport.response

import com.techbyfloig.cubannotary.notary.model.pdf.PdfFieldType

data class ApplicantInfoDto(
    val phone: String,
    val height: Double,
    val idNumber: Long,
    val fatherName: String,
    val motherName: String,
    val foreignPassportNo: String,
    val otherNames: String,
    val residenceNo: String,
    val jobInfo: JobInfoDto,
    val applicantExtra: ApplicantExtraInfoDto,
    val cubaExitInfo: CubaExitInfoDto,
    val cubaReference: List<CubaReferenceDto>,
    val cubaLastAddress: List<CubaLastAddressDto>
){

    fun fields(): List<PdfFieldType> {
        val list = mutableListOf<PdfFieldType>()

        list.add(PdfFieldType.Text("Teléfono", phone, 12))
        list.add(PdfFieldType.Text("Estaturacm", height.toString(), 12))
        list.add(PdfFieldType.Text("Carné de Identidad", idNumber.toString(), 12))
        list.add(PdfFieldType.Text("Madre", motherName, 12))
        list.add(PdfFieldType.Text("Padre", fatherName, 12))
        //list.add(PdfFieldType.Text("Apellidos de soltera", singleLastName, 12))
        list.add(PdfFieldType.Text("Otros nombres", otherNames, 12))
        list.add(PdfFieldType.Text("Número de residencia", residenceNo, 12))
        //list.add(PdfFieldType.Text("Características especiales", specialCharacteristics, 12))

        applicantExtra.fields().forEach {
            list.add(it)
        }

        jobInfo.fields().forEach {
            list.add(it)
        }
        cubaReference[0].getFirstFields().forEach {
            list.add(it)
        }
        cubaReference[1].getSecondFields().forEach {
            list.add(it)
        }

        cubaLastAddress[0].getFirstFields().forEach {
            list.add(it)
        }
        cubaLastAddress[0].getSecondFields().forEach {
            list.add(it)
        }
        cubaExitInfo.fields().forEach {
            list.add(it)
        }

        return list
    }
}