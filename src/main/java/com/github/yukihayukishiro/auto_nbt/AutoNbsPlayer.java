package com.github.yukihayukishiro.auto_nbt;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.minecraft.client.MinecraftClient;

public class AutoNbsPlayer implements ModInitializer {
	public static final MinecraftClient MC = MinecraftClient.getInstance();
	public static final String MOD_ID = "auto_nbs_player";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");
	}
}