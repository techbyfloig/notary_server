package com.techbyfloig.cubannotary.notary.model

import com.google.gson.Gson

data class Response(
    private val data: List<Any>
){
    fun toJson(): String  {
        var json = ""
        data.forEach {
            json += Gson().toJson(it) + "\n"
        }

        return json
    }
}
