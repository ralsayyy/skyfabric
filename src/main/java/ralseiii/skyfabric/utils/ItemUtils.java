package ralseiii.skyfabric.utils;
/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtList;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class ItemUtils {
    public static int i;
    public static NbtCompound getExtraAttributes(ItemStack item) {
        if (item != null) {
            return item.getSubNbt("ExtraAttributes");
        } else
            return null;
    }
    public static String getItemId(ItemStack item) {
        return getItemId(item, false);
    }
    public static String getItemId(ItemStack item, Boolean skytils) {
        var tag = getExtraAttributes(item);
        if (tag != null && tag.contains("id")) {
            var id = tag.getString("id");
            if (skytils) {
                if (id.equals("PET") && tag.contains("petInfo")) {
                    var petInfo = new Gson().fromJson(tag.getString("petInfo"), JsonObject.class);
                    id = "PET-" + petInfo.get("type").getAsString() + "-" + petInfo.get("tier").getAsString();
                } else if (id.equals("ENCHANTED_BOOK") && tag.contains("enchantments")) {
                    var enchantList = tag.getCompound("enchantments").asString();
                    var enchantments = new Gson().fromJson(enchantList, JsonObject.class).entrySet();
                    for (var entry : enchantments) {
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
