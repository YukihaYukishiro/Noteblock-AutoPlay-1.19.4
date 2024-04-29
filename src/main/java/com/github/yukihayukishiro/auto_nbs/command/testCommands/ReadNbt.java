package com.github.yukihayukishiro.auto_nbs.command.testCommands;

import com.github.yukihayukishiro.auto_nbs.AutoNbsPlayer;
import com.github.yukihayukishiro.auto_nbs.Structure.NbtStructure;
import com.mojang.brigadier.context.CommandContext;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class ReadNbt {
   public static int run(CommandContext<FabricClientCommandSource> context){
      File file = new File(Path.of("nbsplayer", "autonbsplay.nbt").toString());
      NbtStructure nbtStructure = new NbtStructure(file);

      for (int y = 0; y < nbtStructure.getBlocks().length; y++) {
         AutoNbsPlayer.MC.player.sendMessage(Text.of("=====[ " + y + " ]====="), false);
         for (int x = 0; x < nbtStructure.getBlocks()[0].length; x++) {
            String temp = "x" + x + "=>";
            for (int z = 0; z < nbtStructure.getBlocks()[0][0].length; z++) {
               temp += "[ " + nbtStructure.getBlocks()[x][y][z].toString() + x + "," + y + "," + z + " ]" + ",";
            }
            AutoNbsPlayer.MC.player.sendMessage(Text.of(temp), false);
         }
      }

      try {

         Files.write(Path.of("nbsplayer", "autonbsplay.list"), (nbtStructure.getBlocks().toString()).getBytes());
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      return 1;
   }

}
