package com.anyject.binancefutureserver.utils

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class Jsons {
    companion object{
        @JvmStatic
        fun toHashMap(json: String): HashMap<String, String> {
            return Json.decodeFromString(json)
        }

        @JvmStatic
        fun toListHashMap(json: String): List<HashMap<String, String>> {
            return Json.decodeFromString(json)
        }
    }
}