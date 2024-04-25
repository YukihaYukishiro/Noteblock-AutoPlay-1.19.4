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
        player.sendMessage(Text.of(, false);
        return 1;
    }
}
