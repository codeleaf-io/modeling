package io.codeleaf.modeling.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RecordWithTypeBuilderTest {

    @Test
    public void testBuild() {
        // Given
        RecordType recordType = RecordTypeBuilder.create()
                .withField("1", false).integer()
                .withField("2", true).bool()
                .endRecord();

        // When
        RecordWithType result = RecordWithTypeBuilder.create(recordType)
                .field("1").integer(5)
                .field("2").bool(true)
                .endRecord();

        // Then
        Assertions.assertEquals(5L, result.getValue().get("1").getValue());
        Assertions.assertEquals(true, result.getValue().get("2").getValue());
    }
}
