package ralseiii.skyfabric.solvers.dwarven;

public class FetchurSolver {
    public static String fetchurSolver(String message) {
        if (message.contains("red and soft")) return "Red Wool";
        if (message.contains("see through")) return "Yellow Stained Glass";
        if (message.contains("circular and sometimes moves")) return "Compass";
        if (message.contains("expensive minerals")) return "Nithril";
        if (message.contains("useful during celebrations")) return "Firework Rocket";
        if (message.contains("gives energy")) return "Cheap coffee/Decent coffee";
        if (message.contains("can be opened")) return "Wooden door";
        if (message.contains("brown and fluffy")) return "Rabbit's foot";
        if (message.contains("explosive")) return "Superboom TNT";
        if (message.contains("wearable")) return "Pumpkin";
        if (message.contains("makes sparks")) return "Flint and Steel";
        if (message.contains("red and white and you can mine it")) return "Nether Quartz Ore";
        if (message.contains("round and green")) return "Ender Pearls";
        return "";
    }
}
