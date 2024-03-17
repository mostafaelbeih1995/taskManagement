package banquemisr.challenge05.exception;

import org.springframework.core.MethodParameter;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

public class BadMethodArgumentException extends MethodArgumentNotValidException {

    public BadMethodArgumentException(MethodParameter parameter, BindingResult bindingResult) {
        super(parameter, bindingResult);
    }
}
