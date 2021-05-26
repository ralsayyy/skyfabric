package ralseiii.skyfabric.utils;

/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

public class SbUtils {
    public static int getYear() {
        // age of skyblock / lenght of skyblock year
        return ((int) (System.currentTimeMillis() / 1000) - 1560268800 ) / 446400;
    }
}
