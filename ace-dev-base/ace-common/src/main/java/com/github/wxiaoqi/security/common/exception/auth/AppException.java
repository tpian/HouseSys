package com.github.wxiaoqi.security.common.exception.auth;


import com.github.wxiaoqi.security.common.constant.CommonConstants;
import com.github.wxiaoqi.security.common.exception.BaseException;

/**
 * Created by ace on 2017/9/8.
 */
public class AppException extends BaseException {
    public AppException(String message) {
        super(message, CommonConstants.EX_TOKEN_ERROR_CODE);
    }
}
