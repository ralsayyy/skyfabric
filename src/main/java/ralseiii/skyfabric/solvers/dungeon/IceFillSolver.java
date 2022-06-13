package ralseiii.skyfabric.solvers.dungeon;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.world.chunk.BlockEntityTickInvoker;
import ralseiii.skyfabric.mixin.WorldAccessor;
import java.util.List;


public class IceFillSolver {
    // 1 = forward, 2 = left, 3 = right, 4 = backwards
    public static int path[][] = {
            {1, 1, 2, 1, 3, 3, 3, 1, 2, 1, 1}, // 1st stage
    };
    public static void solve() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.world == null) return;
        List<BlockEntityTickInvoker> blockEntityTickInvokers = ((WorldAccessor)client.world).getBlockEntityTickers();
        for (BlockEntityTickInvoker o : blockEntityTickInvokers) {
            BlockEntity be = client.world.getBlockEntity(o.getPos());
            if (be instanceof ChestBlockEntity && o.getPos().getY() == 75) {
                // System.out.println("found chest at y=76");
                break;
            }
        }
    }
}
