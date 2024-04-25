package com.github.yukihayukishiro.auto_nbs.utils;

import com.github.yukihayukishiro.auto_nbs.AutoNbsPlayer;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;

public class GetBlocksInRange {

    public static Block[][][] getBlocksInRange(BlockPos pos) {
        Block[][][] blocks = new Block[5][5][5];
        int centerX = pos.getX();
        int centerY = pos.getY();
        int centerZ = pos.getZ();
        int range = 2;

        for (int y = 0; y <= range*2; y++) {
            for (int x = 0; x <= range*2; x++) {
                for (int z = 0; z <= range*2; z++) {
                    blocks[x][y][z] = AutoNbsPlayer.MC.world.getBlockState(new BlockPos(centerX + (range - x), centerY + (y - range), centerZ + (z - range))).getBlock();
                }
            }
        }

        return blocks;

    }

}
