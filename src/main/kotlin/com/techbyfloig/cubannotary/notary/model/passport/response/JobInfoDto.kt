package com.techbyfloig.cubannotary.notary.model.passport.response

import com.techbyfloig.cubannotary.notary.model.pdf.PdfFieldType


data class JobInfoDto(
    val employeeName: String,
    val employeePhone: String,
    val jobTitle: String,
    val professionTitle: String,
    val schoolLevel: String,
    val jobAddress: AddressInfoDto
){
    fun fields(): List<PdfFieldType> {
        val list = mutableListOf<PdfFieldType>()

        list.add(PdfFieldType.Text("Datos Laborales o de Estudio actual Nombre de Centro de Trabajo Estudio",employeeName, 16))
        list.add(PdfFieldType.Text("Ocupación_2", jobTitle,12 ))
        list.add(PdfFieldType.Text("Profesión", professionTitle, 12))
        list.add(PdfFieldType.Text("Teléfono_2", employeePhone, 12))
        list.add(PdfFieldType.Text("Nivel de escolaridad", schoolLevel, 12))
        //list.add(PdfFieldType.Text("Email_2", email, 12))
        //list.add(PdfFieldType.Text("Fax_2", fax, 12))
        //list.add(PdfFieldType.Text("Ocupación", occupation, 12))
        //list.add(PdfFieldType.Text("Profesión u oficio", profession, 12))

        jobAddress.jobFields().forEach {
            list.add(it)
        }

        return list
    }
}