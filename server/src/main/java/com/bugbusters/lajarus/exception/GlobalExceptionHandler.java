package com.bugbusters.lajarus.exception;

import java.util.Map;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Vasilis Naskos <vnaskos.com>
 */
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
    
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Map<String, Object>> handleException(
            Exception exception, HttpServletRequest request) {
        logger.error("< Handle exception");
        logger.error("- Exception: ", exception);
        
        ExceptionAttributes exceptionAttributes = new DefaultExceptionAttributes();
        
        Map<String, Object> responseBody = exceptionAttributes
                .getExceptionAttributes(exception, request,
                        HttpStatus.INTERNAL_SERVER_ERROR);
        
        logger.error("> Handle exception");
        
        return new ResponseEntity<>(responseBody, 
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(value = NoResultException.class)
    public ResponseEntity<Map<String, Object>> handleNoResultException(
            NoResultException exception, HttpServletRequest request) {
        logger.info("< Handle noResultException");
        
        ExceptionAttributes exceptionAttributes = new DefaultExceptionAttributes();
        
        Map<String, Object> responseBody = exceptionAttributes
                .getExceptionAttributes(exception, request,
                        HttpStatus.NOT_FOUND);
        
        logger.info("> Handle noResultException");
        
        return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
    }
}
