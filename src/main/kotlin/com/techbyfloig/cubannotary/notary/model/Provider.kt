package com.techbyfloig.cubannotary.notary.model

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken

sealed class Provider {
    class Google(val tokenId: GoogleIdToken): Provider()
    object Standard : Provider()
}

