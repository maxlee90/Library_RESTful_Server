package library.common;

import java.io.Serializable;

public class ErrorDto implements Serializable {

    private final ErrorCode errorCode;
    private final String message;
    private final String displayMessage;

    public final static ErrorDto INTERNAL_SERVER_ERROR = new ErrorDto(ErrorCode.INTERNAL_SERVER_ERROR,
            "Internal server error");

    public ErrorDto(ErrorCode errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
        this.displayMessage = null;
    }

    public ErrorDto(ErrorCode errorCode, String message, String displayMessage) {
        this.errorCode = errorCode;
        this.message = message;
        this.displayMessage = displayMessage;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

    public String getDisplayMessage() {
        return displayMessage;
    }
}