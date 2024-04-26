package com.github.yukihayukishiro.auto_nbs.utils;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtIo;

public class NbtStructure {
    private List<HashMap<String, Object>> blocks;
    private List<HashMap<String, String>> palette;
    private List<Double> size;

    public NbtStructure(File file) {
        try {
            NbtCompound compound = readNbt(file);
            init(nbtToHashMap(compound));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public NbtStructure(NbtCompound compound) {
        init(nbtToHashMap(compound));
    }

    public NbtStructure(HashMap<String, List<Object>> rawStructure) {
        init(rawStructure);
    }

    private NbtCompound readNbt(File file) throws Exception {
        NbtCompound compound = NbtIo.readCompressed(file);
        return compound;
    }

    private HashMap<String, List<Object>> nbtToHashMap(NbtCompound compound) {
        String json = compound.toString();
        Gson gson = new Gson();
        HashMap<String, List<Object>> hashMap = gson.fromJson(json, HashMap.class);
        return hashMap;
    }

    private void init(HashMap<String, List<Object>> rawStructure) {
        this.blocks = (List) rawStructure.get("blocks");
        this.palette = (List) rawStructure.get("palette");
        this.size = (List) rawStructure.get("size");
    }

    public List<Double> getSize() {
        return this.size;
    }

    public List<HashMap<String, Object>> getBlocks() {
        return this.blocks;
    }

    public List<HashMap<String, String>> getPalette() {
        return this.palette;
    }

}
