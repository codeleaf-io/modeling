package io.codeleaf.modeling.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class ValueAndTypeBuilderTest {

    @Test
    public void testBuild() {
        // When
        ValueWithType<?> result = ValueAndTypeBuilder.create()
                .beginRecord()
                .field("f1").beginList().integer(1).integer(2).integer(3).endList()
                .field("f2").beginMap().key("yes").bool(true).key("no").bool(false).endMap()
                .endRecord();

        // Then
        Assertions.assertTrue(result instanceof RecordWithType);
        Map<String, ValueWithType<?>> fields = ((RecordWithType) result).getValue();
        Assertions.assertEquals(2, fields.size());
        Assertions.assertTrue(fields.get("f1") instanceof ListWithType);
        ListWithType list = (ListWithType) fields.get("f1");
        Assertions.assertEquals(3, list.getValue().size());
        Assertions.assertEquals(IntegerWithType.TYPE, list.getType().getItemValueType());
        Assertions.assertEquals(new IntegerWithType(1), list.getValue().get(0));
        Assertions.assertEquals(new IntegerWithType(2), list.getValue().get(1));
        Assertions.assertEquals(new IntegerWithType(3), list.getValue().get(2));
        Assertions.assertTrue(fields.get("f2") instanceof MapWithType);
        MapWithType map = (MapWithType) fields.get("f2");
        Assertions.assertEquals(BooleanWithType.TYPE, map.getType().getEntryValueType());
        Assertions.assertEquals(2, map.getValue().size());
        Assertions.assertEquals(new BooleanWithType(true), map.getValue().get("yes"));
        Assertions.assertEquals(new BooleanWithType(false), map.getValue().get("no"));
    }
}
