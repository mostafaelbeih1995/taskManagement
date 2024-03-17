package banquemisr.challenge05.exception.handler;

import banquemisr.challenge05.exception.BusinessException;
import banquemisr.challenge05.exception.ExceptionList;
import banquemisr.challenge05.exception.NoUserFoundException;
import banquemisr.challenge05.model.payload.ApiResponse;
import banquemisr.challenge05.model.payload.ErrorPayload;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

//import java.nio.file.AccessDeniedException;
import java.util.*;

@ControllerAdvice
public class RestHandleException {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationErrors(MethodArgumentNotValidException ex) {

        Map<String, Object> errors = new HashMap<>();
        String message = prepareMessages(ExceptionList.INVALID_ARGUMENT);
        return handleErrorResponse(message, ex.getClass().getSimpleName(), errors, HttpStatus.BAD_REQUEST);
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(BusinessException ex, WebRequest request) {

        Map<String, Object> errors = new HashMap<>();
        if (ex.getDetails() != null) {
            for (Map.Entry<String, String> error : ex.getDetails().entrySet()) {
                errors.putAll(loadErrorDetails(error.getKey(), error.getValue(), ex));
            }
        }
        String message = prepareMessages(ex.getMessage());
        return handleErrorResponse(message, ex.getClass().getSimpleName(), errors, ex.getStatus());
    }
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex) {
        Map<String, Object> errors = new HashMap<>();
        String message = prepareMessages(ExceptionList.ACCESS_DENIED);
        return handleErrorResponse(message, ex.getClass().getSimpleName(), errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoUserFoundException.class)
    public ResponseEntity<Object> handleNoUserFoundException(NoUserFoundException ex, WebRequest request) {

        Map<String, Object> errors = new HashMap<>();
        if (ex.getDetails() != null) {
            for (Map.Entry<String, String> error : ex.getDetails().entrySet()) {
                errors.putAll(loadErrorDetails(error.getKey(), error.getValue(), ex));
            }
        }
        String message = prepareMessages(ex.getMessage());
        return handleErrorResponse(message, ex.getClass().getSimpleName(), errors, ex.getStatus());
    }
    private ResponseEntity<Object> handleErrorResponse(String enMessage, String type, Map<String, Object> errors, HttpStatus status) {

        return new ResponseEntity<>(
                ApiResponse.builder()
                        .success(Boolean.FALSE)
                        .errors(ErrorPayload.builder()
                                .enMessage(enMessage)
                                .type(type).details(errors).detailsAsString(errors == null ? "" : errors.toString()).build())
                        .code(status.value())
                        .build(), status);
    }

    private ResponseEntity<Object> handleErrorResponse(HttpStatus status, String errorMsg){

        return new ResponseEntity<>(
                ApiResponse.builder()
                        .success(Boolean.FALSE)
                        .errors(ErrorPayload.builder()
                                .enMessage(errorMsg)
                                .build())
                        .code(status.value())
                        .build(),status);
    }

    private Map<String, Object> loadErrorDetails(String key, String value, Exception ex) {

        Map<String, Object> errors = new HashMap<>();
        String message = prepareMessages(value);
        errors.put(key, message);
        return errors;
    }

    private String prepareMessages(String error) {

        try {
            return getMessage(error, "en");
        } catch (NoSuchMessageException messageEx) {
            String en;
            en = getMessage(ExceptionList.UNEXPECTED_ERROR, "en");
            return en;
        }
    }
    private String getMessage(String error, String language) {

        if ("en".equalsIgnoreCase(language))
            return messageSource.getMessage(error, null, new Locale("en"));
        else
            return messageSource.getMessage(error, null, new Locale("ar"));
    }


}
