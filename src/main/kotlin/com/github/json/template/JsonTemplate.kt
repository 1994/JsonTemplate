package com.github.json.template

import com.github.json.template.utils.JsonFacade

object JsonTemplate {

    fun parse(jsonContent: String, root: Any) {
        JsonTemplateHolder.init(root)
        try {
            JsonFacade.read("")
        } finally {
            JsonTemplateHolder.remove()
        }
    }
}