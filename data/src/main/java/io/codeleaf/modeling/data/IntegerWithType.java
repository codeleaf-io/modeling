package io.codeleaf.modeling.data;

public final class IntegerWithType extends ValueWithType<Long> {

    public static final IntegerType TYPE = new IntegerType();

    public IntegerWithType(long value) {
        super(value, TYPE);
    }

}
