package vn.kienlongbank.base.core;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vn.kienlongbank.base.core.enums.CommonResponseCode;
import vn.kienlongbank.base.core.exception.BusinessException;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@Log4j2
@RequiredArgsConstructor
public class BaseExceptionController {

    private final HttpServletRequest request;

    public static String getTrace(Exception e) {
        try {
            return String.format("%s:%s", e.getStackTrace()[0].getClassName(), e.getStackTrace()[0].getLineNumber());
        } catch (Exception ignored) {
        }
        return ":";
    }

    @ExceptionHandler({BusinessException.class})
    public ResponseEntity<?> handleBusinessException(BusinessException e) {
        log.error("Business Error: {}, trace: {}", e.getMessage(), getTrace(e));
        String requestLanguage = request.getHeader(HttpHeaders.ACCEPT_LANGUAGE);

        return ResponseEntity.ok(new ResponseBase<>(e.getCode(), e.getMessage()));
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<?> handleException(Exception e) {
        log.error("", e);
        return new ResponseEntity<>(new ResponseBase<>(CommonResponseCode.COMMON_ERROR.getCode(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
        MethodArgumentNotValidException.class,
        BindException.class
    })
    public ResponseEntity<?> handleArgumentInvalidException(BindException e) {
        Map<String, List<String>> errors = new HashMap<>();
        final String[] errorFields = {""};
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, List.of(Optional.ofNullable(errorMessage).orElse("")));
            if (!errorFields[0].contains(fieldName)) {
                errorFields[0] = errorFields[0] + fieldName + ", ";
            }
        });

        log.debug("Invalid param: {}", errors);
        String nameParamInvalidString = errorFields[0];
        ResponseBase<?> responseBase = new ResponseBase<>(errors);
        responseBase.setCode(CommonResponseCode.INVALID_PARAM.getCode());
        responseBase.setMessage(CommonResponseCode.INVALID_PARAM.getMessage() + " : " + nameParamInvalidString.substring(0, nameParamInvalidString.length() - 2));

        return new ResponseEntity<>(responseBase, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDeniedException(AccessDeniedException e) {
        return new ResponseEntity<>(
            new ResponseBase<>(
                CommonResponseCode.ACCESS_DENIED.getCode(),
                CommonResponseCode.ACCESS_DENIED.getMessage()),
            HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseBase<?> handleConstraintViolationException(ConstraintViolationException e) {
        Map<String, List<String>> errors = new HashMap<>();
        e.getConstraintViolations().forEach((error) -> {
            String fieldName = error.getPropertyPath().toString().replaceFirst("^.+\\.", "");
            String errorMessage = error.getMessage();
            errors.put(fieldName, List.of(Optional.ofNullable(errorMessage).orElse("")));
        });

        ResponseBase<?> responseBase = new ResponseBase<>(errors);
        responseBase.setCode(CommonResponseCode.INVALID_PARAM.getCode());
        responseBase.setMessage(CommonResponseCode.INVALID_PARAM.getMessage());

        return responseBase;
    }

//    @ExceptionHandler({PropertyReferenceException.class})
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ResponseBase<?> handlePropertyReferenceException(PropertyReferenceException e) {
//        ResponseBase<?> responseBase = new ResponseBase<>(Map.of(e.getPropertyName(), String.format("Field %s is not accepted", e.getPropertyName())));
//        responseBase.setCode(ResponseCode.INVALID_PARAM.getCode());
//        responseBase.setMessage(ResponseCode.INVALID_PARAM.getMessage());
//        return responseBase;
//    }

}
