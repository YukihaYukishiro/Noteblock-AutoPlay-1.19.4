package com.github.yukihayukishiro.auto_nbs.Structure;

import java.io.File;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import com.github.yukihayukishiro.auto_nbs.AutoNbsPlayer;
import com.google.gson.Gson;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtIo;
import net.minecraft.text.Text;

public class NbtStructure {
    private List<Map<String, Object>> raw_blocks = new ArrayList<>();
    private Map<String, Object>[][][] blocks ;
    private List<Map<String, Object>> palette = new ArrayList<>();
    private List<Double> size = new ArrayList<>();

    public NbtStructure(File file) {
        try {
            NbtCompound compound = readNbt(file);
            init(nbtToMap(compound));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public NbtStructure(NbtCompound compound) {
        init(nbtToMap(compound));
    }

    public NbtStructure(Map<String, List<Object>> rawStructure) {
        init(rawStructure);
    }

    private NbtCompound readNbt(File file) throws Exception {
        NbtCompound compound = NbtIo.readCompressed(file);
        return compound;
    }

    private Map<String, List<Object>> nbtToMap(NbtCompound compound) {
        String json = compound.toString();
        Gson gson = new Gson();
        Map<String, List<Object>> hashMap = gson.fromJson(json, Map.class);
        return hashMap;
    }

    private void init(Map<String, List<Object>> rawStructure) {

        this.raw_blocks = (List) rawStructure.get("blocks");
        this.palette = (List) rawStructure.get("palette");
        this.size =(List) rawStructure.get("size");

        this.blocks = new Map[(int) Math.round(this.size.get(0))][(int) Math.round(this.size.get(1))][(int) Math.round(this.size.get(2))];
        

        for (Map<String, Object> block : this.raw_blocks) {
            List<Double> pos =  (List<Double>) block.get("pos");
            Map<String, Object> blockInPalette = this.palette.get((int) Math.round((Double) block.get("state"))); 
            this.blocks[pos.get(0).intValue()][pos.get(1).intValue()][pos.get(2).intValue()] = blockInPalette;
        }

    }

    public List<Double> getSize() {
        return this.size;
    }

    public List<Map<String, Object>> getraw_Blocks() {
        return this.raw_blocks;
    }

    public Map<String, Object>[][][] getBlocks() {
        return this.blocks;
    }

    public List<Map<String, Object>> getPalette() {
        return this.palette;
    }


}
