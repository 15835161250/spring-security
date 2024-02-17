package zone.security.satoken.exception;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import zone.security.satoken.domain.result.AjaxResult;

import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        String message = Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage();
        return AjaxResult.error(message);
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(NotLoginException.class)
    public Object handleNotLoginException(NotLoginException e) {
        String message = Objects.requireNonNull(e.getMessage());
        log.info("NotLoginException:{}", message);
        return AjaxResult.error(401, "没有权限");
    }

    @ExceptionHandler(NotPermissionException.class)
    public Object handleNotPermissionException(NotPermissionException e) {
        String message = Objects.requireNonNull(e.getMessage());
        log.info("NotPermissionException:{}", e.getMessage());
        return AjaxResult.error(401, message);
    }


}
