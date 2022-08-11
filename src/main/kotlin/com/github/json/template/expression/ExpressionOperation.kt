package com.github.json.template.expression

import com.github.json.template.JsonTemplateContext

const val PREFIX = "$" + "{"
const val SUFFIX = "}"

interface ExpressIdentity {
    fun needHandle(content: String): Boolean
}

data class ExpressionContext(val rootTarget: Any, val identity: ExpressIdentity = SimpleExpressIdentity())
data class SimpleExpressIdentity(val prefix: String = PREFIX, val suffix: String = SUFFIX) : ExpressIdentity {
    override fun needHandle(content: String): Boolean {
        return content.startsWith(prefix, ignoreCase = true) && content.endsWith(suffix, ignoreCase = true)
    }

}

data class ExpressionOption(val compile: Boolean)

fun handle(content: String, jsonTemplateContext: JsonTemplateContext, expressIdentity: ExpressIdentity): Any {
    return ""
}