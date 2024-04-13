package ralseiii.skyfabric.solvers.dungeon.entity;
/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.util.math.*;
import ralseiii.skyfabric.utils.Position;
import ralseiii.skyfabric.utils.RenderUtils;
import ralseiii.skyfabric.utils.SbAreas;
import ralseiii.skyfabric.utils.SbChecks;
import net.minecraft.block.Blocks.*;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class LineCoords {
    public Position startPos = new Position();
    public Position endPos = new Position();
    public int red;
    public int green;
    public int blue;

    public LineCoords(int r, int g, int b) {
        red = r;
        green = g;
        blue = b;
    }

    @Override
    public String toString() {
        return "LineCoords{ Pos1(" + startPos.x + ", " +  startPos.y +  ", " + startPos.z + "), Pos2("  + endPos.x + ", " +  endPos.y +  ", " + endPos.z + ")}";
    }
}

class Holder {
    public Vec3d val;
}

class ShootyThingy {
    public Vec3d block;
    public boolean is_already_used;

    public ShootyThingy(Vec3d vec) {
        block = vec;
        is_already_used = false;
    }
}

// TODO: This solver does not really work in any way, shape, or form.

public class CreeperSolver {
    static Position entityPos = new Position();

    public static boolean drawLines = false;
    static LineCoords[] lineCoords = {
            new LineCoords(255, 0, 0),
            new LineCoords(0, 255, 255),
            new LineCoords(0, 255, 0),
            new LineCoords(255, 255, 0),
    };

    public static void register() {
        WorldRenderEvents.END.register(ctx -> {
            solve();
            if (drawLines && SbChecks.currentArea == SbAreas.DUNGEON) {

                for (var l : lineCoords) {
                    if (l.startPos.y != 0.0f) {
                        RenderUtils.drawBox(l.startPos, ctx, l.red, l.green, l.blue, .5f, false);
                        RenderUtils.drawBox(l.endPos, ctx, l.red, l.green, l.blue, .5f, false);
                    }
                        // RenderUtils.renderSolidLine((float) l.startPos.x, (float) l.startPos.y, (float) l.endPos.z, (float) l.endPos.x, (float) l.endPos.y, (float) l.endPos.z, ctx, 255, 0, 0, 1.0f);
                }
            }
        });
    }

    public static void reset() {
        //System.out.println(Arrays.toString(lineCoords));
        drawLines = false;
        lineCoords[0] = new LineCoords(255, 0, 0);
        lineCoords[1] = new LineCoords(0, 255, 255);
        lineCoords[2] = new LineCoords(0, 255, 0);
        lineCoords[3] = new LineCoords(255, 255, 0);
       // System.out.println("a");
    }

    public static void solve() {
        if (drawLines) return;
        if (SbChecks.currentArea != SbAreas.DUNGEON) return;
        var client = MinecraftClient.getInstance();
        if (client == null || client.world == null || client.player == null) return;
        client.world.getEntitiesByClass(CreeperEntity.class, client.player.getBoundingBox().expand(20, 20, 20), entity -> {
            return !entity.isInvisible() && !entity.hasCustomName() && entity.getMaxHealth() == 20.0f && entity.getHealth() == entity.getMaxHealth();
        }).forEach(entity -> {
            entityPos.set(entity.getX(), entity.getY(), entity.getZ());

            var creeperHitbox = entity.getBoundingBox();
            var boxToCheckAgainst = creeperHitbox.expand(0.5).offset(0, 77 - creeperHitbox.minY ,0);
            /*System.out.println("max and min x");
            System.out.println(boxToCheckAgainst.maxX);
            System.out.println(boxToCheckAgainst.minX);
            System.out.println("max and min y");
            System.out.println(boxToCheckAgainst.maxY);
            System.out.println(boxToCheckAgainst.minY);
            System.out.println("max and min z");
            System.out.println(boxToCheckAgainst.maxZ);
            System.out.println(boxToCheckAgainst.minZ);*/
            var roomBox = creeperHitbox.expand(14, 10, 13);

            // System.out.println(boxToCheckAgainst.getMin(Direction.Axis.Y));

            var blocksAroundCreeper = BlockPos.iterate(new BlockPos(roomBox.minX, roomBox.minY, roomBox.minZ), new BlockPos(roomBox.maxX, roomBox.maxY, roomBox.maxZ));

            var relevantBlocks = new ArrayList<ShootyThingy>();

            for (var block_pos: blocksAroundCreeper) {
                if (block_pos.getY() < 69) continue;
                var block = client.world.getBlockState(block_pos);
                if (block.isOf(Blocks.PRISMARINE) || block.isOf(Blocks.SEA_LANTERN)) {
                    relevantBlocks.add(new ShootyThingy(new Vec3d(block_pos.getX(), block_pos.getY(), block_pos.getZ())));
                }
            }

            var hits_found = 0;

            var hits = new ArrayList<Position[]>();


            var counter = 0;
            outer_loop:
            for (var block1: relevantBlocks) {
                if (block1.is_already_used) continue;
                for (var block2: relevantBlocks) {
                    counter++;
                    if (block1.equals(block2)) continue;
                    if (block2.is_already_used) continue;
                    // if (block1.equals(block2)) continue;
                    var holder = new Holder();
                    holder.val = new Vec3d(0, 0, 0);
                    var block1_center = new Vec3d(block1.block.getX() + 0.5, block1.block.getY() - 0.5, block1.block.getZ() + 0.5);
                    var block2_center = new Vec3d(block2.block.getX() + 0.5, block2.block.getY() - 0.5, block2.block.getZ() + 0.5);
                    // System.out.println(block2_center);

                    var has_hit = checkLineBox(new Vec3d(boxToCheckAgainst.minX, boxToCheckAgainst.minY, boxToCheckAgainst.minZ),
                            new Vec3d(boxToCheckAgainst.maxX, boxToCheckAgainst.maxY, boxToCheckAgainst.maxZ),
                            block1_center,
                            block2_center,
                            holder);
                    if (has_hit) {
                        //System.out.println("hit");
                        block1.is_already_used = true;
                        block2.is_already_used = true;
                        lineCoords[hits_found].startPos.set(new Position(block1.block.getX(), block1.block.getY(), block1.block.getZ()));
                        lineCoords[hits_found].endPos.set(new Position(block2.block.getX(), block2.block.getY(), block2.block.getZ()));
                        /*lineCoords[hits_found].startPos.set(new Position(block1_center.getX(), block1_center.getY(), block1_center.getZ()));
                        lineCoords[hits_found].endPos.set(new Position(block2_center.getX(), block2_center.getY(), block2_center.getZ()));*/
                        // System.out.println(block2_center);
                        hits_found += 1;
                        var pos1 = new Position(block1.block.getX(), block1.block.getY(), block1.block.getZ());
                        var pos2 = new Position(block2.block.getX(), block2.block.getY(), block2.block.getZ());

                        hits.add(new Position[]{pos1, pos2});

                        if (hits_found > 3) {
                            break outer_loop;
                        }
                    }
                }
            }

            for (var hit: hits) {
                //System.out.println("x1: " + hit[0].x + " y1: " + hit[0].y + " z1: " + hit[0].z);
                //System.out.println("x2: " + hit[1].x + " y2: " + hit[1].y + " z2: " + hit[1].z);
            }
           // System.out.println();
           // System.out.println(counter);

            drawLines = true;
        });
    }


        /* author: qJake
            source: https://stackoverflow.com/a/3235902
            license: https://creativecommons.org/licenses/by-sa/2.5/
            ported to java
         */
        /* start */
        static boolean checkLineBox(Vec3d B1, Vec3d B2, Vec3d L1, Vec3d L2, Holder Hit) {
            // aSystem.out.println("a");
            if (L2.getX() < B1.getX() && L1.getX() < B1.getX()) return false;
            if (L2.getX() > B2.getX() && L1.getX() > B2.getX()) return false;
            if (L2.getY() < B1.getY() && L1.getY() < B1.getY()) return false;
            if (L2.getY() > B2.getY() && L1.getY() > B2.getY()) return false;
            if (L2.getZ() < B1.getZ() && L1.getZ() < B1.getZ()) return false;
            if (L2.getZ() > B2.getZ() && L1.getZ() > B2.getZ()) return false;
            if (L1.getX() > B1.getX() && L1.getX() < B2.getX() &&
                    L1.getY() > B1.getY() && L1.getY() < B2.getY() &&
                    L1.getZ() > B1.getZ() && L1.getZ() < B2.getZ())
            {
                Hit.val = L1;
                return true;
            }
            return (getIntersection(L1.getX() - B1.getX(), L2.getX() - B1.getX(), L1, L2, Hit) && InBox(Hit, B1, B2, 1))
                    || (getIntersection(L1.getY() - B1.getY(), L2.getY() - B1.getY(), L1, L2, Hit) && InBox(Hit, B1, B2, 2))
                    || (getIntersection(L1.getZ() - B1.getZ(), L2.getZ() - B1.getZ(), L1, L2, Hit) && InBox(Hit, B1, B2, 3))
                    || (getIntersection(L1.getX() - B2.getX(), L2.getX() - B2.getX(), L1, L2, Hit) && InBox(Hit, B1, B2, 1))
                    || (getIntersection(L1.getY() - B2.getY(), L2.getY() - B2.getY(), L1, L2, Hit) && InBox(Hit, B1, B2, 2))
                    || (getIntersection(L1.getZ() - B2.getZ(), L2.getZ() - B2.getZ(), L1, L2, Hit) && InBox(Hit, B1, B2, 3));
        }

    static boolean getIntersection(double fDst1, double fDst2, Vec3d P1, Vec3d P2, Holder Hit) {
        if ((fDst1 * fDst2) >= 0.0f) return false;
        if (fDst1 == fDst2) return false;
        P2.subtract(P1);
        P2.multiply((-fDst1 / (fDst2 - fDst1)));
        P1.add(P2);
        Hit.val = P2;
        return true;
    }

    static boolean InBox(Holder Hit, Vec3d B1, Vec3d B2, int Axis) {
        if (Axis == 1 && Hit.val.getZ() > B1.getZ() && Hit.val.getZ() < B2.getZ() && Hit.val.getY() > B1.getY() && Hit.val.getY() < B2.y) return true;
        if (Axis == 2 && Hit.val.getZ() > B1.getZ() && Hit.val.getZ() < B2.getZ() && Hit.val.getX() > B1.getX() && Hit.val.getX() < B2.x) return true;
        return Axis == 3 && Hit.val.getX() > B1.getX() && Hit.val.getX() < B2.getX() && Hit.val.getY() > B1.getY() && Hit.val.getY() < B2.y;
    }
    /* end */
}
