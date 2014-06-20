/**
 * Copyright (c) 2014, Techmale <oss@techmale.com>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.techmale.util.exceptions;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;

/**
 * <h4>Exception extensions - making Exceptions easier.</h4>
 *
 * <p>
 * Replaces the exception boilerplate with a single line
 * producing better and more readable code. Contains both
 * checked and unchecked exceptions.
 * </p>
 *
 * <h5>Setup:</h5>
 *
 * <pre>
 *     {@code
 *  import static com.techmale.util.exceptions.ExceptionExtensions.*;
 *     }
 * </pre>
 *
 * <h5>Usage</h5>
 * <pre>
 *     {@code
 *  IllegalArgumentException.when(myVar.equals("Foo"), "Bad value for myVar: %s", myvar);
 *     }
 * </pre>
 */
public class ExceptionExtensions<T extends Exception> {

    // unchecked
    public static final ExceptionExtensions<IllegalArgumentException> IllegalArgumentException = new ExceptionExtensions(IllegalArgumentException.class);
    public static final ExceptionExtensions<NullPointerException> NullPointerException = new ExceptionExtensions(NullPointerException.class);
    public static final ExceptionExtensions<NumberFormatException> NumberFormatException = new ExceptionExtensions(NumberFormatException.class);

    // checked Exceptions
    public static final ExceptionExtensions<FileNotFoundException> FileNotFoundException = new ExceptionExtensions(FileNotFoundException.class);

    private Class exceptionClass;

    /**
     * Instantiate Exception Extension
     *
     * @param exceptionClass Exception Class
     */
    protected ExceptionExtensions(Class exceptionClass) {
        this.exceptionClass = exceptionClass;
    }


    /**
     * Exception.when throws exception when the condition is true
     *
     * @param condition If true, then exception is thrown
     * @param msg       Msg to display (uses String.format)
     * @param args      optional varArgs for string format
     * @throws T
     */
    public void when(boolean condition, String msg, Object... args) throws T {
        if (condition) {
            throwException(msg, args);
        }
    }


    /**
     * Exception.ifNull throws exception if the passed object is null
     *
     * @param o    object to check if null
     * @param msg  Msg to display (uses String.format)
     * @param args optional varArgs for string format
     * @throws T
     */
    public void ifNull(Object o, String msg, Object... args) throws T {
        when(o == null, msg, args);
    }


    /**
     * Throws exception with a formatted message, this should only be
     * called if you are extending this class.
     *
     * @param msg  Msg to display (uses String.format)
     * @param args optional varArgs for string format
     * @throws T
     */
    protected void throwException(String msg, Object[] args) throws T {
        try {
            String formattedMessage = String.format(msg, args);
            throw (T) exceptionClass.getConstructor(String.class).newInstance(formattedMessage);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

}