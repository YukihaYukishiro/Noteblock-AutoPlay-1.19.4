package com.github.yukihayukishiro.auto_nbs;

import com.github.yukihayukishiro.auto_nbs.command.testCommands.GetBlockAroundPlayerTest;
import com.github.yukihayukishiro.auto_nbs.command.testCommands.ReadNbt;
import com.github.yukihayukishiro.auto_nbs.command.testCommands.TuneNoteBlock;
import com.github.yukihayukishiro.auto_nbs.utils.MessageHandler;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents.StartTick;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.block.BlockState;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class ANPRegister {
    public static void registerEverything() {
        registerCommands();
        registerEvents();
    }

    private static void registerCommands() {
            ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher.register((LiteralArgumentBuilder<FabricClientCommandSource>)(ClientCommandManager.literal("anp")
                .then((LiteralArgumentBuilder<FabricClientCommandSource>)(ClientCommandManager.literal("tunenoteblock").then(ClientCommandManager.argument("note", IntegerArgumentType.integer())
                .executes(TuneNoteBlock::run))



                )))));
    }

    private static void registerEvents(){
        ClientTickEvents.START_CLIENT_TICK.register(listener -> {
            // Do something every tick
            if(!AutoNbsPlayer.tuneing_queue.isEmpty()){
                int[] tuneing = AutoNbsPlayer.tuneing_queue.poll();
                BlockPos pos = new BlockPos(tuneing[0],tuneing[1],tuneing[2]);
                AutoNbsPlayer.MC.interactionManager.interactBlock(AutoNbsPlayer.MC.player, Hand.MAIN_HAND, new BlockHitResult(new Vec3d(pos.getX()+0.5,pos.getY()+0.5,pos.getZ()+0.5), Direction.UP, pos, false));
                BlockState block = AutoNbsPlayer.MC.world.getBlockState(pos);
                int note = (int) block.get(net.minecraft.block.NoteBlock.NOTE);
                MessageHandler.sendFeedback("Note tuned to: "+ (note+1));
            }

        });
    }
}
