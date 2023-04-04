package io.codeleaf.modeling.data;

import org.junit.Assert;
import org.junit.Test;

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
        Assert.assertTrue(result instanceof RecordWithType);
        Map<String, ValueWithType<?>> fields = ((RecordWithType) result).getValue();
        Assert.assertEquals(2, fields.size());
        Assert.assertTrue(fields.get("f1") instanceof ListWithType);
        ListWithType list = (ListWithType) fields.get("f1");
        Assert.assertEquals(3, list.getValue().size());
        Assert.assertEquals(IntegerWithType.TYPE, list.getType().getItemValueType());
        Assert.assertEquals(new IntegerWithType(1), list.getValue().get(0));
        Assert.assertEquals(new IntegerWithType(2), list.getValue().get(1));
        Assert.assertEquals(new IntegerWithType(3), list.getValue().get(2));
        Assert.assertTrue(fields.get("f2") instanceof MapWithType);
        MapWithType map = (MapWithType) fields.get("f2");
        Assert.assertEquals(BooleanWithType.TYPE, map.getType().getEntryValueType());
        Assert.assertEquals(2, map.getValue().size());
        Assert.assertEquals(new BooleanWithType(true), map.getValue().get("yes"));
        Assert.assertEquals(new BooleanWithType(false), map.getValue().get("no"));
    }
}
