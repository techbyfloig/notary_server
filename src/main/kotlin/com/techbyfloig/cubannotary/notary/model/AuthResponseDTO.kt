package com.techbyfloig.cubannotary.notary.model

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import net.bytebuddy.asm.Advice.Return

data class AuthResponseDTO(
    private var tokens: List<Pair<String, String>>
) {
    fun generateTokenResponse(): String {
        val tokenFormat = tokens.map {
            it.first + ": " + it.second
        }
        return "${tokenFormat[0]}\n${tokenFormat[1]}"
    }

}
