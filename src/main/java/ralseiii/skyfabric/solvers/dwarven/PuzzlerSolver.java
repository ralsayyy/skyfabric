package ralseiii.skyfabric.solvers.dwarven;

import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import ralseiii.skyfabric.solvers.dungeon.chat.ThreeWeirdos;
import ralseiii.skyfabric.utils.RenderUtils;

public class PuzzlerSolver {
    public static int rewardX;
    public static int rewardZ;
    public static boolean renderOverlay = false;
    public static void register() {
        WorldRenderEvents.END.register((context) -> {
            if (PuzzlerSolver.renderOverlay)
                RenderUtils.renderSolidBox((float) rewardX, 195.01f, (float) rewardZ, 1, 1, 1, context, 0, 255, 0, 1);
        });
    }

    public static void solve(String message) {
        rewardX = 181;
        rewardZ = 135;
        for (char c : message.toCharArray()) {
            if (c == "▲".toCharArray()[0])
                rewardZ++;
            else if (c == "▼".toCharArray()[0])
                rewardZ--;
            else if (c == "◀".toCharArray()[0])
                rewardX--;
            else if (c == "▶".toCharArray()[0])
                rewardX++;
        }
        renderOverlay = true;
    }
}
