package com.github.yukihayukishiro.auto_nbs.utils;

import com.github.yukihayukishiro.auto_nbs.AutoNbsPlayer;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;

public class GetBlocksInRange {

    public static Block[][][] getBlocksInRange(BlockPos pos) {
        Block[][][] blocks = new Block[5][5][5];
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        int range = 2;
        for (int h = y - range; h <= y + range; h++) {
            for (int i = x + range; i >= x - range; i--) {
                for (int j = z + range; j >= z - range; j--) {
                    BlockPos newPos = new BlockPos(i, 0, j);
                    Block block = AutoNbsPlayer.MC.world.getBlockState(newPos).getBlock();
                    blocks[i - x + range][h - y + range][j - z + range] = block;
                }
            }
        }
        return blocks;

    }

}
