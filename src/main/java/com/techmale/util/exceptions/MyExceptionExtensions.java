package com.techmale.util.exceptions;

import java.util.concurrent.ExecutionException;

/**
 * Created by rillingworth on 19/06/14.
 */
public class MyExceptionExtensions<T extends Exception> extends ExceptionExtensions<T> {

    public static final MyExceptionExtensions<IllegalArgumentException> IllegalArgumentException = new MyExceptionExtensions(IllegalArgumentException.class);

    protected MyExceptionExtensions(Class clazz) {
        super(clazz);
    }

    public void whenNot(boolean condition, String msg, Object... args) throws T {
        when(!condition,msg,args);
    }

    public static void main(String[] args) {

        IllegalArgumentException.whenNot(false,"");

    }

}
