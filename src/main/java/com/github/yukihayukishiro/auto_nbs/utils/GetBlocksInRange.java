package com.github.yukihayukishiro.auto_nbs.utils;

import net.minecraft.util.math.BlockPos;

public class GetBlocksInRange {

    public static BlockPos[][][] getCoordinates(BlockPos pos) {
        int range = 4;
        BlockPos[][][] coordinates = new BlockPos[1 + range * 2][1 + range * 2][1 + range * 2];
        int centerX = pos.getX();
        int centerY = pos.getY();
        int centerZ = pos.getZ();

        for (int y = 0; y <= range * 2; y++) {
            for (int x = 0; x <= range * 2; x++) {
                for (int z = 0; z <= range * 2; z++) {
                    coordinates[x][y][z] = new BlockPos(((centerX - range) + x), ((centerY - range) + y),
                            ((centerZ - range) + z));
                }
            }
        }

        return coordinates;
    }
}
