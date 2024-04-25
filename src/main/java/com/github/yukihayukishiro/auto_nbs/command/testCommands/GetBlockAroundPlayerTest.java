package com.github.yukihayukishiro.auto_nbs.command.testCommands;

import com.github.yukihayukishiro.auto_nbs.utils.GetBlocksInRange;
import com.mojang.brigadier.context.CommandContext;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class GetBlockAroundPlayerTest {
    public static int run(CommandContext<ServerCommandSource> context) {
        context.getSource().sendFeedback(Text.of("Command executed!!"), false);

        PlayerEntity player = context.getSource().getPlayer();
        BlockPos playerPos = player.getBlockPos();
        player.sendMessage(Text.of("Player position: " + playerPos.toString()), false);
        Block[][][] area = GetBlocksInRange.getBlocksInRange(playerPos);
        player.sendMessage(Text.of("First: " + area[0][0][0].getName().getString()), false);
        player.sendMessage(Text.of("Last: " + area[4][4][4].getName().getString()), false);
        //convert area to string
        String temp = "";
        for (int y = 0; y < 5; y++) {
            player.sendMessage(Text.of("=====[ " + y + " ]====="), false);
            for (int x = 0; x < 5; x++) {
                temp = "x" + x + "=>";
                for (int z = 0; z < 5; z++) {
                    temp += "[ " + area[x][y][z].getName().getString() + " ]" + ",";
                }
                player.sendMessage(Text.of(temp), false);
            }
        }


        return 1;
    }
}
