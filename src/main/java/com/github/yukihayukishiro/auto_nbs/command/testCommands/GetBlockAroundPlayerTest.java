package com.github.yukihayukishiro.auto_nbs.command.testCommands;

import java.io.File;
import java.nio.file.Path;
import java.util.Map;

import com.github.yukihayukishiro.auto_nbs.AutoNbsPlayer;
import com.github.yukihayukishiro.auto_nbs.utils.ConfirmStructure;
import com.github.yukihayukishiro.auto_nbs.utils.GetBlocksInRange;
import com.github.yukihayukishiro.auto_nbs.utils.NbtStructure;
import com.mojang.brigadier.context.CommandContext;

import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.block.BlockState;
import net.minecraft.block.NoteBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class GetBlockAroundPlayerTest {
    public static int run(CommandContext<FabricClientCommandSource> context) {
        PlayerEntity player = context.getSource().getPlayer();
        BlockPos playerPos = player.getBlockPos();
        player.sendMessage(Text.of("Player position: " + playerPos.toString()), false);
        
        File file = new File(Path.of("nbsplayer", "autonbsplay.nbt").toString());
        
        Boolean confirm = ConfirmStructure.confirmStructure(playerPos, file);

        if (confirm) {
            player.sendMessage(Text.of("Structure confirmed"), false);
        } else {
            player.sendMessage(Text.of("Structure not confirmed"), false);
        }
        

        return 1;
    }
}
