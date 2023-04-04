package io.codeleaf.modeling.data;

public class MalformedEntryException extends MalformedValueException {

    private final String key;

    public MalformedEntryException(String key, Object value, String message) {
        super(value, message);
        this.key = key;
    }

    public MalformedEntryException(String key, Object value, Throwable cause) {
        super(value, cause);
        this.key = key;
    }

    public MalformedEntryException(String key, Object value, String message, Throwable cause) {
        super(value, message, cause);
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
