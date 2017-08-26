package com.bugbusters.lajarus.exception;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;

/**
 *
 * @author Vasilis Naskos
 */
public class DefaultExceptionAttributes implements ExceptionAttributes {

    private final static String TIMESTAMP = "timestamp";
    private final static String STATUS = "status";
    private final static String ERROR = "error";
    private final static String EXCEPTION = "exception";
    private final static String MESSAGE = "message";
    private final static String PATH = "path";
    
    @Override
    public Map<String, Object> getExceptionAttributes(Exception exception,
            HttpServletRequest request, HttpStatus httpStatus) {
        
        Map<String, Object> exceptionAttributes = new LinkedHashMap<>();
        
        exceptionAttributes.put(TIMESTAMP, new Date());
        addHttpStatus(exceptionAttributes, httpStatus);
        addExceptionDetails(exceptionAttributes, exception);
        addPath(exceptionAttributes, request);
        
        return exceptionAttributes;
    }
    
    private void addHttpStatus(Map<String, Object> exceptionAttributes,
            HttpStatus httpStatus) {
        exceptionAttributes.put(STATUS, httpStatus.value());
        exceptionAttributes.put(ERROR, httpStatus.getReasonPhrase());
    }
    
    private void addExceptionDetails(Map<String, Object> exceptionAttributes,
            Exception exception) {
        exceptionAttributes.put(EXCEPTION, exception.getClass().getName());
        exceptionAttributes.put(MESSAGE, exception.getMessage());
    }
    
    private void addPath(Map<String, Object> exceptionAttributes,
            HttpServletRequest request) {
        exceptionAttributes.put(PATH, request.getServletPath());
    }
    
}
