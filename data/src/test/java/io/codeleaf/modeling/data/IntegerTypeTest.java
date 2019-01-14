package io.codeleaf.modeling.data;

import org.junit.Assert;
import org.junit.Test;

public class IntegerTypeTest {

    @Test
    public void testEquals() {
        // Given
        ValueType integerType = new IntegerType();

        // When
        boolean result = integerType.equals(new IntegerType());

        // Then
        Assert.assertTrue(result);
    }

    @Test
    public void testEquals_NotEquals() {
        // Given
        ValueType integerType = new IntegerType();

        // When
        boolean result = integerType.equals(new BooleanType());

        // Then
        Assert.assertFalse(result);
    }

}
