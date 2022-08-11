package com.github.json.template.utils

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.deser.std.StringDeserializer
import com.fasterxml.jackson.databind.module.SimpleModule

object CustomDeserializer : StdDeserializer<Any>(String::class.java) {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Any {
        return ""
    }
}

object CustomBeanModify : BeanDeserializerModifier() {
    override fun modifyDeserializer(
        config: DeserializationConfig?, beanDesc: BeanDescription, deserializer: JsonDeserializer<*>
    ): JsonDeserializer<*> {
        if (deserializer !is StringDeserializer) {
            return deserializer
        }

        if (!beanDesc.beanClass.isAssignableFrom(String::class.java)) {
            return deserializer
        }

        return CustomDeserializer
    }
}

object JsonFacade {
    private val objectMapper = ObjectMapper()

    init {
        val simpleModule = SimpleModule()
        simpleModule.setDeserializerModifier(CustomBeanModify)
        objectMapper.registerModule(simpleModule)
    }

    fun read(string: String) {
        val readValue = objectMapper.readValue(string, Map::class.java)
        println(readValue)
    }
}