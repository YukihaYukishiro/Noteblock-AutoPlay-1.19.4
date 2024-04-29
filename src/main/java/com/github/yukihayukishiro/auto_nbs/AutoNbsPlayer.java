package com.github.yukihayukishiro.auto_nbs;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.yukihayukishiro.auto_nbs.utils.AutoNbsTask;

public class AutoNbsPlayer implements ModInitializer {

	public static Queue<AutoNbsTask> queue = new ArrayDeque<>();

	public static final MinecraftClient MC = MinecraftClient.getInstance();

	public static final String MOD_ID = "auto_nbs_player";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final Path MOD_DIR = Path.of("nbsplayer");
	public static final Path SONG_DIR = Path.of("nbsplayer", "nbs");
	public static final Path PLAYLIST_DIR = Path.of("nbsplayer", "playlist");

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");

		if (!Files.exists(MOD_DIR)) {
			try {
				Files.createDirectories(MOD_DIR);
			} catch (IOException e) {
			}
		}
		if (!Files.exists(SONG_DIR)) {
			try {
				Files.createDirectories(SONG_DIR);
			} catch (IOException e) {
			}
		}
		if (!Files.exists(PLAYLIST_DIR)) {
			try {
				Files.createDirectories(PLAYLIST_DIR);
			} catch (IOException e) {
			}
		}
		ANPRegister.registerEverything();
	}


}