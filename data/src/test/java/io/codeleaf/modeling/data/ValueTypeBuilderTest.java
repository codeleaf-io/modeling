package io.codeleaf.modeling.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class ValueTypeBuilderTest {

    @Test
    public void testBuild() {
        // When
        ValueType result = ValueTypeBuilder.create().beginRecord()
                .withOptionalField("f1").list().integer()
                .withRequiredField("f2").map().bool()
                .endRecord();

        // Then
        Assertions.assertTrue(result instanceof RecordType);
        Map<String, ValueType> fieldTypes = ((RecordType) result).getFieldTypes();
        Assertions.assertEquals(2, fieldTypes.size());
        Assertions.assertEquals(ListType.create(IntegerWithType.TYPE), fieldTypes.get("f1"));
        Assertions.assertEquals(MapType.create(BooleanWithType.TYPE), fieldTypes.get("f2"));
    }
}
