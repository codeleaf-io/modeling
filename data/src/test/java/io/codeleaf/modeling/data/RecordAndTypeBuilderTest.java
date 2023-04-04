package io.codeleaf.modeling.data;

import org.junit.Assert;
import org.junit.Test;

public class RecordAndTypeBuilderTest {

    @Test
    public void testBuild() {
        // When
        RecordWithType result = RecordAndTypeBuilder.create()
                .field("1").integer(5)
                .field("2").bool(true)
                .endRecord();

        // Then
        Assert.assertEquals(5L, result.getValue().get("1").getValue());
        Assert.assertEquals(true, result.getValue().get("2").getValue());
    }
}
