package com.carshop.mycarshop.common.exception;


import com.carshop.mycarshop.common.exception.aws.AwsTaskException;
import com.carshop.mycarshop.common.exception.member.MemberTaskException;
import com.carshop.mycarshop.common.message.MessageCode;
import com.carshop.mycarshop.common.message.MessageHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

@RestControllerAdvice
@Log4j2
@RequiredArgsConstructor
public class RestExceptionAdvice extends ResponseEntityExceptionHandler {

    private final MessageHandler messageHandler;

    private final DefaultRedirectStrategy defaultRedirectStrategy = new DefaultRedirectStrategy();

    // 사용자 지정 에러 처리
    @ExceptionHandler(value = {AccessDeniedException.class})
    public ResponseEntity<?> handleBoardNotFound(AccessDeniedException e, WebRequest request){

        log.error("request.getUserPrincipal() : " + request.getUserPrincipal());
        ///defaultRedirectStrategy.sendRedirect(request, response, "/auth/login");

        log.error("RestExceptionAdvice - AccessDeniedException!!! ");
        String errorMsg = messageHandler.getMessage(MessageCode.ERROR_ACCESS_DENIED, Collections.emptyList());

        return super.handleExceptionInternal(
                e,
                errorMsg,
                new HttpHeaders(),
                HttpStatus.UNAUTHORIZED,
                request
        );
    }
    @ExceptionHandler(value = {AwsTaskException.class})
    public ResponseEntity<?> handleBoardNotFound(AwsTaskException e, WebRequest request){

        log.error("RestExceptionAdvice - AwsTaskException!!! ");
        return super.handleExceptionInternal(
                e,
                e.getMsg(),
                new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                request
        );
    }
    @ExceptionHandler(value = {MemberTaskException.class})
    public ResponseEntity<?> handleBoardNotFound(MemberTaskException e, WebRequest request){

        log.error("RestExceptionAdvice - MemberTaskException!!! ");
        return super.handleExceptionInternal(
                e,
                e.getMsg(),
                new HttpHeaders(),
                HttpStatus.NOT_FOUND,
                request
        );
    }

    @ExceptionHandler(value = {ItemNotFoundException.class})
    public ResponseEntity<?> handleItemNotFound(ItemNotFoundException e, WebRequest request){

        log.error("RestExceptionAdvice - ItemNotFoundException!!! ");
        log.error(e.getMessage());
        return super.handleExceptionInternal(
                e,
                e.getMessage(),
                new HttpHeaders(),
                HttpStatus.NOT_FOUND,
                request
        );
    }

    @ExceptionHandler(value = {AlreadyRegisterException.class})
    public ResponseEntity<?> handleAlreadyRegisterException(AlreadyRegisterException e, WebRequest request){

        log.error("RestExceptionAdvice - AlreadyRegisterException!!! ");
        log.error(e.getMessage());
        return super.handleExceptionInternal(
                e,
                e.getMessage(),
                new HttpHeaders(),
                HttpStatus.NOT_FOUND,
                request
        );
    }

    @ExceptionHandler(value = {NoSuchElementException.class})
    public ResponseEntity<?> handleBoardNotFound(NoSuchElementException e, WebRequest request){

        log.error("RestExceptionAdvice - NoSuchElementException!!! ");
        log.error(e.getMessage());
        return super.handleExceptionInternal(
                e,
                e.getMessage(),
                new HttpHeaders(),
                HttpStatus.NOT_FOUND,
                request
        );
    }


    // ResponseEntityExceptionHandler 클래스에서 기본 제공하는 메서드를 재 지정 함
    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex,
                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        // 기존 방식으로 처리 되는 것 주석 처리.. stack trace 내용이 다 보여서 공통된 양식으로 보내줄 필요가 있음
        //return super.handleMissingPathVariable(ex, headers, status, request);
        log.error("RestExceptionAdvice - MissingPathVariableException!!! ");
        return super.handleExceptionInternal(
                ex,
                ex.getMessage(),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request
        );
    }

    // HttpMessageNotReadableException은 ResponseEntityExceptionHandler 클래스에서 기본 제공하는 메서드를 재 지정 함
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        //return super.handleHttpMessageNotReadable(ex, headers, status, request);

        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message", "전달값 JSON parse error!!");

        log.error("RestExceptionAdvice - HttpMessageNotReadableException!!! ");
        log.error(ex.getMessage());
        return super.handleExceptionInternal(
                ex,
                errorMap,
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request
        );
    }

    // BindException은 ResponseEntityExceptionHandler 클래스에서 기본 제공하는 메서드를 재 지정 함
    @Override
    protected ResponseEntity<Object> handleBindException(BindException e, HttpHeaders headers, HttpStatus status, WebRequest request) {

        log.error("RestExceptionAdvice - BindException!!! ");

        Map<String, String> errorMap = new HashMap<>();
        if (e.hasErrors()) {
            BindingResult bindingResult = e.getBindingResult();

            StringBuilder errorMessage = new StringBuilder();

            bindingResult.getFieldErrors().forEach(fieldError -> {
                errorMessage.append(fieldError.getField())
                        .append(" : ")
                        .append(fieldError.getCode())
                        .append(" : ")
                        .append(fieldError.getDefaultMessage())
                        .append("\n");
            });
            errorMap.put("message", errorMessage.toString());
        }

        return super.handleExceptionInternal(
                e,
                errorMap,
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request
        );
    }
}
