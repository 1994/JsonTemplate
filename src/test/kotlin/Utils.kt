package com.github.json.template

import com.google.common.io.Resources
import org.skyscreamer.jsonassert.JSONAssert
import kotlin.test.assertNotNull

object Utils {

    private fun getResourceAsText(path: String): String {
        return Resources.getResource(path).readText()
    }

    fun assertJson(file: String, target: Any) {
        val input = getResourceAsText("templates/${file}")
        val expect = getResourceAsText("asserts/${file}")
        assertNotNull(input)
        JSONAssert.assertEquals(expect, JsonTemplate.parse(input, target), true)
    }
}
