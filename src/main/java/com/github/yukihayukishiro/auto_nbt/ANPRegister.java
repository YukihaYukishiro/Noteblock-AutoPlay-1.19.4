package com.github.yukihayukishiro.auto_nbt;

import com.github.yukihayukishiro.auto_nbt.command.testCommands.TestingCommand;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class ANPRegister {
    public static void registerEverything() {
        registerCommands();
    }

    private static void registerCommands() {
        CommandRegistrationCallback.EVENT.register(TestingCommand::register);

    }
}
