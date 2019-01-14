package io.codeleaf.modeling.data;

public final class BooleanWithType extends ValueWithType<Boolean> {

    public static final BooleanType TYPE = new BooleanType();

    public BooleanWithType(boolean value) {
        super(value, TYPE);
    }

}
