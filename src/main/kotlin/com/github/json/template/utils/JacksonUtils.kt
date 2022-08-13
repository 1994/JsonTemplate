package com.github.json.template.utils

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.deser.std.StringDeserializer
import com.fasterxml.jackson.databind.module.SimpleModule
import com.github.json.template.expression.ExpressionFactory

object CustomDeserializer : StdDeserializer<Any>(String::class.java) {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Any? {
        if (p.currentName?.isNotBlank() == true) {
            return ExpressionFactory.getHandleResult(p.text)
        }
        return p.text
    }
}

object CustomBeanModify : BeanDeserializerModifier() {
    override fun modifyDeserializer(
        config: DeserializationConfig?, beanDesc: BeanDescription, deserializer: JsonDeserializer<*>
    ): JsonDeserializer<*> {
        if (!beanDesc.beanClass.isAssignableFrom(String::class.java)) {
            return deserializer
        }

        return deserializer as? StringDeserializer ?: CustomDeserializer
    }
}

object JsonFacade {

    private val objectMapper: ObjectMapper = ObjectMapper().apply {
        registerModules(
            SimpleModule().apply {
                setDeserializerModifier(CustomBeanModify)
            }
        )
    }

    fun read(string: String): String {
        val readValue = objectMapper.readValue(string, Map::class.java)
        return objectMapper.writeValueAsString(readValue)
    }
}