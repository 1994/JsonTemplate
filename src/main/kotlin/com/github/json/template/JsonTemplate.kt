package com.github.json.template

import com.github.json.template.utils.JsonFacade

object JsonTemplate {

    fun parseToString(jsonContent: String, root: Any): String {
        return JsonFacade.write(parseToMap(jsonContent, root))
    }

    fun parseToMap(jsonContent: String, root: Any): Map<String, Any?> {
        return parse(jsonContent, root, Map::class.java) as Map<String, Any?>
    }

    fun <T> parse(jsonContent: String, root: Any, clazz: Class<T>): T {
        JsonTemplateHolder.init(root)
        try {
            return JsonFacade.read(jsonContent, clazz)
        } finally {
            JsonTemplateHolder.remove()
        }
    }

}