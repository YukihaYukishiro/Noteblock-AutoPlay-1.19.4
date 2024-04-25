package com.github.yukihayukishiro.auto_nbt.command.testCommands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;

import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class TestingCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess,
            CommandManager.RegistrationEnvironment environment) {
        dispatcher.register(
                CommandManager.literal("autonbsplayer")
                        .then(CommandManager.literal("test")
                                .then(CommandManager.literal("helloworld")
                                        .executes(TestingCommand::run))));
    }

    public static int run(CommandContext<ServerCommandSource> context) {
        context.getSource().sendFeedback(Text.of("Hello, world!"), false);
        return 1;
    }

}
