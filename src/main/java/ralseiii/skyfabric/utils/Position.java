package ralseiii.skyfabric.utils;

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
