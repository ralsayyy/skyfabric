package ralseiii.skyfabric.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.util.Identifier;

public class PlayerUtils {
    public static boolean hasRegisteredMapTexture = false;
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
