package com.ssy.petition.advice;


import com.ssy.petition.common.CommonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.naming.SizeLimitExceededException;

@ControllerAdvice
public class MultipartFileAdvice {

    @ResponseBody
    @ExceptionHandler(value = SizeLimitExceededException.class)
    public CommonResult fileErrorHandler(SizeLimitExceededException e) {
        return CommonResult.failed("上传失败" + e.getMessage());
    }

}
