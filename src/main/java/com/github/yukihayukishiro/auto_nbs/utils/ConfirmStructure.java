package com.github.yukihayukishiro.auto_nbs.utils;

import java.io.File;
import java.util.Map;

import com.github.yukihayukishiro.auto_nbs.AutoNbsPlayer;

import net.minecraft.block.BlockState;
import net.minecraft.block.NoteBlock;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class ConfirmStructure {
    public static boolean confirmStructure(BlockPos pos, File file) {
        Map<String, Object>[][][] structure_blocks = new NbtStructure(file).getBlocks();

        BlockPos[][][] coordinates = GetBlocksInRange.getCoordinates(pos, structure_blocks.length,
                structure_blocks[0].length, structure_blocks[0][0].length);
        Boolean isMatched = true;
        for (int y = 0; y < coordinates.length; y++) {
            // AutoNbsPlayer.MC.player.sendMessage(Text.of("=====[ " + y + " ]====="),
            // false);
            for (int x = 0; x < coordinates[0].length; x++) {
                for (int z = 0; z < coordinates[0][0].length; z++) {
                    BlockState block = AutoNbsPlayer.MC.world.getBlockState(coordinates[x][y][z]);
                    String[] parts = block.getBlock().getTranslationKey().split("\\.");
                    String blockName = parts[parts.length - 1];

                    String structure_name = (String) structure_blocks[x][y][z].get("Name");
                    String[] structure_name_parts = structure_name.split("\\:");
                    structure_name = structure_name_parts[structure_name_parts.length - 1];
                    // Map<String,Object> structure_properties = (Map<String,Object>)
                    // structure_blocks[x][y][z].get("Properties");

                    if (blockName.equals(structure_name)) {
                        if (blockName.equals("note_block")) {
                            // int structure_note = (int)
                            // Integer.parseInt(structure_properties.get("note").toString());
                            // int note = (int) block.get( NoteBlock.NOTE);
                            // String structure_inst = (String) structure_properties.get("instrument");
                            // String inst = (String) block.get( NoteBlock.INSTRUMENT).asString();
                            // AutoNbsPlayer.MC.player.sendMessage(Text.of("[ "+ "("+ x + "," + y + "," + z
                            // + ")" + "Note: {"+note+"} Structure Note: {"+structure_note + "}Inst: {"+
                            // inst + "}Structure Inst: {" + structure_inst + "} ]"), false);
                            // if(note != structure_note || !inst.equals(structure_inst)){
                            // //AutoNbsPlayer.MC.player.sendMessage(Text.of("Note or Instrument is not
                            // matched in" + x +","+y+ ","+z), false);
                            // isMatched = false;
                            // }
                        }
                    } else {
                        if (structure_name.equals("note_block")) {
                            isMatched = false;
                        }
                        // AutoNbsPlayer.MC.player.sendMessage(Text.of(structure_name+" is not matched
                        // for " + blockName + "in " + x +","+y+ ","+z), false);
                    }

                }
            }
        }
        // AutoNbsPlayer.MC.player.sendMessage(Text.of("Is Matched:
        // "+isMatched.toString()), false);

        return isMatched;

    }

}
