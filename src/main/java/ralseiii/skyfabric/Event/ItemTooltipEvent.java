package ralseiii.skyfabric.Event;

import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.client.gui.screen.Screen;
import ralseiii.skyfabric.utils.ItemUtils;
import ralseiii.skyfabric.utils.api.Bazaar;
import ralseiii.skyfabric.utils.api.auctions.LowestBin;

import java.util.List;

public class ItemTooltipEvent {

    static Double limitDecimal(double input, int decimals) {
        return Math.floor(input * Math.pow(10, decimals)) / Math.pow(10, decimals);
    }

    public static void onItemTooltip(ItemStack item, List<Text> lines) {
        String id = ItemUtils.getItemId(item);
        if (id == null || id.isEmpty())
            return;
        if (Bazaar.isBazaarItem(id)) {
            double sellPrice = Screen.hasShiftDown() ? Bazaar.getSellPriceForId(id) * item.getCount() : Bazaar.getSellPriceForId(id);
            double buyPrice = Screen.hasShiftDown() ? Bazaar.getBuyPriceForId(id) * item.getCount() : Bazaar.getBuyPriceForId(id);
            lines.add(Text.of("§lBazaar Sell:§r " + limitDecimal(sellPrice, 1)));
            lines.add(Text.of("§lBazaar Buy:§r " + limitDecimal(buyPrice, 1)));
        }
        if (LowestBin.isAvailable(id)) {
            lines.add(Text.of("§lLowest BIN:§r " + LowestBin.get(id)));
        }
    }
}
