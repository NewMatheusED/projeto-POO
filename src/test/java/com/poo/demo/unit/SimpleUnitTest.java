package com.poo.demo.unit;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Teste unitário simples para verificar se o ambiente de teste está funcionando
 */
class SimpleUnitTest {

    @Test
    void testBasicMath() {
        // Teste básico para verificar se o JUnit está funcionando
        assertEquals(2, 1 + 1);
        assertTrue(true);
        assertFalse(false);
    }

    @Test
    void testStringOperations() {
        String testString = "Hello World";
        assertNotNull(testString);
        assertEquals("Hello World", testString);
        assertTrue(testString.contains("World"));
    }

    @Test
    void testArrayOperations() {
        String[] array = {"a", "b", "c"};
        assertEquals(3, array.length);
        assertEquals("a", array[0]);
        assertEquals("c", array[2]);
    }
}
