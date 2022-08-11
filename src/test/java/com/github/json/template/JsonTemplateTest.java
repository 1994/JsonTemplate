package com.github.json.template;

import org.junit.jupiter.api.Test;

import java.util.Collections;

class JsonTemplateTest {

    @Test
    void test() {
        JsonTemplate.INSTANCE.parse("", Collections.emptyMap());
    }
}
