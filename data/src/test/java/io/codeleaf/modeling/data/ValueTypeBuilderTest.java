package io.codeleaf.modeling.data;

import org.junit.Assert;
import org.junit.Test;

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
        Assert.assertTrue(result instanceof RecordType);
        Map<String, ValueType> fieldTypes = ((RecordType) result).getFieldTypes();
        Assert.assertEquals(2, fieldTypes.size());
        Assert.assertEquals(ListType.create(IntegerWithType.TYPE), fieldTypes.get("f1"));
        Assert.assertEquals(MapType.create(BooleanWithType.TYPE), fieldTypes.get("f2"));
    }
}
