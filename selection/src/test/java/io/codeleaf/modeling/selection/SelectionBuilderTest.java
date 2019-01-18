package io.codeleaf.modeling.selection;

import org.junit.Assert;
import org.junit.Test;

public class SelectionBuilderTest {

    @Test
    public void build() {
        // When
        Selection result = SelectionBuilder.create(String.class, String.class).field("f1").equalTo("foo");

        // Then
        Assert.assertTrue(result instanceof FieldSelection);
        Selection innerSelection = ((FieldSelection<?>) result).getSelection();
        Assert.assertTrue(innerSelection instanceof EqualToSelection);
        Assert.assertEquals("foo", ((EqualToSelection<?>) innerSelection).getValue());
    }
}
