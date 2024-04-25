package com.github.yukihayukishiro.auto_nbs.command;

import com.github.yukihayukishiro.auto_nbs.command.testCommands.*;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public class RegisterModCommands {
        @SuppressWarnings({ "unchecked", "rawtypes" })
        public static void register(CommandDispatcher<ServerCommandSource> dispatcher,
                        CommandRegistryAccess registryAccess,
                        CommandManager.RegistrationEnvironment environment) {
                dispatcher.register((LiteralArgumentBuilder) (CommandManager.literal("autonbsplayer")
                                .then((LiteralArgumentBuilder) CommandManager.literal("test")
                                                .then((LiteralArgumentBuilder) CommandManager
                                                                .literal("helloworld")
                                                                .executes(HelloWorld::run))
                                                .then((LiteralArgumentBuilder) CommandManager
                                                                .literal("getblockaroundplayer")
                                                                .executes(GetBlockAroundPlayerTest::run)))));
        }

}
