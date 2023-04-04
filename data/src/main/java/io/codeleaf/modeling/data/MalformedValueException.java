package io.codeleaf.modeling.data;

import java.util.List;

public class MalformedValueException extends NumberFormatException {

    static final long serialVersionUID = -1520562029129494856L;

    private final Object value;

    public MalformedValueException(Object value, String message) {
        super(message);
        this.value = value;
    }

    public MalformedValueException(Object value, Throwable cause) {
        initCause(cause);
        this.value = value;
    }

    public MalformedValueException(Object value, String message, Throwable cause) {
        super(message);
        initCause(cause);
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public static void isWellFormed(List<MalformedValueException> exceptions) throws MalformedValueException {
        if (exceptions == null || exceptions.isEmpty()) {
            return;
        }
        int exceptionCount = exceptions.size();
        MalformedValueException exception = exceptions.get(0);
        for (int i = 1; i < exceptionCount; i++) {
            exception.addSuppressed(exceptions.get(i));
        }
        throw exception;
    }

}
