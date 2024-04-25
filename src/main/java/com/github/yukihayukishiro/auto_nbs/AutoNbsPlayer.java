package com.github.yukihayukishiro.auto_nbs;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AutoNbsPlayer implements ModInitializer {

	public static final MinecraftClient MC = MinecraftClient.getInstance();

	public static final String MOD_ID = "auto_nbs_player";
	public static final Logger LOGGER = LoggerFactory.getLogger("MOD_ID");
	public static final Path SONG_DIR = Path.of("nbsplayer");
	public static final Path SONG_FILE = Path.of("nbsplayer", "nbs");
	public static final Path PLAYLIST_PATH = Path.of("nbsplayer", "playlist");

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");

		if (!Files.exists(SONG_DIR)) {
			try {
				Files.createDirectories(SONG_DIR);
			} catch (IOException e) {
			}
		}
		if (!Files.exists(SONG_FILE)) {
			try {
				Files.createDirectories(SONG_FILE);
			} catch (IOException e) {
			}
		}
		if (!Files.exists(PLAYLIST_PATH)) {
			try {
				Files.createDirectories(PLAYLIST_PATH);
			} catch (IOException e) {
			}
		}
		ANPRegister.registerEverything();
	}

	public static void addChatMessage(String message) {
		MC.player.sendMessage(Text.of(message), false);
	}

	public static void addChatMessage(Text text) {
		MC.player.sendMessage(text, false);
	}

}