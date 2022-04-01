package ralseiii.skyfabric.utils;
/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */
public class Position {
    public double x;
    public double y;
    public double z;
    public void set(double _x, double _y, double _z) {
        x = _x;
        y = _y;
        z = _z;
    }
    public void set(Position pos) {
        x = pos.x;
        y = pos.y;
        z = pos.z;
    }
}
