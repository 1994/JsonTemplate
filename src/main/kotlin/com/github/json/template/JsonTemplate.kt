package com.github.json.template

import com.github.json.template.utils.JsonFacade

object JsonTemplate {

    fun parse(jsonContent: String, root: Any): String {
        JsonTemplateHolder.init(root)
        try {
            return JsonFacade.read(jsonContent)
        } finally {
            JsonTemplateHolder.remove()
        }
    }
}