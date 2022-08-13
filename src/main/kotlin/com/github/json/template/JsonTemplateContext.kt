package com.github.json.template

import com.github.json.template.expression.ExpressionConfig

data class JsonTemplateContext(val target: Any, val expressionConfig: ExpressionConfig = ExpressionConfig())

object JsonTemplateHolder {
    private val threadLocal: ThreadLocal<JsonTemplateContext> = InheritableThreadLocal()

    fun init(target: Any) {
        threadLocal.set(JsonTemplateContext(target))
    }

    fun get(): JsonTemplateContext {
        return threadLocal.get()
    }

    fun remove() {
        threadLocal.remove()
    }
}