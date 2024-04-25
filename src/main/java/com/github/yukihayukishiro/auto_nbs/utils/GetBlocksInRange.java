package com.github.yukihayukishiro.auto_nbs.utils;

import com.github.yukihayukishiro.auto_nbs.AutoNbsPlayer;

import net.minecraft.block.Block;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class GetBlocksInRange {

    public static Block[][][] getBlocksInRange(BlockPos pos) {
        Block[][][] blocks = new Block[5][5][5];
        int centerX = pos.getX();
        int centerY = pos.getY();
        int centerZ = pos.getZ();
        int range = 2;

        for (int y = centerY - range; y <= centerY + range; y++) {
            for (int x = centerX + range; x >= centerX - range; x--) {
                for (int z = centerZ + range; z >= centerZ - range; z--) {

                    BlockPos newPos = new BlockPos(x, y, z);
                    Block block = AutoNbsPlayer.MC.world.getBlockState(newPos).getBlock();
                    blocks[(centerX + range) - x][(centerY + range) - y][(centerZ + range) - z] = block;
                }
            }
        }
        AutoNbsPlayer.MC.player.sendMessage(Text.of("First = " + blocks[0][0][0].getName().getString()), false);
        AutoNbsPlayer.MC.player.sendMessage(Text.of("Last = " + blocks[4][4][4].getName().getString()), false);
        return blocks;

    }

}
