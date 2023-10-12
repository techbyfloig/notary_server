package com.techbyfloig.cubannotary.notary.model.passport.entities

import com.techbyfloig.cubannotary.notary.model.passport.response.JobInfoDto
import javax.persistence.*


@Entity
@Table(name = "job_info")
data class JobInfo(
    @Id @GeneratedValue val id: Long,
    @Column(name = "employee_name")
    val employeeName: String,
    @Column(name = "employee_phone")
    val employeePhone: String,
    @Column(name = "job_tile")
    val jobTitle: String,
    @Column(name = "profession_title")
    val professionTitle: String,
    @Column(name = "school_level")
    val schoolLevel: String,
    @OneToOne
    @JoinColumn(name = "address_id") //foreign key name
    val addressId: AddressInfo
){
    fun toDto(): JobInfoDto {
        return JobInfoDto(
            employeeName = employeeName,
            employeePhone = employeePhone,
            jobTitle = jobTitle,
            professionTitle = professionTitle,
            schoolLevel = schoolLevel,
            jobAddress = addressId.toDto()
        )
    }
}