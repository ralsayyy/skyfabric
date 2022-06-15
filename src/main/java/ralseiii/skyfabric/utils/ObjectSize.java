package ralseiii.skyfabric.utils;
/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */
public class ObjectSize {
    public float width;
    public float height;
    public float depth;
    public void set(float _x, float _y, float _z) {
        width = _x;
        height = _y;
        depth = _z;
    }
    public void set(ObjectSize pos) {
        width = pos.width;
        height = pos.height;
        depth = pos.depth;

    }
}
