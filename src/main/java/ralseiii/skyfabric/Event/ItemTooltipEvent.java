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

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ItemTooltipEvent {
    static NumberFormat format = NumberFormat.getInstance(Locale.US);
    /*
     Returns the current stack size, except if it is 1.
     If it is one, we return the maximum stack size
     */
    static int get_preferred_stack_size(ItemStack item) {
        return item.getCount() == 1 ? item.getMaxCount() : item.getCount();
    }

    static String get_bazaar_string_optional_count(boolean sell, boolean is_pressing_shift, long price, ItemStack item) {
        StringBuilder retval = new StringBuilder("§lBazaar");
        var count = 1;
        if (is_pressing_shift) {
            count = get_preferred_stack_size(item);
        }

        if (sell) {
            retval.append(" Sell ");
        } else {
            retval.append(" Buy ");
        }

        retval.append("(x").append(count).append(") :§r ");

        retval.append(String.format("%,d", price * count));
        retval.append(" coins");

        return retval.toString();
    }



    public static void onItemTooltip(ItemStack item, List<Text> lines) {
        var skytils_id = ItemUtils.getItemId(item, true);
        var hypixel_id = ItemUtils.getItemId(item, false);

        if (skytils_id == null || skytils_id.isEmpty()) return;
        if (hypixel_id == null || hypixel_id.isEmpty()) return;



        if (Bazaar.isBazaarItem(hypixel_id) && true) {
            lines.add(Text.of(""));
            lines.add(Text.of(get_bazaar_string_optional_count(false, Screen.hasShiftDown(), Bazaar.getBuyPrice(hypixel_id), item)));
            lines.add(Text.of(get_bazaar_string_optional_count(true, Screen.hasShiftDown(), Bazaar.getBuyPrice(hypixel_id), item)));
        }
        if (LowestBin.isAvailable(skytils_id) && true) {
            lines.add(Text.of("§lLowest BIN:§r " + format.format(LowestBin.get(skytils_id)) + " coins"));
        }
        if (/*AutoConfig.getConfigHolder(ModConfig.class).getConfig().itemInfo.sbItemId*/ true) lines.add(Text.of("§7sb:" + ItemUtils.getItemId(item, false)));
    }
}
