package banquemisr.challenge05.exception;

import org.springframework.http.HttpStatus;

import static banquemisr.challenge05.exception.ExceptionList.RECORD_NOT_FOUND;

public class RecordNotFoundException extends BusinessException{

    public RecordNotFoundException() {
        this(RECORD_NOT_FOUND);
    }

    public RecordNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }

    public RecordNotFoundException(String message, Throwable cause) {
        super(message, cause, HttpStatus.NOT_FOUND);
    }

    public RecordNotFoundException(String message, HttpStatus status) {
        super(message, status);
    }
}
