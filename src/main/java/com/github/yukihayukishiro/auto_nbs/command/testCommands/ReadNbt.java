package com.github.yukihayukishiro.auto_nbs.command.testCommands;

import com.github.yukihayukishiro.auto_nbs.AutoNbsPlayer;
import com.github.yukihayukishiro.auto_nbs.utils.NbtStructure;
import com.mojang.brigadier.context.CommandContext;

import java.io.File;
import java.nio.file.Path;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class ReadNbt {
   public static int run(CommandContext<ServerCommandSource> context) {
      File file = new File(Path.of("nbsplayer", "test.nbt").toString());
      NbtStructure nbtStructure = new NbtStructure(file);

      AutoNbsPlayer.MC.player.sendMessage(Text.of("size: " + nbtStructure.getSize()), false);

      AutoNbsPlayer.MC.player.sendMessage(Text.of("list of block data"), false);
      AutoNbsPlayer.MC.player.sendMessage(Text.of(nbtStructure.getBlocks().toString()), false);
      AutoNbsPlayer.MC.player.sendMessage(Text.of("list of palette data"), false);
      AutoNbsPlayer.MC.player.sendMessage(Text.of(nbtStructure.getPalette().toString()), false);

      return 1;
   }

}
