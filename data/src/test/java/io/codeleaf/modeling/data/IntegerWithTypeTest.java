package io.codeleaf.modeling.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IntegerWithTypeTest {

    @Test
    public void testEquals() {
        // Given
        IntegerWithType integerWithType = new IntegerWithType(9);

        // When
        boolean result = integerWithType.equals(new IntegerWithType(9));

        // Then
        Assertions.assertTrue(result);
    }

    @Test
    public void testEquals_False() {
        // Given
        IntegerWithType integerWithType = new IntegerWithType(9);

        // When
        boolean result = integerWithType.equals(new IntegerWithType(11));

        // Then
        Assertions.assertFalse(result);
    }

}
