package ralseiii.skyfabric.utils;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.item.ItemStack;

public class ItemUtils {
    public static CompoundTag getExtraAttributes(ItemStack item) {
        if (item != null) {
            return item.getSubTag("ExtraAttributes");
        } else
            return null;
    }
    public static String getItemId(ItemStack item) {
        CompoundTag tag = getExtraAttributes(item);
        if (tag != null) {
            return tag.getString("id");
        } else {
            return null;
        }
    }
}
