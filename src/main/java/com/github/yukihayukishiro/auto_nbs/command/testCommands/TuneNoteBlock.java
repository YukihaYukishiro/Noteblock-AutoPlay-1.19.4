package com.github.yukihayukishiro.auto_nbs.command.testCommands;

import com.github.yukihayukishiro.auto_nbs.AutoNbsPlayer;
import com.github.yukihayukishiro.auto_nbs.utils.MessageHandler;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;

import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;

public class TuneNoteBlock {
    public static int run(CommandContext<FabricClientCommandSource> context) {
        int target = IntegerArgumentType.getInteger(context, "note") ;
        PlayerEntity player = AutoNbsPlayer.MC.player;
        final BlockPos playerPos = player.getBlockPos().add(0, -1, 0);

        MessageHandler.sendFeedback("Player position: " + playerPos.toString());

        BlockState block = AutoNbsPlayer.MC.world.getBlockState(playerPos);
        int note = (int) block.get(net.minecraft.block.NoteBlock.NOTE);
        MessageHandler.sendFeedback("Target: " + target);

        if(target<note){
            target+=25-note;
            note=0;
        }

        for(int i = note; i<target;i++){
            AutoNbsPlayer.tuneing_queue.add(new int[]{playerPos.getX(),playerPos.getY(),playerPos.getZ()});
        }
        





        return 1;
    }

    
}
