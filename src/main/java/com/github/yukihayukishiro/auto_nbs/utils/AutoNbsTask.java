package com.github.yukihayukishiro.auto_nbs.utils;

import com.github.yukihayukishiro.auto_nbs.AutoNbsPlayer;

import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class AutoNbsTask {
    private int delay;
    private String type;
    private BlockPos blockPos;
    public AutoNbsTask(String clickType,BlockPos pos,int delay){
        this.type = clickType;
        this.blockPos = pos;
        this.delay = delay;
    }

    public void run(){
        switch (this.type) {
            case "right":
                AutoNbsPlayer.MC.interactionManager.interactBlock(AutoNbsPlayer.MC.player, Hand.MAIN_HAND, new BlockHitResult(new Vec3d(blockPos.getX(),blockPos.getY(),blockPos.getZ()), Direction.UP, blockPos, false));
                break;
            
            case "left":
                AutoNbsPlayer.MC.interactionManager.attackBlock(blockPos, Direction.UP);
                break;
        
            default:
                break;
        }
    }

    public int get_delay(){
        return this.delay;
    }

}
