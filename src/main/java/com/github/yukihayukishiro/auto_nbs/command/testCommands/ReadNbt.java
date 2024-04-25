package com.github.yukihayukishiro.auto_nbs.command.testCommands;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtIo;

public class ReadNbt {
   public static void nbtTest() {
      File file = new File(Path.of("nbsplayer","mapart.nbt").toString());
      try {
        NbtCompound nbt = NbtIo.readCompressed(Files.newInputStream(file.toPath()));
        Files.write(Path.of("nbsplayer","mapart.txt"), nbt.toString().getBytes());
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}
