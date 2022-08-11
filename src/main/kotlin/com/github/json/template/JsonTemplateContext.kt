package com.github.json.template

data class JsonTemplateContext(val target: Any)

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