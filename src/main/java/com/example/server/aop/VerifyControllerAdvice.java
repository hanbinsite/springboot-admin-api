package com.example.server.aop;

import com.example.server.common.result.enums.ResultEnum;
import com.example.server.common.result.model.Result;
import com.example.server.common.result.utils.ResultUtils;
import com.example.server.exception.ApiException;
import com.example.server.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.*;

@RestControllerAdvice
@Slf4j
public class VerifyControllerAdvice {

    // 捕获方法参数校验异常
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public Object constraintViolationExceptionHandler(ConstraintViolationException e){
        Set<ConstraintViolation<?>> message = e.getConstraintViolations();
//        HashMap<String, Object> map = new HashMap<>();
        List<String> msgList = new ArrayList<>();
        message.stream().forEach(msg -> {
//            String path = msg.getPropertyPath().toString();
//            String field = path.substring(path.indexOf(".")+1);
//            map.put(field,msg.getMessageTemplate());
            msgList.add(msg.getMessageTemplate());
        });
//        return ResponseEntity.ok(map);
        return ResultUtils.error(ResultEnum.FAIL.getCode(), msgList.get(0).toString());
    }

    // 捕获实体参数校验异常  ResponseEntity
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Object resolveMethodArgumentNotValidException(MethodArgumentNotValidException e){
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
//        HashMap<String, Object> map = new HashMap<>();
        List<String> msgList = new ArrayList<>();
        allErrors.stream().forEach(error -> {
            FieldError fieldError = (FieldError) error;
//            map.put(fieldError.getField(), fieldError.getDefaultMessage());
            msgList.add(fieldError.getDefaultMessage());
        });
        return ResultUtils.error(ResultEnum.FAIL.getCode(), msgList.get(0).toString());

    }

}
