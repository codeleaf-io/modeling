package io.codeleaf.modeling.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ListWithTypeTest {

    @Test
    public void testEquals() {
        // Given
        ListWithType listWithType = ListWithType.create(new IntegerType(),
                new IntegerWithType(1),
                new IntegerWithType(2),
                new IntegerWithType(3));

        // When
        boolean result = listWithType.equals(ListWithType.create(new IntegerType(),
                new IntegerWithType(1),
                new IntegerWithType(2),
                new IntegerWithType(3)));

        // Then
        Assertions.assertTrue(result);
    }

    @Test
    public void testEquals_False() {
        // Given
        ListWithType listWithType = ListWithType.create(new IntegerType(),
                new IntegerWithType(1),
                new IntegerWithType(2),
                new IntegerWithType(3));

        // When
        boolean result = listWithType.equals(ListWithType.create(new IntegerType(),
                new IntegerWithType(1),
                new IntegerWithType(2),
                new IntegerWithType(10_000)));

        // Then
        Assertions.assertFalse(result);
    }

}
