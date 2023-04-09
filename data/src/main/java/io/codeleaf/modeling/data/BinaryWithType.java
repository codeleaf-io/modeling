package io.codeleaf.modeling.data;

public final class BinaryWithType extends ValueWithType<byte[]> {

    public static final BinaryType TYPE = new BinaryType();

    public BinaryWithType(byte[] value) {
        super(value, TYPE);
    }

}
