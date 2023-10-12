package com.techbyfloig.cubannotary.notary.model.passport.entities

import com.techbyfloig.cubannotary.notary.model.UserEntity
import com.techbyfloig.cubannotary.notary.model.passport.response.PassportDto
import javax.persistence.*


@Entity
@Table(name = "passport")
data class Passport(
    @Column(name = "app_id")
    @Id val appId: Long,
    @ManyToOne
    @JoinColumn(name = "user_id")
    var userId: UserEntity,
    val name: String,
    @Column(name = "middle_name")
    val middleName: String?,
    @Column(name = "last_name")
    val lastName: String,
    @Column(name = "second_last_name")
    val secondLastName: String?,
    @Column(name = "applicant_email")
    val applicantEmail: String,
    @Column(name = "request_day")
    val requestDay: Long,
    @OneToOne
    @JoinColumn(name = "service_type")
    val serviceTypeId: ServiceType,
    @OneToOne
    @JoinColumn(name = "address_id")
    var addressId: AddressInfo?,
    @OneToOne
    @JoinColumn(name = "passport_id")
    var passportId: PassportInfo?,
    @OneToOne
    @JoinColumn(name = "birth_info_id")
    var birthInfoId: BirthInfo?,
    @OneToOne
    @JoinColumn(name = "applicant_info_id")
    var applicantInfoId: ApplicantInfo?,
) {

    // create a DTO from the entity
    fun toDto(): PassportDto {
        return PassportDto(
            appId = appId,
            userId = userId.id,
            name = name,
            middleName = middleName ?: "",
            lastName = lastName,
            secondLastName = secondLastName ?: "",
            applicantEmail = applicantEmail,
            serviceType = serviceTypeId.toDto(),
            requestDay = requestDay,
            addressInfo = addressId?.toDto(),
            passportInfo = passportId?.toDto(),
            birthInfo = birthInfoId?.toDto(),
            applicantInfo = applicantInfoId?.toDto()
        )
    }
}