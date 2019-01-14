package io.codeleaf.modeling.data;

import org.junit.Assert;
import org.junit.Test;

public class IntegerWithTypeTest {

    @Test
    public void testEquals() {
        // Given
        IntegerWithType integerWithType = new IntegerWithType(9);

        // When
        boolean result = integerWithType.equals(new IntegerWithType(9));

        // Then
        Assert.assertTrue(result);
    }

    @Test
    public void testEquals_False() {
        // Given
        IntegerWithType integerWithType = new IntegerWithType(9);

        // When
        boolean result = integerWithType.equals(new IntegerWithType(11));

        // Then
        Assert.assertFalse(result);
    }

}
