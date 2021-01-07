package com.example.server.aop;

import com.example.server.common.result.enums.ResultEnum;
import com.example.server.common.result.model.Result;
import com.example.server.common.result.utils.ResultUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class MyResponseAdvice  implements ResponseBodyAdvice<Object> {

    private final ObjectMapper objectMapper;

    public MyResponseAdvice(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * Whether this component supports the given controller method return type
     * and the selected {@code HttpMessageConverter} type.
     *
     * @param returnType  the return type
     * @param converterType the selected converter type
     * @return {@code true} if {@link #beforeBodyWrite} should be invoked;
     * {@code false} otherwise
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    /**
     * Invoked after an {@code HttpMessageConverter} is selected and just before
     * its write method is invoked.
     *
     * @param body         the body to be written
     * @param returnType      the return type of the controller method
     * @param selectedContentType  the content type selected through content negotiation
     * @param selectedConverterType the converter type selected to write to the response
     * @param request        the current request
     * @param response       the current response
     * @return the body that was passed in or a modified (possibly new) instance
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if(body instanceof Result){ //发生异常之后，异常处理器里面返回的已经是Result了
            return body;
        }else if(body instanceof String){ //String属于特殊情况，需要单独处理，否则会报错
            try {
                return objectMapper.writeValueAsString(ResultUtils.success(body));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return ResultUtils.error(ResultEnum.UNKNOWN_ERROR.getCode(), e.getMessage());
            }
        }
        return ResultUtils.success(body);
    }
}
