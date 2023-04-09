package io.codeleaf.modeling.selection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SelectionBuilderTest {

    @Test
    public void build() {
        // When
        Selection result = SelectionBuilder.create(String.class, String.class).field("f1").equalTo("foo");

        // Then
        Assertions.assertTrue(result instanceof FieldSelection);
        Selection innerSelection = ((FieldSelection<?>) result).getSelection();
        Assertions.assertTrue(innerSelection instanceof EqualToSelection);
        Assertions.assertEquals("foo", ((EqualToSelection<?>) innerSelection).getValue());
    }
}
