package com.github.yukihayukishiro.auto_nbs.Structure;

import net.minecraft.util.math.BlockPos;

public class GetBlocksInRange {

    public static BlockPos[][][] getCoordinates(BlockPos pos,int xSize, int ySize, int zSize) {
        int range = 4;
        BlockPos[][][] coordinates = new BlockPos[xSize][ySize][zSize];
        int xrange = (int) (xSize-1)/2;
        int yrange = (int) (ySize-1)/2;
        int zrange = (int) (zSize-1)/2;
        int centerX = pos.getX();
        int centerY = pos.getY();
        int centerZ = pos.getZ();

        for (int y = 0; y < coordinates.length; y++) {
            for (int x = 0; x < coordinates[0].length; x++) {
                for (int z = 0; z < coordinates[0][0].length; z++) {
                    coordinates[x][y][z] = new BlockPos(((centerX - xrange) + x), ((centerY - yrange) + y),
                            ((centerZ - zrange) + z));
                }
            }
        }

        return coordinates;
    }
}
