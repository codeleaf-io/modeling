package io.codeleaf.modeling.data;

public final class FloatWithType extends ValueWithType<Double> {

    public static final FloatType TYPE = new FloatType();

    public FloatWithType(double value) {
        super(value, TYPE);
    }

}
