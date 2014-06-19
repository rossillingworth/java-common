/**
 * Copyright (c) 2014, Techmale <oss@techmale.com>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.techmale.util.exceptions;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;

/**

 *
 * Exception extensions - making Exceptions easier to use.
 *
 * Exception.when(condition,message[,string format varArgs])
 *
 */
public class ExceptionExtensions<T extends Exception> {

    // unchecked
    public static final ExceptionExtensions<IllegalArgumentException> IllegalArgumentException = new ExceptionExtensions(IllegalArgumentException.class);
    public static final ExceptionExtensions<NullPointerException> NullPointerException = new ExceptionExtensions(NullPointerException.class);
    public static final ExceptionExtensions<NumberFormatException> NumberFormatException = new ExceptionExtensions(NumberFormatException.class);

    // checked Exceptions
    public static final ExceptionExtensions<FileNotFoundException> FileNotFoundException = new ExceptionExtensions(FileNotFoundException.class);

    private Class clazz;

    /**
     * Instantiate Exception Extension
     *
     * @param clazz Class of RuntimeException type to throw
     */
    protected ExceptionExtensions(Class clazz) {
        this.clazz = clazz;
    }

    /**
     * Exception.when allows for better and more readable code
     * see tests for examples.
     *
     * @param condition If true, then exception is thrown
     * @param msg       Msg to display (uses String.format)
     * @param args      optional varArgs for string format
     */
    public void when(boolean condition, String msg, Object... args) throws T {
        if (condition) {
            try {
                String formattedMessage = String.format(msg, args);
                throw (T) clazz.getConstructor(String.class).newInstance(formattedMessage);
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

    public static void main(String[] args) {

        try {
            FileNotFoundException.when(true,"");
        } catch (java.io.FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}