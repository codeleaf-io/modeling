package io.codeleaf.modeling.service;

public class ServiceException extends Exception {

    private final Service service;

    public ServiceException(Service service, String message) {
        super(message);
        this.service = service;
    }

    public ServiceException(Service service, Throwable cause) {
        super(cause);
        this.service = service;
    }

    public ServiceException(Service service, String message, Throwable cause) {
        super(message, cause);
        this.service = service;
    }

    public Service getService() {
        return service;
    }

}
