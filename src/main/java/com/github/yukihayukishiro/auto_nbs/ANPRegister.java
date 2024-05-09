package com.github.yukihayukishiro.auto_nbs;

import java.io.File;
import java.nio.file.Path;

import com.github.yukihayukishiro.auto_nbs.command.testCommands.TuneNoteBlock;
import com.github.yukihayukishiro.auto_nbs.utils.AutoNbsTask;
import com.github.yukihayukishiro.auto_nbs.utils.MessageHandler;
import com.github.yukihayukishiro.auto_nbs.utils.Stage;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class ANPRegister {
    private static int delay;

    public static void registerEverything() {
        registerCommands();
        registerEvents();
    }

    private static void registerCommands() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher
                .register((LiteralArgumentBuilder<FabricClientCommandSource>) (ClientCommandManager.literal("anp")
                        .then((LiteralArgumentBuilder<FabricClientCommandSource>) (ClientCommandManager
                                .literal("tunenoteblock")
                                .then(ClientCommandManager.argument("note", IntegerArgumentType.integer())
                                        .executes(TuneNoteBlock::run)))))
                        .then((LiteralArgumentBuilder<FabricClientCommandSource>) (ClientCommandManager
                                .literal("hitnote")
                                .then(ClientCommandManager.argument("num", IntegerArgumentType.integer())
                                        .executes(TuneNoteBlock::run_b))))
                        .then((LiteralArgumentBuilder<FabricClientCommandSource>) (ClientCommandManager.literal("stage")
                                .executes(cont -> {
                                    Stage stage = new Stage(AutoNbsPlayer.MC.player.getBlockPos());
                                    stage.playNote("BASEDRUM", 0);
                                    stage.playNote("HARP", 0);
                                    stage.playNote("HARP", 4);
                                    stage.playNote("HARP", 24);
                                    return 1;
                                })

                        ))

                ));
    }

    private static void registerEvents() {
        ClientTickEvents.START_CLIENT_TICK.register(listener -> {
            if (delay > 0) {
                // MessageHandler.sendFeedback("delay left : "+ delay);
                delay--;
                return;
            }
            // Do something every tick
            if (!AutoNbsPlayer.queue.isEmpty()) {
                AutoNbsTask task = AutoNbsPlayer.queue.poll();
                task.run();
                delay += task.get_delay();
            }

        });
    }
}
