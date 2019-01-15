package io.codeleaf.modeling.service;

public class ProcedureExecutionException extends ServiceException {

    private final Procedure procedure;

    public ProcedureExecutionException(Service service, Procedure procedure, String message) {
        super(service, message);
        this.procedure = procedure;
    }

    public ProcedureExecutionException(Service service, Procedure procedure, Throwable cause) {
        super(service, cause);
        this.procedure = procedure;
    }

    public ProcedureExecutionException(Service service, Procedure procedure, String message, Throwable cause) {
        super(service, message, cause);
        this.procedure = procedure;
    }

    public Procedure getProcedure() {
        return procedure;
    }

}
