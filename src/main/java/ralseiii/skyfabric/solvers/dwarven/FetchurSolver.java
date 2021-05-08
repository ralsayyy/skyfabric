package ralseiii.skyfabric.solvers.dwarven;

import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public class FetchurSolver {
    public static Text fetchurSolver(String message) {
        if (message.contains("red and soft")) return new TranslatableText("tile.wool.red.name");
        if (message.contains("see through")) return new TranslatableText("tile.stained_glass.yellow.name");
        if (message.contains("circular and sometimes moves")) return new TranslatableText("item.compass.name");
        if (message.contains("expensive minerals")) return Text.of("Mithril");
        if (message.contains("its useful during celebrations")) return new TranslatableText("item.fireworks.name");
        if (message.contains("gives energy")) return Text.of("Cheap coffee");
        if (message.contains("can be opened")) return new TranslatableText("tile.doorWood.name");
        if (message.contains("brown and fluffy")) return new TranslatableText("item.rabbit_foot.name");
        if (message.contains("explosive")) return Text.of("Superboom TNT");
        if (message.contains("wearable")) return new TranslatableText("tile.pumpkin.name");
        if (message.contains("makes sparks")) return new TranslatableText("item.flint_and_steel.name");
        if (message.contains("red and white and you can mine it")) return new TranslatableText("tile.quartz_ore.name");
        if (message.contains("round and green")) return new TranslatableText("item.enderpearl.name");
        return Text.of("");
    }
}
