package com.techbyfloig.cubannotary.notary.env

object Environment {

    /**
     * Define all the UPS restful apis constant values
     */
    object UpsApisConst {

        const val VERSION = "version"
        const val SECRET_KEY = "SzBM4u2J9nbK6yBDrFw1uKgqgAnDGTyWipZBwLrC2fq9CEqZJuHKsKRfYaVeOj3e"
        const val CLIENT_KEY = "yyOVCLCDc33qVbdZMdjNFH2B0RGzk6oiByNP9nDe2AoG0pZ1"
        const val GRANT_TYPE = "grant_type"

        object Rating {

            const val PATH = "/api/rating/{$VERSION}/{${PathParameters.REQUEST_OPT}}"

            object PathParameters {
                const val REQUEST_OPT = "requestoption"
            }

            object PathQueries {
                const val ADDITIONAL_INFO = "additionalinfo"
            }

        }

        object Track {

            const val PATH = "/track/v1/details/{${PathParameters.INQUIRY_NO}}"

            object PathParameters {
                const val INQUIRY_NO = "inquiryNumber"
            }

            object PathQueries {
                const val LOCALE = "locale"
                const val RETURN_SIGNATURE = "returnSignature"
            }

        }

        object AddressValidation {

            const val PATH = "/api/addressvalidation/{$VERSION}/{${PathParameters.REQUEST_OPT}}?maximumcandidatelistsize=1"

            object PathParameters {
                const val REQUEST_OPT = "requestoption"
            }

            object PathQueries {
                const val REGIONAL_REQ_INDICATOR = "regionalrequestindicator"
                const val MAX_CANDIDATES = "maximumcandidatelistsize"
            }
        }

        object Shipment {

            const val PATH_BY_SHIP = "/api/shipments/{$VERSION}/${Ship.SHIP}"
            const val PATH_BY_SHIP_TIME = "/api/shipments/{$VERSION}/{${ShipTime.SHIP_TIME}}"

            object Ship {

                const val SHIP = "ship"

                object PathQueries {
                    const val ADDITIONAL_ADD_VALIDATION = "additionaladdressvalidation"
                }
            }

            object ShipTime {
                const val SHIP_TIME = "/transittimes"
            }

        }

        object Label {

            const val PATH = "/labels/$VERSION/recovery"

        }

        object Oauth {

            const val VALIDATE_CLIENT_PATH = "/security/v1/oauth/${ValidateClient.VALIDATE_CLIENT}"
            const val TOKEN_PATH = "/security/v1/oauth/${Token.TOKEN}"
            const val REFRESH_TOKEN_PATH = "/security/v1/oauth/${RefreshToken.REFRESH}"


            object Token {
                const val TOKEN = "token"
            }

            object RefreshToken {
                const val REFRESH = "refresh"

            }

            object ValidateClient {
                const val VALIDATE_CLIENT = "validate-client"

                object PathQueries {
                    const val CLIENT_ID = "client_id"
                    const val REDIRECT_URL = "redirect_url"
                }

            }


        }


    }

     val stripeBaseUrl: String = "https://api.stripe.com/v1/"
     val stripePublishableKey: String =
        "pk_live_51Mz9vyIK8TCPzAedokUrtm1XpFcR0JccVsLcZKufNyEiU4eu3yzMVqhcpSJj4fUmGOkBnUHhSBZFnuIY04O82mEK00sXHusGQM"
     val stripeSecretKey: String =
        "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"
     val stripeTestSecretKey: String =
        "sk_test_51Mz9vyIK8TCPzAedjiqZu3IULz7g8AvjTjMnAs3knvmjPE1VyVxiaVS3j4XZhovtwCNDVtbZp8fjJYROMGxCaEgM004VuGnHOP"
     val stripeTestPublishableKey: String =
        "pk_test_51Mz9vyIK8TCPzAedecMYFT2w9mUtgNS43jb8bVVokdSH2apur0yb1XMNVxgtpcMqzrQPTQgqvKDnMu2XsMTiAPnz00mXywkDH3"
     val upsAppKey: String = "yyOVCLCDc33qVbdZMdjNFH2B0RGzk6oiByNP9nDe2AoG0pZ1"
     val upsSecretKey: String = "SzBM4u2J9nbK6yBDrFw1uKgqgAnDGTyWipZBwLrC2fq9CEqZJuHKsKRfYaVeOj3e"
}