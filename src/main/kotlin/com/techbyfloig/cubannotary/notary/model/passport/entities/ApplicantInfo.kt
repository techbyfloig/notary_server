package com.techbyfloig.cubannotary.notary.model.passport.entities

import com.techbyfloig.cubannotary.notary.model.passport.response.ApplicantInfoDto
import javax.persistence.*

@Entity
@Table(name = "applicant_info")
data class ApplicantInfo(
    @Id @GeneratedValue val id: Long,
    val phone: String,
    val height: Double,
    @Column(name = "id_number")
    val idNumber: Long,
    @Column(name = "father_name")
    val fatherName: String,
    @Column(name = "mother_name")
    val motherName: String,
    @Column(name = "foreign_passport_no")
    val foreignPassportNo: String,
    @Column(name = "other_names")
    val otherNames: String,
    @Column(name = "residence_no")
    val residenceNo: String,
    @OneToOne
    @JoinColumn(name = "job_info_id") //foreign key name
    val jobInfoId: JobInfo,
    @OneToOne
    @JoinColumn(name = "applicant_extra_id") //foreign key name
    val applicantExtraId: ApplicantExtraInfo,
    @OneToOne
    @JoinColumn(name = "cuba_exit_info_id") //foreign key name
    val cubaExitInfoId: CubaExitInfo,
    @OneToMany
    @JoinColumn(name = "cuba_reference_id") //foreign key name
    val cubaReferenceId: List<CubaReference>,
    @OneToMany
    @JoinColumn(name = "cuba_last_address_id") //foreign key name
    val cubaLastAddressId: List<CubaLastAddress>
){
    fun toDto(): ApplicantInfoDto {
        return ApplicantInfoDto(
            phone = phone,
            height = height,
            idNumber = idNumber,
            fatherName = fatherName,
            motherName = motherName,
            foreignPassportNo = foreignPassportNo,
            otherNames = otherNames,
            residenceNo = residenceNo,
            jobInfo = jobInfoId.toDto(),
            applicantExtra = applicantExtraId.toDto(),
            cubaExitInfo = cubaExitInfoId.toDto(),
            cubaReference = cubaReferenceId.map { it.toDto() },
            cubaLastAddress = cubaLastAddressId.map { it.toDto() }
        )
    }
}