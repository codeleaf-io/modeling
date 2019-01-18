package io.codeleaf.modeling.data;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class ValueWithTypeBuilderTest {

    @Test
    public void testBuild() {
        // Given
        ValueType valueType = ValueTypeBuilder.create().beginRecord().withOptionalField("f1").list().integer().endRecord();

        // When
        ValueWithType<?> result = ValueWithTypeBuilder.create(valueType)
                .beginRecord()
                    .field("f1").beginList().integer(1).integer(2).integer(3).endList()
                .endRecord();

        // Then
        Assert.assertEquals(valueType, result.getType());
        Assert.assertTrue(result instanceof RecordWithType);
        Map<String, ValueWithType<?>> fields = ((RecordWithType) result).getValue();
        Assert.assertEquals(1, fields.size());
        Assert.assertTrue(fields.get("f1") instanceof ListWithType);
        ListWithType list = (ListWithType) fields.get("f1");
        Assert.assertEquals(3, list.getValue().size());
        Assert.assertEquals(new IntegerWithType(1), list.getValue().get(0));
        Assert.assertEquals(new IntegerWithType(2), list.getValue().get(1));
        Assert.assertEquals(new IntegerWithType(3), list.getValue().get(2));
    }
}
