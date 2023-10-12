package com.techbyfloig.cubannotary.notary.model.pdf.constantTables


enum class Sex(val constant: String, val pdfFieldKey: String = "") {
    Male("Masculino", "undefined_4" ),
    Female("Feminino", "undefined_5");

    companion object{
        fun getStringValues():Array<String> {
            return Sex.values().map {
                it.constant
            }.toTypedArray()
        }

    }
}

enum class MaritalStatus(val constant: String, val pdfFieldKey: String = "") {
    Married("Casado", "Estado Civil" ),
    Divorced("Divorciado", "Estado Civil"),
    Single("Soltero", "Estado Civil");

    companion object{
        fun getStringValues():Array<String> {
            return MaritalStatus.values().map {
                it.constant
            }.toTypedArray()
        }

    }
}



enum class EyeColor(val constant: String, val pdfFieldKey: String = "") {
    Clear("Claros", "Claros" ),
    Black("Negros", "Negros"),
    Brown("Pardos", "Pardos");

    companion object{
        fun getStringValues():Array<String> {
            return EyeColor.values().map {
                it.constant
            }.toTypedArray()
        }

    }

}

enum class SkinColor(val constant: String, val pdfFieldKey: String = "") {
    White("Blanca", "Blanca"),
    Black("Negra", "Negra"),
    Mixed("Mestiza", "Mestiza"),
    Yellow("Amarrilla", "Amarilla"),
    Albino("Albina", "Albina");

    companion object{
        fun getStringValues():Array<String> {
            return SkinColor.values().map {
                it.constant
            }.toTypedArray()
        }

    }
}

enum class HairColor(val constant: String, val pdfFieldKey: String = "") {
    White("Canoso", "Canoso"),
    Brown("Castano", "Castaño"),
    Black("Negro", "Negro"),
    Blonde("Rubio", "Rubio"),
    Red("Rojo", "Rojo"),
    Others("Otros", "Otros");

    companion object{
        fun getStringValues():Array<String> {
            return HairColor.values().map {
                it.constant
            }.toTypedArray()
        }

    }
}

enum class CubaExitType(val constant: String, val pdfFieldKey: String = "") {
    OfficialMatter("Asunto Oficial", "Asunto oficial"),
    ExteriorResidencePermit("Permiso de Residencia en el Exterior (PRE)", "Residencia en el"),
    TravelPermit("Permiso de Viaje al Exterior (PVE)", "Permiso de Viaje al"),
    TemporalTravelPermit("Permiso de Viaje Temporal (PVT)", "PVT"),
    EmigrationPermit("Permiso Emigración", "Permiso de Emigración"),
    UndefinedExitPermit("Permiso de Salida Indefinido (PSI)", "PSI"),
    EspecialResidencePermit("Permiso de Residencia en el Exterior Especial (PRE-E)", "PREE"),
    ExteriorResident("Residente en el Exterior", "RE"),
    EspecialExteriorResident("Residente en el Exterior Especial", "Exterior Especial"),
    IlegalExit("Salida Ilegal", "Salida Ilegal");

    companion object{
        fun getStringValues():Array<String> {
            return CubaExitType.values().map {
                it.constant
            }.toTypedArray()
        }

    }

}

/**
 * Represents all the passport application types
 *
 * @property constant represents the firebase constant value
 * @property pdfFieldKey represents the key the identifies one of this [constant] in the pdf form
 */
enum class ServiceType(val constant: String, val pdfFieldKey: String = ""){
    FirstTime("Pasaporte por primera vez" , "1eravez"),
    Renew("Renovación de Pasaporte", "renova"),
    Extend("Prórrogas", "Prorrogas"),
    EnablingEntry("Habilitación de Entrada", "HEE"),
    HE1("HE-1", "HE1"),
    HE3("HE-3", "HE3"),
    HE4("HE-4","HE4" ),
    HE11("HE-11", "HE11"),
    DVT("DVT", "DVT"),
    CCV("CCV", "CCV");

    companion object{
        fun getStringValues():Array<String> {
            return ServiceType.values().map {
                it.constant
            }.toTypedArray()
        }

    }
}

enum class USState(val abbreviation: String, val fullName: String) {
    ALABAMA("AL", "Alabama"),
    ALASKA("AK", "Alaska"),
    ARIZONA("AZ", "Arizona"),
    ARKANSAS("AR", "Arkansas"),
    CALIFORNIA("CA", "California"),
    COLORADO("CO", "Colorado"),
    CONNECTICUT("CT", "Connecticut"),
    DELAWARE("DE", "Delaware"),
    FLORIDA("FL", "Florida"),
    GEORGIA("GA", "Georgia"),
    HAWAII("HI", "Hawaii"),
    IDAHO("ID", "Idaho"),
    ILLINOIS("IL", "Illinois"),
    INDIANA("IN", "Indiana"),
    IOWA("IA", "Iowa"),
    KANSAS("KS", "Kansas"),
    KENTUCKY("KY", "Kentucky"),
    LOUISIANA("LA", "Louisiana"),
    MAINE("ME", "Maine"),
    MARYLAND("MD", "Maryland"),
    MASSACHUSETTS("MA", "Massachusetts"),
    MICHIGAN("MI", "Michigan"),
    MINNESOTA("MN", "Minnesota"),
    MISSISSIPPI("MS", "Mississippi"),
    MISSOURI("MO", "Missouri"),
    MONTANA("MT", "Montana"),
    NEBRASKA("NE", "Nebraska"),
    NEVADA("NV", "Nevada"),
    NEW_HAMPSHIRE("NH", "New Hampshire"),
    NEW_JERSEY("NJ", "New Jersey"),
    NEW_MEXICO("NM", "New Mexico"),
    NEW_YORK("NY", "New York"),
    NORTH_CAROLINA("NC", "North Carolina"),
    NORTH_DAKOTA("ND", "North Dakota"),
    OHIO("OH", "Ohio"),
    OKLAHOMA("OK", "Oklahoma"),
    OREGON("OR", "Oregon"),
    PENNSYLVANIA("PA", "Pennsylvania"),
    RHODE_ISLAND("RI", "Rhode Island"),
    SOUTH_CAROLINA("SC", "South Carolina"),
    SOUTH_DAKOTA("SD", "South Dakota"),
    TENNESSEE("TN", "Tennessee"),
    TEXAS("TX", "Texas"),
    UTAH("UT", "Utah"),
    VERMONT("VT", "Vermont"),
    VIRGINIA("VA", "Virginia"),
    WASHINGTON("WA", "Washington"),
    WEST_VIRGINIA("WV", "West Virginia"),
    WISCONSIN("WI", "Wisconsin"),
    WYOMING("WY", "Wyoming")
}