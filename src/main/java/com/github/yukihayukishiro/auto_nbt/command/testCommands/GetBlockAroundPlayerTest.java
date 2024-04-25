package com.github.yukihayukishiro.auto_nbt.command.testCommands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;

import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class GetBlockAroundPlayerTest {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess,
            CommandManager.RegistrationEnvironment environment) {
        dispatcher.register(
                CommandManager.literal("autonbsplayer")
                        .then(CommandManager.literal("test")
                                .then(CommandManager.literal("getblockaroundplayer")
                                        .executes(TestingCommand::run))));
    }

    public static int run(CommandContext<ServerCommandSource> context) {
        context.getSource().sendFeedback(Text.of("Command executed!"), false);
        
        PlayerEntity player = context.getSource().getPlayer();
        Locati


        return 1;
    }
}
