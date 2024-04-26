package com.github.yukihayukishiro.auto_nbs.command.testCommands;

import java.io.File;
import java.nio.file.Path;
import java.util.Map;

import com.github.yukihayukishiro.auto_nbs.AutoNbsPlayer;
import com.github.yukihayukishiro.auto_nbs.utils.GetBlocksInRange;
import com.github.yukihayukishiro.auto_nbs.utils.NbtStructure;
import com.mojang.brigadier.context.CommandContext;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class GetBlockAroundPlayerTest {
    public static int run(CommandContext<ServerCommandSource> context) {
        context.getSource().sendFeedback(Text.of("Command executed!!"), false);

        PlayerEntity player = context.getSource().getPlayer();
        BlockPos playerPos = player.getBlockPos();
        player.sendMessage(Text.of("Player position: " + playerPos.toString()), false);

        BlockPos[][][] coordinates = GetBlocksInRange.getCoordinates(playerPos);

        File file = new File(Path.of("nbsplayer", "autonbsplay.nbt").toString());
        Map<String,Object>[][][] structure_blocks = new NbtStructure(file).getBlocks();
        Boolean isMatched = true;
        for (int y = 0; y < coordinates.length; y++) {
            AutoNbsPlayer.MC.player.sendMessage(Text.of("=====[ " + y + " ]====="), false);
            for (int x = 0; x < coordinates[0].length; x++) {
                String temp = "x" + x + "=>";
                for (int z = 0; z < coordinates[0][0].length; z++) {
                    BlockState block = AutoNbsPlayer.MC.world.getBlockState(coordinates[x][y][z]);
                    String[] parts = block.getBlock().getTranslationKey().split("\\.");
                    String blockName = parts[parts.length - 1];

                    String structure_name = (String) structure_blocks[x][y][z].get("Name");
                    String[] structure_name_parts = block.getBlock().getTranslationKey().split("\\.");
                    structure_name = structure_name_parts[structure_name_parts.length - 1];
                    //{Name=minecraft:note_block, Properties={instrument=harp, note=3, powered=false}}

                    if(blockName.equals(structure_name)){
                        temp += "[ " + blockName + x + "," + y + "," + z + " ]" + "," + "Matched with "+ structure_name ;
                    }else{
                        temp += "[ " + blockName + x + "," + y + "," + z + " ]" + "," + "Not Matchedwith "+ structure_name;
                        isMatched = false;
                    }

                }
                AutoNbsPlayer.MC.player.sendMessage(Text.of(temp), false);
            }
        }
        AutoNbsPlayer.MC.player.sendMessage(Text.of("Is Matched: "+isMatched.toString()), false);


        

        return 1;
    }
}
