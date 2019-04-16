package info.ivicel.tumoblog.admin.controlleradvice;

import info.ivicel.tumoblog.admin.dto.ResponseResult;
import info.ivicel.tumoblog.admin.enums.ResultEnums;
import info.ivicel.tumoblog.admin.exception.OperationException;
import info.ivicel.tumoblog.admin.exception.PageNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ErrorHandlerControllAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ErrorHandlerControllAdvice.class);

    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseResult onConstraintVoilationException(Exception e) {
        logger.warn("提交的参数错误: ", e);
        return ResponseResult.of(ResultEnums.ERROR);
    }

    @ExceptionHandler(OperationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseResult onOperationException(OperationException e) {
        logger.warn("服务出错", e);
        return ResponseResult.of(ResultEnums.INNER_ERROR);
    }

    @ExceptionHandler(PageNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String onPageNotFoundException(HttpServletRequest request, PageNotFoundException e) {
        logger.info(String.format("找不到页面: %s, %s", request.getRequestURI(), e.getMessage()));
        return "404";
    }
}
