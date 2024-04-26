package com.github.yukihayukishiro.auto_nbs.utils;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtIo;

public class NbtHandler {

    public static NbtCompound readNbt(File file) throws Exception {
        NbtCompound compound = NbtIo.readCompressed(file);
        return compound;
    }

    public static HashMap<String, List<Object>> nbtToHashMap(NbtCompound compound) {
        String json = compound.toString();
        Gson gson = new Gson();
        HashMap<String, List<Object>> hashMap = gson.fromJson(json, HashMap.class);
        return hashMap;
    }
}
