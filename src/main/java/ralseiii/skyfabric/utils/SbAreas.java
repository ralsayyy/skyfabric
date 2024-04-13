package ralseiii.skyfabric.utils;

/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */
public class SbAreas {
    public static int CRYSTAL_HOLLOWS = 0;
    public static int DUNGEON = 1;
    public static int DWARVEN_MINES = 2;
    public static int OTHER = 3;
    public static int DUNGEON_HUB = 4;

    static String[] areas = {
      "Crystal Hollows",
      "Dungeon",
      "Dwarven Mines",
      "Other",
      "Dungeon Hub"
    };

    public static String areaToString(int area) {
        return areas[area];
    }
}
