package com.github.yukihayukishiro.auto_nbs.utils;

import com.github.yukihayukishiro.auto_nbs.AutoNbsPlayer;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;

public class GetBlocksInRange {

    public static Block[][][] getBlocksInRange(BlockPos pos) {
        int range = 4;
        Block[][][] blocks = new Block[1 + range * 2][1 + range * 2][1 + range * 2];
        int centerX = pos.getX();
        int centerY = pos.getY();
        int centerZ = pos.getZ();

        for (int y = 0; y <= range * 2; y++) {
            for (int x = 0; x <= range * 2; x++) {
                for (int z = 0; z <= range * 2; z++) {
                    blocks[x][y][z] = AutoNbsPlayer.MC.world
                            .getBlockState(
                                    new BlockPos(((centerX - range) + x), ((centerY - range) + y),
                                            ((centerZ - range) + z)))
                            .getBlock();
                }
            }
        }

        return blocks;

    }

}
