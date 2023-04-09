package io.codeleaf.modeling.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MapWithTypeTest {

    @Test
    public void testEquals() {
        // Given
        MapWithType listWithType = new MapWithType.Builder(MapType.create(new IntegerType()))
                .withEntry("1", new IntegerWithType(1))
                .withEntry("2", new IntegerWithType(2))
                .withEntry("3", new IntegerWithType(3))
                .build();

        // When
        boolean result = listWithType.equals(new MapWithType.Builder(MapType.create(new IntegerType()))
                .withEntry("1", new IntegerWithType(1))
                .withEntry("2", new IntegerWithType(2))
                .withEntry("3", new IntegerWithType(3))
                .build());

        // Then
        Assertions.assertTrue(result);
    }

    @Test
    public void testEquals_False() {
        // Given
        MapWithType listWithType = new MapWithType.Builder(MapType.create(new IntegerType()))
                .withEntry("1", new IntegerWithType(1))
                .withEntry("2", new IntegerWithType(2))
                .withEntry("3", new IntegerWithType(3))
                .build();

        // When
        boolean result = listWithType.equals(new MapWithType.Builder(MapType.create(new IntegerType()))
                .withEntry("1", new IntegerWithType(1))
                .withEntry("2", new IntegerWithType(2))
                .withEntry("10_000", new IntegerWithType(10_000))
                .build());

        // Then
        Assertions.assertFalse(result);
    }

}
