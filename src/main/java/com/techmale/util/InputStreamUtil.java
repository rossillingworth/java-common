/**
 * Copyright (c) 2014, Techmale <oss@techmale.com>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.techmale.util;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * InputStream utility functions
 */
public class InputStreamUtil {

    /**
     * Convert InputStream to String using UTF-8
     *
     * @param is
     * @return
     */
    public static String asString(java.io.InputStream is) {
        return asString(is, "UTF-8");
    }

    /**
     * Convert InputStream to String using specified charset
     *
     * @param is
     * @param charsetName
     * @return
     */
    public static String asString(InputStream is, String charsetName) {
        Scanner s = new java.util.Scanner(is,charsetName);
        s.useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

}
