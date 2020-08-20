package com.murillo.maciel.store.services.exceptions;

public class ObjNotFoundException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public ObjNotFoundException(String message)
    {
        super(message);
    }

    public ObjNotFoundException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
