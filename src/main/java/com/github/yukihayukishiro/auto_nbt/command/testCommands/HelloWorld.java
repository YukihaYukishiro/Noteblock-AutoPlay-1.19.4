package com.github.yukihayukishiro.auto_nbt.command.testCommands;

import com.mojang.brigadier.context.CommandContext;

import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class HelloWorld {
    public static int run(CommandContext<ServerCommandSource> context) {
        context.getSource().sendFeedback(Text.of("Hello, world!"), false);
        return 1;
    }
}
