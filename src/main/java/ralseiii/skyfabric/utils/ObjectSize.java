package ralseiii.skyfabric.utils;

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
