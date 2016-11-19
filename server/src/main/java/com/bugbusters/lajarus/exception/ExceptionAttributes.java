package com.bugbusters.lajarus.exception;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;

/**
 *
 * @author Vasilis Naskos <vnaskos.com>
 */
public interface ExceptionAttributes {
    
    Map<String, Object> getExceptionAttributes(Exception exception,
            HttpServletRequest request, HttpStatus httpStatus);
    
}
