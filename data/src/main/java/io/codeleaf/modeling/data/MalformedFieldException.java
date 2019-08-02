package io.codeleaf.modeling.data;

public class MalformedFieldException extends MalformedValueException {

    private final String field;

    public MalformedFieldException(String field, Object value, String message) {
        super(value, message);
        this.field = field;
    }

    public MalformedFieldException(String field, Object value, Throwable cause) {
        super(value, cause);
        this.field = field;
    }

    public MalformedFieldException(String field, Object value, String message, Throwable cause) {
        super(value, message, cause);
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
