package com.anyject.binancefutureserver.utils

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class JsonsTest {

    @Test
    fun `JsonString을 HashMap(String,String)으로 변환`() {
        val jsonString = """
            {"a":"A","b":"B"}
        """.trimIndent()

        val hashMap = Jsons.toHashMap(jsonString)
        Assertions.assertThat(hashMap)
        Assertions.assertThat(hashMap["a"]).isEqualTo("A")
        Assertions.assertThat(hashMap["b"]).isEqualTo("B")
    }
    @Test
    fun `JsonString을 List(HashMap(String,String))으로 변환`() {
        val jsonString = """
            [
                {"a":"A","b":"B"},
                {"c":"C","d":"D"}
            ]
        """.trimIndent()

        val hashMap = Jsons.toListHashMap(jsonString)
        Assertions.assertThat(hashMap[0]["a"]).isEqualTo("A")
        Assertions.assertThat(hashMap[0]["b"]).isEqualTo("B")
        Assertions.assertThat(hashMap[1]["c"]).isEqualTo("C")
        Assertions.assertThat(hashMap[1]["d"]).isEqualTo("D")
    }



}