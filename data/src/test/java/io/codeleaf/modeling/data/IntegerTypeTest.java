package io.codeleaf.modeling.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IntegerTypeTest {

    @Test
    public void testEquals() {
        // Given
        ValueType integerType = new IntegerType();

        // When
        boolean result = integerType.equals(new IntegerType());

        // Then
        Assertions.assertTrue(result);
    }

    @Test
    public void testEquals_NotEquals() {
        // Given
        ValueType integerType = new IntegerType();

        // When
        boolean result = integerType.equals(new BooleanType());

        // Then
        Assertions.assertFalse(result);
    }

}
