package com.github.yukihayukishiro.auto_nbs.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import com.github.yukihayukishiro.auto_nbs.AutoNbsPlayer;

import com.github.yukihayukishiro.auto_nbs.Structure.ConfirmStructure;
import com.github.yukihayukishiro.auto_nbs.Structure.GetBlocksInRange;
import com.github.yukihayukishiro.auto_nbs.Structure.NbtStructure;

import net.minecraft.block.BlockState;
import net.minecraft.block.NoteBlock;
import net.minecraft.block.enums.Instrument;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class Stage {

    Map<String, BlockPos> stage = new HashMap<String, BlockPos>();

    public Stage(BlockPos centerPos) {
        AutoNbsPlayer.MC.player.sendMessage(Text.of("Initializing Stage with center pos:" + centerPos), false);

        int range = 4;
        int centerX = centerPos.getX();
        int centerY = centerPos.getY();
        int centerZ = centerPos.getZ();

        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                for (int z = 0; z < 9; z++) {
                    BlockPos current = new BlockPos(((centerX - range) + x), ((centerY - range) + y),
                            ((centerZ - range) + z));
                    BlockState block = AutoNbsPlayer.MC.world.getBlockState(current);
                    ;
                    if (block.getBlock() instanceof NoteBlock) {
                        AutoNbsPlayer.MC.player.sendMessage(Text.of("Note block found at:" + current), false);
                        Instrument instrument = block.get(NoteBlock.INSTRUMENT);
                        int note = block.get(NoteBlock.NOTE);
                        AutoNbsPlayer.MC.player.sendMessage(Text.of(instrument + ":" + note), false);
                        stage.put(instrument.toString() + ":" + note, current);
                    }

                }
            }
        }
    }

    public void playNote(String instrument, int note) {
        BlockPos target = stage.get(instrument + ":" + note);
        AutoNbsPlayer.MC.player.sendMessage(Text.of("Playing note:" + instrument + ":" + note + " at:" + target),
                false);
        AutoNbsPlayer.queue.add(new AutoNbsTask("left", target, 0));
    }

    public static void defalt_Stage(BlockPos centerPos, File file) {
        AutoNbsPlayer.MC.player.sendMessage(Text.of("Initializing Stage with center pos:" + centerPos), false);

        if (ConfirmStructure.confirmStructure(centerPos, file)) {
            MessageHandler.sendFeedback("Structure confirmed");
            MessageHandler.sendFeedback("Continue initialization");

            Map<String, Object>[][][] structure_blocks = new NbtStructure(file).getBlocks();

            BlockPos[][][] coordinates = GetBlocksInRange.getCoordinates(centerPos, structure_blocks.length,
                    structure_blocks[0].length, structure_blocks[0][0].length);
            for (int y = 0; y < coordinates.length; y++) {
                for (int x = 0; x < coordinates[0].length; x++) {
                    for (int z = 0; z < coordinates[0][0].length; z++) {
                        BlockState block = AutoNbsPlayer.MC.world.getBlockState(coordinates[x][y][z]);
                        String[] parts = block.getBlock().getTranslationKey().split("\\.");
                        String blockName = parts[parts.length - 1];

                        Map<String, Object> structure_properties = (Map<String, Object>) structure_blocks[x][y][z]
                                .get("Properties");

                        if (blockName.equals("note_block")) {
                            int structure_note = (int) Integer
                                    .parseInt(structure_properties.get("note").toString());
                            int note = (int) block.get(NoteBlock.NOTE);
                            BlockPos bp = coordinates[x][y][z];
                            if (note != structure_note) {
                                int target = structure_note;
                                int n = note;
                                if (target < note) {
                                    target += 25 - note;
                                    n = 0;
                                }

                                for (int i = n; i < target; i++) {
                                    AutoNbsPlayer.queue.add(new AutoNbsTask("right", bp, 0));
                                }
                            }

                        }
                    }
                }
            }

        } else {
            MessageHandler.sendFeedback("Structure not confirmed");
            MessageHandler.sendFeedback("Initialization failed");

        }

    }
}
