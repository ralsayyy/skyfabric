package ralseiii.skyfabric.solvers.dwarven;

/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public class FetchurSolver {
    public static Text fetchurSolver(String message) {
        if (message.contains("red and soft")) return new TranslatableText("block.minecraft.red_wool");
        if (message.contains("see through")) return new TranslatableText("block.minecraft.stained_glass.yellow");
        if (message.contains("circular and sometimes moves")) return new TranslatableText("item.minecraft.compass");
        if (message.contains("expensive minerals")) return Text.of("Mithril");
        if (message.contains("its useful during celebrations")) return new TranslatableText("item.minecraft.firework_rocket");
        if (message.contains("gives energy")) return Text.of("Cheap coffee");
        if (message.contains("can be opened")) return new TranslatableText("block.minecraft.door");
        if (message.contains("brown and fluffy")) return new TranslatableText("item.minecraft.rabbit_foot");
        if (message.contains("explosive")) return Text.of("Superboom TNT");
        if (message.contains("wearable")) return new TranslatableText("block.minecraft.pumpkin");
        if (message.contains("makes sparks")) return new TranslatableText("item.minecraft.flint_and_steel");
        if (message.contains("red and white and you can mine it")) return new TranslatableText("block.minecraft.nether_quartz_ore.name");
        if (message.contains("round and green")) return new TranslatableText("item.minecraft.enderpearl");
        return Text.of("");
    }
}
