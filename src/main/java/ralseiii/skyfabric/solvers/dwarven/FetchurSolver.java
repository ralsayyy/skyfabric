package ralseiii.skyfabric.solvers.dwarven;

import net.minecraft.text.TranslatableText;

public class FetchurSolver {
    public static String fetchurSolver(String message) {
        if (message.contains("red and soft")) return new TranslatableText("tile.wool.red.name").toString();
        if (message.contains("see through")) return new TranslatableText("tile.stained_glass.yellow.name").toString();
        if (message.contains("circular and sometimes moves")) return new TranslatableText("item.compass.name").toString();
        if (message.contains("expensive minerals")) return "Mithril";
        if (message.contains("its useful during celebrations")) return new TranslatableText("item.fireworks.name").toString();
        if (message.contains("gives energy")) return "Cheap coffee";
        if (message.contains("can be opened")) return new TranslatableText("tile.doorWood.name").toString();
        if (message.contains("brown and fluffy")) return new TranslatableText("item.rabbit_foot.name").toString();
        if (message.contains("explosive")) return "Superboom TNT";
        if (message.contains("wearable")) return new TranslatableText("tile.pumpkin.name").toString();
        if (message.contains("makes sparks")) return new TranslatableText("item.flint_and_steel.name").toString();
        if (message.contains("red and white and you can mine it")) return new TranslatableText("tile.quartz_ore.name").toString();
        if (message.contains("round and green")) return new TranslatableText("item.enderpearl.name").toString();
        return "";
    }
}
