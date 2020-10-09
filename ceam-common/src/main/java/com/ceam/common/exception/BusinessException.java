package com.ceam.common.exception;

/**
 * @author CeaM
 * @Description 业务异常
 * @date 2020/10/9 15:24
 */
public class BusinessException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    protected final String message;

    public BusinessException(String message)
    {
        this.message = message;
    }

    public BusinessException(String message, Throwable e)
    {
        super(message, e);
        this.message = message;
    }

    @Override
    public String getMessage()
    {
        return message;
    }
}
