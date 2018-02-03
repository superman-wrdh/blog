package com.hc.http;

import com.hc.exception.ApplicationException;

/**
 * http exception
 */
public class HttpException extends ApplicationException {
    public HttpException(String msg) {
        super(msg);
    }
    public HttpException(String msg, Throwable cause) {
        super(msg, cause);
    }
}