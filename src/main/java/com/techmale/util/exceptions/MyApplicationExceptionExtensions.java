package com.techmale.util.exceptions;

import com.techmale.util.exceptions.ExceptionExtensions;

/**
 * Demonstration of extending your own Exception
 */
public class MyApplicationExceptionExtensions{

    public static final ExceptionExtensions<InstantiationException> InstantiationException = new ExceptionExtensions(InstantiationException.class);

    public static void main(String[] args) {
        try {
            InstantiationException.when(true,"");
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }
    }
}
