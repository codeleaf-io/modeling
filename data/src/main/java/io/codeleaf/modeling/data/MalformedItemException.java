package io.codeleaf.modeling.data;

public class MalformedItemException extends MalformedValueException {

    private final int index;

    public MalformedItemException(int index, Object value, String message) {
        super(value, message);
        this.index = index;
    }

    public MalformedItemException(int index, Object value, Throwable cause) {
        super(value, cause);
        this.index = index;
    }

    public MalformedItemException(int index, Object value, String message, Throwable cause) {
        super(value, message, cause);
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
