package org.arturocode.moviemanagement.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.arturocode.moviemanagement.presentation.dto.response.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            Exception.class,
            ObjectNotFoundException.class,
            InvalidPasswordException.class,
            MethodArgumentTypeMismatchException.class,
            MethodArgumentNotValidException.class,
            HttpRequestMethodNotSupportedException.class,
            HttpMediaTypeNotSupportedException.class,
            HttpMessageNotReadableException.class
    })
    public ResponseEntity<ApiError> handleAllException(
            Exception exception,
            HttpServletRequest request,
            HttpServletResponse response) {

        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime timestamp = LocalDateTime.now(zoneId);

        if (exception instanceof ObjectNotFoundException objectNotFoundException) {
            return this.handleObjectNotFoundException(objectNotFoundException, request, response, timestamp);
        } else if (exception instanceof InvalidPasswordException invalidPasswordException) {
            return this.handleInvalidPasswordException(invalidPasswordException, request, response, timestamp);
        } else if (exception instanceof MethodArgumentTypeMismatchException methodArgumentTypeMismatchException) {
            return this.handleMethodArgumentTypeMismatchException(methodArgumentTypeMismatchException, request, response, timestamp);
        } else if (exception instanceof MethodArgumentNotValidException methodArgumentNotValidException) {
            return this.handleMethodArgumentNotValidException(methodArgumentNotValidException, request, response, timestamp);
        } else if (exception instanceof HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException) {
            return this.handleHttpRequestMethodNotSupportedException(httpRequestMethodNotSupportedException, request, response, timestamp);
        } else if (exception instanceof HttpMediaTypeNotSupportedException httpMediaTypeNotSupportedException) {
            return this.handleHttpMediaTypeNotSupportedException(httpMediaTypeNotSupportedException, request, response, timestamp);
        } else if (exception instanceof HttpMessageNotReadableException httpMessageNotReadableException) {
            return this.handleHttpMessageNotReadableException(httpMessageNotReadableException, request, response, timestamp);
        } else {
            return this.handleException(exception, request, response, timestamp);
        }
    }

    private ResponseEntity<ApiError> handleHttpMessageNotReadableException(HttpMessageNotReadableException httpMessageNotReadableException, HttpServletRequest request, HttpServletResponse response, LocalDateTime timestamp) {
        int httpStatus = HttpStatus.BAD_REQUEST.value();
        ApiError apiError = new ApiError(
                httpStatus,
                request.getRequestURL().toString(),
                request.getMethod(),
                "Im sorry, the request information could not be found, please check the URL and try again" + httpMessageNotReadableException.getMessage(),
                httpMessageNotReadableException.getMessage(),
                timestamp,
                null
        );
        return ResponseEntity.status(httpStatus).body(apiError);
    }

    private ResponseEntity<ApiError> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException httpMediaTypeNotSupportedException, HttpServletRequest request, HttpServletResponse response, LocalDateTime timestamp) {
        int httpStatus = HttpStatus.UNSUPPORTED_MEDIA_TYPE.value();
        ApiError apiError = new ApiError(
                httpStatus,
                request.getRequestURL().toString(),
                request.getMethod(),
                "Oops! Media Type Not Supported. Check the Content-Type header and try again" + httpMediaTypeNotSupportedException.getSupportedMediaTypes(),
                httpMediaTypeNotSupportedException.getMessage(),
                timestamp,
                null
        );
        return ResponseEntity.status(httpStatus).body(apiError);
    }

    private ResponseEntity<ApiError> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException, HttpServletRequest request, HttpServletResponse response, LocalDateTime timestamp) {
        int httpStatus = HttpStatus.METHOD_NOT_ALLOWED.value();
        ApiError apiError = new ApiError(
                httpStatus,
                request.getRequestURL().toString(),
                request.getMethod(),
                "Oops! Method Not Allowed. Check the HTTP method used in the request and try again",
                httpRequestMethodNotSupportedException.getMessage(),
                timestamp,
                null
        );
        return ResponseEntity.status(httpStatus).body(apiError);
    }

    private ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException, HttpServletRequest request, HttpServletResponse response, LocalDateTime timestamp) {
        int httpStatus = HttpStatus.BAD_REQUEST.value();

        List<ObjectError> errors = methodArgumentNotValidException.getAllErrors();

        List<String> details = errors.stream().map(error -> {
                    if (error instanceof FieldError fieldError) {
                        return fieldError.getField() + ": " + fieldError.getDefaultMessage();
                    }
                    return error.getDefaultMessage();
                })
                .toList();

        ApiError apiError = new ApiError(
                httpStatus,
                request.getRequestURL().toString(),
                request.getMethod(),
                "The request contains invalid data or incomplete parameters" +
                        "Please verify and provide the required information before trying again",
                methodArgumentNotValidException.getMessage(),
                timestamp,
                details
        );
        return ResponseEntity.status(httpStatus).body(apiError);
    }


    private ResponseEntity<ApiError> handleInvalidPasswordException(InvalidPasswordException invalidPasswordException, HttpServletRequest request, HttpServletResponse response, LocalDateTime timestamp) {
        int httpStatus = HttpStatus.BAD_REQUEST.value();
        ApiError apiError = new ApiError(
                httpStatus,
                request.getRequestURL().toString(),
                request.getMethod(),
                "Invalid password: The provided password does not meet the required criteria" + invalidPasswordException.getErrorDescription(),
                invalidPasswordException.getMessage(),
                timestamp,
                null
        );

        return ResponseEntity.status(httpStatus).body(apiError);
    }

    private ResponseEntity<ApiError> handleObjectNotFoundException(Exception objectNotFoundException, HttpServletRequest request, HttpServletResponse response, LocalDateTime timestamp) {
        int httpStatus = HttpStatus.NOT_FOUND.value();
        ApiError apiError = new ApiError(
                httpStatus,
                request.getRequestURL().toString(),
                request.getMethod(),
                "Im sorry, the request information could not be found, please check the URL and try again" + objectNotFoundException.getMessage(),
                objectNotFoundException.getMessage(),
                timestamp,
                null
        );
        return ResponseEntity.status(httpStatus).body(apiError);
    }

    private ResponseEntity<ApiError> handleException(Exception exception, HttpServletRequest request, HttpServletResponse response, LocalDateTime timestamp) {

        int httpStatus = HttpStatus.INTERNAL_SERVER_ERROR.value();

        ApiError apiError = new ApiError(
                httpStatus,
                request.getRequestURL().toString(),
                request.getMethod(),
                "Oops, something went wrong, please try again" + exception.getMessage(),
                exception.getMessage(),
                timestamp,
                null
        );
        return ResponseEntity.status(httpStatus).body(apiError);
    }

    private ResponseEntity<ApiError> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException methodArgumentTypeMismatchException, HttpServletRequest request, HttpServletResponse response, LocalDateTime timestamp) {
        int httpStatus = HttpStatus.BAD_REQUEST.value();
        Object valueRejected = methodArgumentTypeMismatchException.getValue();
        String propertyName = methodArgumentTypeMismatchException.getName();
        ApiError apiError = new ApiError(
                httpStatus,
                request.getRequestURL().toString(),
                request.getMethod(),
                "Invalid request: The provided value: " + valueRejected + " does not have the expected type for the " + propertyName,
                methodArgumentTypeMismatchException.getMessage(),
                timestamp,
                null
        );

        return ResponseEntity.status(httpStatus).body(apiError);
    }

}
