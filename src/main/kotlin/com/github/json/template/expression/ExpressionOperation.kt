package com.github.json.template.expression

import com.github.json.template.JsonTemplateHolder
import com.googlecode.aviator.AviatorEvaluator

const val PREFIX = "$" + "{"
const val SUFFIX = "}"

interface ExpressIdentity {
    fun needHandle(expression: String): Boolean

    fun getContent(expression: String): String
}

data class ExpressionConfig(val identity: ExpressIdentity = SimpleExpressIdentity())
data class SimpleExpressIdentity(val prefix: String = PREFIX, val suffix: String = SUFFIX) : ExpressIdentity {
    override fun needHandle(expression: String): Boolean {
        return expression.startsWith(prefix, ignoreCase = true) && expression.endsWith(suffix, ignoreCase = true)
    }

    override fun getContent(expression: String): String {
        return expression.removeSurrounding(prefix, suffix);
    }
}

data class ExpressionOption(val compile: Boolean)

object ExpressionFactory {
    fun getHandleResult(expression: String): Any? {
        val identity = JsonTemplateHolder.get().expressionConfig.identity
        if (!identity.needHandle(expression)) {
            return expression
        }

        val content = identity.getContent(expression)
        val target = JsonTemplateHolder.get().target
        if (target is Map<*, *>) {
            return target.getOrDefault(content, null)
        }
        AviatorEvaluator.compile(content, true).apply {
            execute()
        }
        return content
    }
}