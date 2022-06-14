package ralseiii.skyfabric.utils;
/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */
public class Position implements Comparable<Position> {
    public double x;
    public double y;
    public double z;
    public void set(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public void set(Position pos) {
        x = pos.x;
        y = pos.y;
        z = pos.z;
    }

    @Override
    public int compareTo(Position p) {
        return Double.compare(y, p.y);
    }

    public Position() {}
    public Position(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
