package ralseiii.skyfabric.overlay;
/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.inventory.Inventory;
import ralseiii.skyfabric.utils.ItemUtils;
import ralseiii.skyfabric.utils.api.Bazaar;
import ralseiii.skyfabric.utils.api.auctions.LowestBin;
import java.util.StringTokenizer;

public class DungeonChestProfitOverlay {
    static int j;
    public static void render(MatrixStack matrixStack, String title, Inventory inv, int rows) {

        Long itemPrice = 0L;
        var chestPrice = 0L;
        var totalSlots = rows * 9;
        var client = MinecraftClient.getInstance();
        var tx = client.textRenderer;
        for (var i = 0; i < totalSlots; i++) {
            var item = inv.getStack(i);
            var id = ItemUtils.getItemId(item, true);
            if (Bazaar.isBazaarItem(id)) {
                itemPrice += Bazaar.getSellPrice(id);
            } else if (LowestBin.isAvailable(id)) {
                itemPrice += LowestBin.get(id);
            }
            if (item.getName().getString().contains("Open Reward Chest")) {
                var nbt = item.getNbt();
                var display = nbt.getCompound("display");
                var lore = display.getList("Lore", 8).getString(6);
                var st = new StringTokenizer(lore, "\"");
                for (var k = 0; k < 12; k++) {
                    lore = st.nextToken();
                }

                var sb = new StringBuilder();
                for (var c: lore.toCharArray()) {
                    if (c == '1' || c == '2' || c == '3' ||
                            c == '4' || c == '5' || c == '6' ||
                            c == '7' || c == '8' || c == '9' ||
                            c == '0') {
                        sb.append(c);
                    }
                }
                var priceString = sb.toString();
                chestPrice = Integer.parseInt(priceString);
            }


        }
        Long profit = itemPrice - chestPrice;
        tx.draw(matrixStack, profit.toString(), 0, 0, 0xffffff);
        j++;
    }
}
