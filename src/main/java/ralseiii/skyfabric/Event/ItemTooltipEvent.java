package ralseiii.skyfabric.Event;

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
    public static void onItemTooltip(ItemStack item, List<Text> lines) {
        String id = ItemUtils.getItemId(item, true);
        if (id == null || id.isEmpty())
            return;
        if (Bazaar.isBazaarItem(id)) {
            lines.add(Text.of("§lBazaar Buy:§r " + format.format(Screen.hasShiftDown() ? Bazaar.getBuyPriceForId(id) * item.getCount() : Bazaar.getBuyPriceForId(id)) + " coins"));
            lines.add(Text.of("§lBazaar Sell:§r " + format.format(Screen.hasShiftDown() ? Bazaar.getSellPriceForId(id) * item.getCount() : Bazaar.getSellPriceForId(id)) + " coins"));
        }
        if (LowestBin.isAvailable(id)) {
            lines.add(Text.of("§lLowest BIN:§r " + format.format(LowestBin.get(id)) + " coins"));
        }
    }
}
