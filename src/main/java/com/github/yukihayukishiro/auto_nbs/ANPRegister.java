package com.github.yukihayukishiro.auto_nbs;

import com.github.yukihayukishiro.auto_nbs.command.RegisterModCommands;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class ANPRegister {
    public static void registerEverything() {
        registerCommands();
    }

    private static void registerCommands() {
        CommandRegistrationCallback.EVENT.register(RegisterModCommands::register);
    }
}
