package com.anyject.binancefutureserver.config

import org.springframework.stereotype.Component
import kotlin.reflect.full.memberProperties

@Component
class ApplicationPropertiesPrinter(
    private val appProperties: ApplicationProperties
) {

    fun printMap() : Map<String, String> {
        val properties = mutableMapOf<String, String>()
        getPropertyValues(properties, "", appProperties)

       return properties
    }

    private fun getPropertyValues(properties: MutableMap<String, String>, prefix: String, obj: Any) {
        obj::class.memberProperties.forEach { prop ->
            val name = prefix + prop.name
            when (val value = prop.getter.call(obj)) {
                is String -> properties[name] = value
                is Any -> getPropertyValues(properties, "$name.", value)
            }
        }
    }
}
