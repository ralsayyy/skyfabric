package ralseiii.skyfabric.solvers.dwarven;

import net.minecraft.text.Text;

public class FetchurSolver {


    public static String fetchurSolver(String message) {
        if (message.contains("red and soft")) return "Red wool";
        if (message.contains("see through")) return "Yellow Glass";
        if (message.contains("circular and sometimes moves")) return "compass";
        if (message.contains("expensive minerals")) return "mithril";
        if (message.contains("useful during celebrations")) return "Red wool";
        if (message.contains("gives energy")) return "cheap coffee/decent coffee";
        if (message.contains("can be opened")) return "wooden door";
        if (message.contains("brown and fluffy")) return "rabbit's feet";
        if (message.contains("explosive")) return "superboom tnt";
        if (message.contains("wearable")) return "pumpkin";
        if (message.contains("makes sparks")) return "flint and steel";
        if (message.contains("red and white and you can mine it")) return "nether quartz ore";
        if (message.contains("round and green")) return "ender pearl";
        return "";
    }
}
