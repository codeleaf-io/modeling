package io.codeleaf.modeling.data;

public class MalformedValueException extends Exception {

    private final Object value;

    public MalformedValueException(Object value, String message) {
        super(message);
        this.value = value;
    }

    public MalformedValueException(Object value, Throwable cause) {
        super(cause);
        this.value = value;
    }

    public MalformedValueException(Object value, String message, Throwable cause) {
        super(message, cause);
        this.value = value;
    }

    public Object getValue() {
        return value;
    }
}
