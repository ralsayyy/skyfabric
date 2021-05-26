package ralseiii.skyfabric.utils;

/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class PlayerUtils {
    // this is pretty much the class i dump everything into
    public static boolean hasRegisteredMapTexture = false;
    public static String secretsAmount = null;
    public static Identifier mapTextureId;
    public static NativeImageBackedTexture mapBackedTexture;
    public static int getExpLevel() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.player == null) return 0;
        int nextExpLevelAmount = client.player.getNextLevelExperience();
        if (nextExpLevelAmount <= 37)
            return ((nextExpLevelAmount - 7) / 2);
        if (nextExpLevelAmount <= 107)
            return ((nextExpLevelAmount + 38) / 5);
        else
            return ((nextExpLevelAmount + 158) / 9);
    }
}
