/**
 * Copyright (c) 2014, Techmale <oss@techmale.com>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.techmale.util;

import java.io.IOException;
import java.io.InputStream;

import static com.techmale.util.exceptions.ExceptionExtensions.*;

/**
 * Collection of utility methods for manipulating files stored in classpath or resources.
 */
public final class FileHelper {

    private FileHelper() {
    }

    /**
     * Reads a file and returns it as a string.
     *
     * @param path The path to the file relative to the Resources directory.
     * @return String representation of a file.
     * @throws java.io.IOException              Thrown if the specified file is not found.
     * @throws IllegalArgumentException Thrown if path given is not a file.
     */
    public static String getString(String path) throws IOException {
        InputStream is = getInputStream(path);
        String contents = InputStreamUtil.asString(is,"UTF-8");
        is.close();
        return contents;
    }


    /**
     * Reads a file relative to classpath, returns it as an InputStream
     *
     * @param path Path to file.
     * @return Input stream representation of file.
     * @throws IllegalArgumentException Thrown if path given is not a file.
     */
    private static InputStream getInputStream(String path) {
        // Path requires a leading /
        // add if not present.
        String newPath = path;

        if (!path.startsWith("/")) {
            newPath = "/" + path;
        }

        InputStream fileStream = FileHelper.class.getResourceAsStream(newPath);
        IllegalArgumentException.when(fileStream == null, "Path specified does not point to a file, path: %s", path);
        return fileStream;
    }
}