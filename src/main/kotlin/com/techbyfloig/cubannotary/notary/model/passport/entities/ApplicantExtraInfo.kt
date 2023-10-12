package com.techbyfloig.cubannotary.notary.model.passport.entities

import com.techbyfloig.cubannotary.notary.model.passport.response.ApplicantExtraInfoDto
import javax.persistence.*


@Entity
@Table(name = "applicant_extra_info")
data class ApplicantExtraInfo(
    @Id @GeneratedValue val id: Long,
    @OneToOne
    @JoinColumn(name = "eyes_color_id") //foreign key name
    val eyesColorId: EyesColor,
    @OneToOne
    @JoinColumn(name = "sex_id") //foreign key name
    val sexId: Sex,
    @OneToOne
    @JoinColumn(name = "marital_status_id") //foreign key name
    val maritalStatusId: MaritalStatus,
    @OneToOne
    @JoinColumn(name = "skin_color_id") //foreign key name
    val skinColorId: SkinColor,
    @OneToOne
    @JoinColumn(name = "hair_color_id") //foreign key name
    val hairColor: HairColor
){
    fun toDto(): ApplicantExtraInfoDto {
        return ApplicantExtraInfoDto(
            eyesColor = eyesColorId.toDto(),
            hairColor = hairColor.toDto(),
            maritalStatus = maritalStatusId.toDto(),
            skinColor = skinColorId.toDto(),
            sex = sexId.toDto()
        )
    }
}


