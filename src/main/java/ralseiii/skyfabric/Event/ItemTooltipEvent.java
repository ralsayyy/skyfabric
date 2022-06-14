package ralseiii.skyfabric.Event;
/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.client.gui.screen.Screen;
import ralseiii.skyfabric.utils.ItemUtils;
import ralseiii.skyfabric.utils.api.Bazaar;
import ralseiii.skyfabric.utils.api.auctions.LowestBin;
import ralseiii.skyfabric.config.ModConfig;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ItemTooltipEvent {
    static NumberFormat format = NumberFormat.getInstance(Locale.US);
    public static void onItemTooltip(ItemStack item, List<Text> lines) {
        var id = ItemUtils.getItemId(item, true);

        if (id == null || id.isEmpty()) return;

        if (Bazaar.isBazaarItem(id) && true) {
            lines.add(Text.of("§lBazaar Buy:§r " + format.format(Screen.hasShiftDown() ? Bazaar.getBuyPriceForId(id) * item.getCount() : Bazaar.getBuyPriceForId(id)) + " coins"));
            lines.add(Text.of("§lBazaar Sell:§r " + format.format(Screen.hasShiftDown() ? Bazaar.getSellPriceForId(id) * item.getCount() : Bazaar.getSellPriceForId(id)) + " coins"));
        }
        if (LowestBin.isAvailable(id) && true) {
            lines.add(Text.of("§lLowest BIN:§r " + format.format(LowestBin.get(id)) + " coins"));
        }
        if (/*AutoConfig.getConfigHolder(ModConfig.class).getConfig().itemInfo.sbItemId*/ true) lines.add(Text.of("§7sb:" + ItemUtils.getItemId(item, false)));
    }
}
