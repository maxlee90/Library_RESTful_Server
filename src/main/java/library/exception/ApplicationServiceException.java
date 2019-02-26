package library.exception;

import library.common.ErrorDto;

public class ApplicationServiceException extends RuntimeException {

    private final ErrorDto error;

    public ApplicationServiceException(String message, ErrorDto error, Throwable throwable) {
        super(message, throwable);
        this.error = error;
    }

    public ApplicationServiceException(String message, ErrorDto error) {
        super(message, null);
        this.error = error;
    }

    public ApplicationServiceException(ErrorDto error, Throwable throwable) {
        super(null, throwable);
        this.error = error;
    }

    public ApplicationServiceException(ErrorDto error) {
        this(null, error);
    }

    public ErrorDto getError() {
        return error;
    }

}
