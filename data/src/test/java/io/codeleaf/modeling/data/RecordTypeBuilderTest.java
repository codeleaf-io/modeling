package io.codeleaf.modeling.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class RecordTypeBuilderTest {

    @Test
    public void testBuild() {
        // When
        RecordType result = RecordTypeBuilder.create()
                .withField("1", false).integer()
                .withField("2", true).bool()
                .endRecord();

        // Then
        Assertions.assertEquals(Map.of("1", IntegerWithType.TYPE, "2", BooleanWithType.TYPE),
                result.getFieldTypes());
    }
}
