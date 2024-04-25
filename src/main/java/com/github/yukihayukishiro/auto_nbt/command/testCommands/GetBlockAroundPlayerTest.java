package com.github.yukihayukishiro.auto_nbt.command.testCommands;

import com.mojang.brigadier.context.CommandContext;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.math.Position;

public class GetBlockAroundPlayerTest {
    public static int run(CommandContext<ServerCommandSource> context) {
        context.getSource().sendFeedback(Text.of("Command executed!"), false);

        PlayerEntity player = context.getSource().getPlayer();
        Position playerPos = player.getPos();
        player.sendMessage(Text.of("Player position: " + playerPos.toString()), false);

        return 1;
    }
}
