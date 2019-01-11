package io.codeleaf.modeling.data;

public final class TimestampWithType extends ValueWithType<Long> {

    public static final TimestampType TYPE = new TimestampType();

    public TimestampWithType(long value) {
        super(value, TYPE);
    }

}
