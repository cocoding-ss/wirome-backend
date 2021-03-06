package me.apjung.backend.api.advisor;

import me.apjung.backend.api.exception.DuplicatedEmailException;
import me.apjung.backend.api.exception.auth.InvalidGrantException;
import me.apjung.backend.api.exception.auth.UnsupportedGrantTypeException;
import me.apjung.backend.component.custommessagesourceresolver.CustomMessageSourceResolver;
import me.apjung.backend.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthExceptionHandler extends BaseExceptionHandler {
    public AuthExceptionHandler(CustomMessageSourceResolver customMessageSourceResolver) {
        super(customMessageSourceResolver);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DuplicatedEmailException.class)
    public ErrorResponse duplicatedEmailException(DuplicatedEmailException exception) {
        return getErrorResponse(exception);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UnsupportedGrantTypeException.class)
    public ErrorResponse duplicatedEmailException(UnsupportedGrantTypeException exception) {
        return getErrorResponse(exception);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidGrantException.class)
    public ErrorResponse duplicatedEmailException(InvalidGrantException exception) {
        return getErrorResponse(exception);
    }
}
