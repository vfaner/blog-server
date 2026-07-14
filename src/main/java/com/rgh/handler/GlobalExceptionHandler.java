package com.rgh.handler;

import com.rgh.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

/**
 * @author: rgh  mail:817094@qq.com
 * @description: 全局异常处理
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 实体校验异常捕获
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResultUtil handler(MethodArgumentNotValidException e) {

        BindingResult result = e.getBindingResult();
        ObjectError objectError = new ObjectError(result.getObjectName(), result.toString());
        final Optional<ObjectError> first = result.getAllErrors().stream().findFirst();
        if(first.isPresent()){
            objectError = first.get();
        }

        log.error("实体校验异常：-{}-{}", objectError.getObjectName(),objectError.getDefaultMessage());
        return ResultUtil.fail(HttpStatus.BAD_REQUEST.value(),objectError.getObjectName()+objectError.getDefaultMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResultUtil handler(IllegalArgumentException e) {
        log.error("Assert异常：----------------{}", e.getMessage());
        return ResultUtil.fail(HttpStatus.BAD_REQUEST.value(),e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = RuntimeException.class)
    public ResultUtil handler(RuntimeException e) {
        log.error("运行时异常：----------------{}", e.getMessage(), e);
        return ResultUtil.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public ResultUtil handler(Exception e) {
        log.error("未知异常：----------------{}", e.getMessage(), e);
        return ResultUtil.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "服务器内部错误");
    }

}

