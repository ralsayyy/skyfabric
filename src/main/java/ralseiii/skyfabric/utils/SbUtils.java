package ralseiii.skyfabric.utils;

public class SbUtils {
    public static int getYear() {
        // age of skyblock / lenght of skyblock year
        return ((int) (System.currentTimeMillis() / 1000) - 1560268800 ) / 446400;
    }
}
