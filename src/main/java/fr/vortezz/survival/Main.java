package fr.vortezz.survival;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import fr.vortezz.survival.commands.Config;
import fr.vortezz.survival.commands.Feed;
import fr.vortezz.survival.commands.Furnace;
import fr.vortezz.survival.commands.Give;
import fr.vortezz.survival.commands.Heal;
import fr.vortezz.survival.commands.Reload;
import fr.vortezz.survival.craft.Craft;
import fr.vortezz.survival.listeners.BlockEvents;
import fr.vortezz.survival.listeners.ClickEvents;
import fr.vortezz.survival.listeners.InventoriesEvents;

public class Main extends JavaPlugin {

	public static FileConfiguration config;
	public static FileConfiguration messages;

	private static Main instance;

	private File languageFileEn;
	private File languageFileFr;

	@Override
	public void onEnable() {
		System.out.println("[Survival] Launching plugin !");

		instance = this;

		this.saveDefaultConfig();

		try {
			initConfig();

			languageFileEn = new File(getDataFolder(), "en.yml");
			languageFileFr = new File(getDataFolder(), "fr.yml");

			if (!languageFileEn.exists()) {
				languageFileEn.getParentFile().mkdirs();
				saveResource("en.yml", false);
			}

			if (!languageFileFr.exists()) {
				languageFileFr.getParentFile().mkdirs();
				saveResource("fr.yml", false);
			}
			initMessages();
			Craft.addRecipe();
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.getCommand("feed").setExecutor(new Feed());
		this.getCommand("heal").setExecutor(new Heal());
		this.getCommand("furnace").setExecutor(new Furnace());
		this.getCommand("sconfig").setExecutor(new Config());
		this.getCommand("sgive").setExecutor(new Give());
		this.getCommand("sreload").setExecutor(new Reload());

		Bukkit.getPluginManager().registerEvents(new ClickEvents(), this);
		Bukkit.getPluginManager().registerEvents(new BlockEvents(), this);
		Bukkit.getPluginManager().registerEvents(new InventoriesEvents(), this);

		System.out.println("[Survival] Plugin launched !");
	}

	@Override
	public void onDisable() {
		System.out.println("[Survival] See you later !");
	}

	private void initConfig() throws IOException {
		config = getConfig();
		System.out.println("[Survival] Config Initiated !");
	}

	private void initMessages() throws IOException {
		File file = new File(Main.getInstance().getDataFolder(), config.getString("messages.file"));
		try {
			messages = YamlConfiguration.loadConfiguration(file);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

		System.out.println("[Survival] Messages Initiated !");
	}

	public static void reloadConfigData() {
		File configFile = new File(Main.getInstance().getDataFolder(), "config.yml");
		try {
			config = YamlConfiguration.loadConfiguration(configFile);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	public static void reloadAll() {
		try {
			File configFile = new File(Main.getInstance().getDataFolder(), "config.yml");
			config = YamlConfiguration.loadConfiguration(configFile);
			File messagesFile = new File(Main.getInstance().getDataFolder(), config.getString("messages.file"));
			messages = YamlConfiguration.loadConfiguration(messagesFile);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	public static FileConfiguration getConfigData() {
		return config;
	}

	public static FileConfiguration getMessages() {
		return messages;
	}

	public static Main getInstance() {
		return instance;
	}

}
