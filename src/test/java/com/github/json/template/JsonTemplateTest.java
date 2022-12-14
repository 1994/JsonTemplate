package com.github.json.template;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import lombok.Data;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

class JsonTemplateTest {

    @Test
    @DisplayName("hello world")
    void testHello() {
        Utils.INSTANCE.assertJson("hello.json", Collections.singletonMap("world", "hello world"));
    }

    @Test
    @DisplayName("test sample")
    void testSample() {
        Utils.INSTANCE.assertJson("sample.json", Collections.singletonMap("world", "hello world"));
    }

    @Test
    void name() {
        Expression name = AviatorEvaluator.compile("name", true);
        Object execute = name.execute(Collections.singletonMap("name", "add"));
        System.out.println(execute);
    }

    @Data
    public static class User {
        private String name = "ad";
    }
}
