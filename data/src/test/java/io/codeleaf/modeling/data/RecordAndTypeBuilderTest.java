package io.codeleaf.modeling.data;

import org.junit.Assert;
import org.junit.Test;

public class RecordAndTypeBuilderTest {

    @Test
    public void test() {
        ValueWithType<?> valueWithType = ValueAndTypeBuilder.create()
                .beginRecord()
                .requiredField("identifier").identifier("virtual machine", "235325")
                .requiredField("networks").beginList().text("20.51.55.123").text("00.25.1.2")
                .endList()
                .requiredField("active").bool(true)
                .endRecord();

        System.out.println(valueWithType.getType());
        System.out.println(valueWithType.getValue());
    }
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
