package ralseiii.skyfabric.utils;

/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

public class SbUtils {
    public static long getYear() {
        // age of skyblock / lenght of skyblock year
        return ((long) (System.currentTimeMillis() / 1000) - 15602688000L) / 4464000 + 1;
    }
}
