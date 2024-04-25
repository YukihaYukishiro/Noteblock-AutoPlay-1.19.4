package com.github.yukihayukishiro.auto_nbs.command.testCommands;

import com.github.yukihayukishiro.auto_nbs.AutoNbsPlayer;
import com.github.yukihayukishiro.auto_nbs.utils.GetBlocksInRange;
import com.mojang.brigadier.context.CommandContext;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.Mutable;

public class GetBlockAroundPlayerTest {
    public static int run(CommandContext<ServerCommandSource> context) {
        context.getSource().sendFeedback(Text.of("Command executed!!"), false);

        PlayerEntity player = context.getSource().getPlayer();
        BlockPos playerPos = player.getBlockPos();
        player.sendMessage(Text.of("Player position: " + playerPos.toString()), false);
        Block[][][] area = GetBlocksInRange.getBlocksInRange(playerPos);
        // convert to string
        String[][][] areaString = new String[area.length][area[0].length][area[0][0].length];
        String temp;
        for (int y = 0; y < area.length; y++) {
            player.sendMessage(Text.of("=====[ " + y + " ]====="), false);
            for (int x = 0; x < area[0].length; x++) {
                temp = "x" + x + "=>";
                for (int z = 0; z < area[0][0].length; z++) {
                    areaString[x][y][z] = "[ " + area[x][y][z].getName().getString() + " ]";
                    temp += areaString[x][y][z] + ",";
                }
                player.sendMessage(Text.of(temp), false);
            }
        }
        player.sendMessage(Text.of("==============="), false);
        return 1;
    }
}
