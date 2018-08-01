package com.lzh.conf.handler;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 全局异常处理。
 *
 * @author axeon
 */
@RestController
@Api(value = "GlobalExceptionHandler", description = "全局异常处理(前端不需绑接口)")
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(RuntimeException.class)
    public void runtimeException(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "程序运行错误");
    }

    @ExceptionHandler(NullPointerException.class)
    public void nullException(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "空指针异常");
    }

    /*@ExceptionHandler(TransactionException.class)
    public void expiredJwtTokenException(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "数据库事务异常");
    }*/

    @ExceptionHandler(SQLException.class)
    public void databaseException(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "数据库错误");
    }

    @ExceptionHandler(Exception.class)
    public void unknownException(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "服务器内部错误");
    }
}