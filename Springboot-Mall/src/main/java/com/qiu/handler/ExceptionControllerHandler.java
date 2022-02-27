package com.qiu.handler;

import cn.dev33.satoken.exception.DisableLoginException;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import com.qiu.util.general.CommonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 全局异常处理
 * @author Captain
 */
@RestControllerAdvice
public class ExceptionControllerHandler {
    @ExceptionHandler(NotLoginException.class)
    public CommonResult handlerNotLoginException(NotLoginException e) {
        String message;
        switch (e.getType()) {
            case NotLoginException.NOT_TOKEN:
                message = "未提供身份验证";
                break;
            case NotLoginException.INVALID_TOKEN:
                message = "身份验证无效";
                break;
            case NotLoginException.TOKEN_TIMEOUT:
                message = "身份验证已过期";
                break;
            case NotLoginException.BE_REPLACED:
                message = "账号已被顶下线";
                break;
            case NotLoginException.KICK_OUT:
                message = "账号已被踢下线";
                break;
            default:
                message = "当前会话未登录";
                break;
        }
        return CommonResult.error(message);
    }

    @ExceptionHandler(DisableLoginException.class)
    public CommonResult handlerDisableLoginException() {
        return CommonResult.error( "此账号已被封禁");
    }

    @ExceptionHandler(NotPermissionException.class)
    public CommonResult handlerNotPermissionException() {
        return CommonResult.error( "无此权限");
    }
}
