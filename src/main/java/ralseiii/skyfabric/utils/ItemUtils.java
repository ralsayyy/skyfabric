package ralseiii.skyfabric.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.ListTag;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class ItemUtils {
    public static int i;
    public static CompoundTag getExtraAttributes(ItemStack item) {
        if (item != null) {
            return item.getSubTag("ExtraAttributes");
        } else
            return null;
    }
    public static String getItemId(ItemStack item) {
        return getItemId(item, false);
    }
    public static String getItemId(ItemStack item, Boolean skytils) {
        CompoundTag tag = getExtraAttributes(item);
        if (tag != null && tag.contains("id")) {
            String id = tag.getString("id");
            if (skytils) {
                if (id.equals("PET") && tag.contains("petInfo")) {
                    JsonObject petInfo = new Gson().fromJson(tag.getString("petInfo"), JsonObject.class);
                    id = "PET-" + petInfo.get("type").getAsString() + "-" + petInfo.get("tier").getAsString();
                } else if (id.equals("ENCHANTED_BOOK") && tag.contains("enchantments")) {
                    String enchantList = tag.getCompound("enchantments").asString();
                    Set<Map.Entry<String, JsonElement>> enchantments = new Gson().fromJson(enchantList, JsonObject.class).entrySet();
                    for (Map.Entry<String, JsonElement> entry : enchantments) {
                        id = "ENCHANTED_BOOK-" + entry.getKey().toUpperCase() + "-" + entry.getValue().getAsInt();
                        break;
                    }
                }
            }
            return id;
        } else {
            return null;
        }
    }
}
