package com.github.yukihayukishiro.auto_nbs.utils;

import com.github.yukihayukishiro.auto_nbs.AutoNbsPlayer;

import net.minecraft.text.Text;

public class MessageHandler {
    public static void sendFeedback(String message) {
        AutoNbsPlayer.MC.player.sendMessage(Text.of(message), false);
    }

}
