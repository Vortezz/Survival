package fr.vortezz.survival.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SpawnEggMeta;

import fr.vortezz.survival.Main;

public class ItemGenerator {

	public static ItemStack createItem(Material material, String name) {
		ItemStack item = new ItemStack(material);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack trueItem() {
		FileConfiguration messages = Main.getMessages();
		ItemStack item = new ItemStack(Material.GREEN_WOOL);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§a" + messages.getString("activated"));
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack falseItem() {
		FileConfiguration messages = Main.getMessages();
		ItemStack item = new ItemStack(Material.RED_WOOL);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§c" + messages.getString("disabled"));
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack hammerItem(int size) {
		FileConfiguration messages = Main.getMessages();
		ItemStack item = new ItemStack(Material.DIAMOND_PICKAXE, size);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD + messages.getString("hammer"));
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.setCustomModelData(1);
		item.setItemMeta(meta);
		item.addEnchantment(Enchantment.DURABILITY, 3);
		return item;
	}

	public static ItemStack maxHammerItem(int size) {
		FileConfiguration messages = Main.getMessages();
		ItemStack item = new ItemStack(Material.DIAMOND_PICKAXE, size);
		ItemMeta meta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.AQUA + messages.getString("smelt"));
		lore.add(ChatColor.AQUA + messages.getString("fortune"));
		lore.add(ChatColor.AQUA + messages.getString("speed"));
		meta.setDisplayName(ChatColor.GOLD + messages.getString("hammer"));
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.setCustomModelData(1);
		item.setItemMeta(meta);
		item.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 2);
		item.addEnchantment(Enchantment.DIG_SPEED, 3);
		item.addEnchantment(Enchantment.DURABILITY, 3);
		return item;
	}

	public static ItemStack smeltUpgradeItem(int size) {
		FileConfiguration messages = Main.getMessages();
		ItemStack item = new ItemStack(Material.MUSHROOM_STEW, size);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD + messages.getString("usmelt"));
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.setCustomModelData(1);
		item.setItemMeta(meta);
		item.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
		return item;
	}

	public static ItemStack speedUpgradeItem(int size) {
		FileConfiguration messages = Main.getMessages();
		ItemStack item = new ItemStack(Material.MUSHROOM_STEW, size);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD + messages.getString("uspeed"));
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.setCustomModelData(2);
		item.setItemMeta(meta);
		item.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
		return item;
	}

	public static ItemStack fortuneUpgradeItem(int size) {
		FileConfiguration messages = Main.getMessages();
		ItemStack item = new ItemStack(Material.MUSHROOM_STEW, size);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD + messages.getString("ufortune"));
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.setCustomModelData(3);
		item.setItemMeta(meta);
		item.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
		return item;
	}

	public static ItemStack captureEggItem(int size) {
		FileConfiguration messages = Main.getMessages();
		ItemStack item = new ItemStack(Material.MUSHROOM_STEW, size);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE + messages.getString("egg"));
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.setCustomModelData(4);
		item.setItemMeta(meta);
		item.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
		return item;
	}

	public static ItemStack getFurnacedItem(Material material, int size) {
		switch (material.toString()) {
			case "COBBLESTONE":
				return new ItemStack(Material.STONE, size);
			case "SAND":
				return new ItemStack(Material.GLASS, size);
			case "RED_SAND":
				return new ItemStack(Material.GLASS, size);
			case "OAK_LOG":
				return new ItemStack(Material.CHARCOAL, size);
			case "SPRUCE_LOG":
				return new ItemStack(Material.CHARCOAL, size);
			case "BIRCH_LOG":
				return new ItemStack(Material.CHARCOAL, size);
			case "JUNGLE_LOG":
				return new ItemStack(Material.CHARCOAL, size);
			case "ACACIA_LOG":
				return new ItemStack(Material.CHARCOAL, size);
			case "DARK_OAK_LOG":
				return new ItemStack(Material.CHARCOAL, size);
			case "OAK_WOOD":
				return new ItemStack(Material.CHARCOAL, size);
			case "SPRUCE_WOOD":
				return new ItemStack(Material.CHARCOAL, size);
			case "BIRCH_WOOD":
				return new ItemStack(Material.CHARCOAL, size);
			case "JUNGLE_WOOD":
				return new ItemStack(Material.CHARCOAL, size);
			case "ACACIA_WOOD":
				return new ItemStack(Material.CHARCOAL, size);
			case "DARK_OAK_WOOD":
				return new ItemStack(Material.CHARCOAL, size);
			case "STRIPPED_OAK_LOG":
				return new ItemStack(Material.CHARCOAL, size);
			case "STRIPPED_SPRUCE_LOG":
				return new ItemStack(Material.CHARCOAL, size);
			case "STRIPPED_BIRCH_LOG":
				return new ItemStack(Material.CHARCOAL, size);
			case "STRIPPED_JUNGLE_LOG":
				return new ItemStack(Material.CHARCOAL, size);
			case "STRIPPED_ACACIA_LOG":
				return new ItemStack(Material.CHARCOAL, size);
			case "STRIPPED_DARK_OAK_LOG":
				return new ItemStack(Material.CHARCOAL, size);
			case "STRIPPED_OAK_WOOD":
				return new ItemStack(Material.CHARCOAL, size);
			case "STRIPPED_SPRUCE_WOOD":
				return new ItemStack(Material.CHARCOAL, size);
			case "STRIPPED_BIRCH_WOOD":
				return new ItemStack(Material.CHARCOAL, size);
			case "STRIPPED_JUNGLE_WOOD":
				return new ItemStack(Material.CHARCOAL, size);
			case "STRIPPED_ACACIA_WOOD":
				return new ItemStack(Material.CHARCOAL, size);
			case "STRIPPED_DARK_OAK_WOOD":
				return new ItemStack(Material.CHARCOAL, size);
			case "WET_SPONGE":
				return new ItemStack(Material.SPONGE, size);
			case "SANDSTONE":
				return new ItemStack(Material.SMOOTH_SANDSTONE, size);
			case "RED_SANDSTONE":
				return new ItemStack(Material.SMOOTH_RED_SANDSTONE, size);
			case "NETHERRACK":
				return new ItemStack(Material.NETHER_BRICKS, size);
			case "COAL_ORE":
				return new ItemStack(Material.COAL, size);
			case "IRON_ORE":
				return new ItemStack(Material.IRON_INGOT, size);
			case "GOLD_ORE":
				return new ItemStack(Material.GOLD_INGOT, size);
			case "NETHER_GOLD_ORE":
				return new ItemStack(Material.GOLD_NUGGET, size);
			case "NETHER_QUARTZ_ORE":
				return new ItemStack(Material.QUARTZ, size);
			case "LAPIS_ORE":
				return new ItemStack(Material.LAPIS_LAZULI, size);
			case "DIAMOND_ORE":
				return new ItemStack(Material.DIAMOND, size);
			case "REDSTONE_ORE":
				return new ItemStack(Material.REDSTONE, size);
			case "EMERALD_ORE":
				return new ItemStack(Material.EMERALD, size);
			case "CACTUS":
				return new ItemStack(Material.GREEN_DYE, size);
			case "KELP":
				return new ItemStack(Material.DRIED_KELP, size);
			case "QUARTZ_BLOCK":
				return new ItemStack(Material.SMOOTH_QUARTZ, size);
			case "CHORUS_FRUIT":
				return new ItemStack(Material.POPPED_CHORUS_FRUIT, size);
			case "WHITE_TERRACOTTA":
				return new ItemStack(Material.WHITE_GLAZED_TERRACOTTA, size);
			case "ORANGE_TERRACOTTA":
				return new ItemStack(Material.ORANGE_GLAZED_TERRACOTTA, size);
			case "MAGENTA_TERRACOTTA":
				return new ItemStack(Material.MAGENTA_GLAZED_TERRACOTTA, size);
			case "LIGHT_BLUE_TERRACOTTA":
				return new ItemStack(Material.LIGHT_BLUE_GLAZED_TERRACOTTA, size);
			case "YELLOW_TERRACOTTA":
				return new ItemStack(Material.YELLOW_GLAZED_TERRACOTTA, size);
			case "LIME_TERRACOTTA":
				return new ItemStack(Material.LIME_GLAZED_TERRACOTTA, size);
			case "PINK_TERRACOTTA":
				return new ItemStack(Material.PINK_GLAZED_TERRACOTTA, size);
			case "GRAY_TERRACOTTA":
				return new ItemStack(Material.GRAY_GLAZED_TERRACOTTA, size);
			case "LIGHT_GRAY_TERRACOTTA":
				return new ItemStack(Material.LIGHT_GRAY_GLAZED_TERRACOTTA, size);
			case "CYAN_TERRACOTTA":
				return new ItemStack(Material.CYAN_GLAZED_TERRACOTTA, size);
			case "PURPLE_TERRACOTTA":
				return new ItemStack(Material.PURPLE_GLAZED_TERRACOTTA, size);
			case "BLUE_TERRACOTTA":
				return new ItemStack(Material.BLUE_GLAZED_TERRACOTTA, size);
			case "BROWN_TERRACOTTA":
				return new ItemStack(Material.BROWN_GLAZED_TERRACOTTA, size);
			case "GREEN_TERRACOTTA":
				return new ItemStack(Material.GREEN_GLAZED_TERRACOTTA, size);
			case "RED_TERRACOTTA":
				return new ItemStack(Material.RED_GLAZED_TERRACOTTA, size);
			case "BLACK_TERRACOTTA":
				return new ItemStack(Material.BLACK_GLAZED_TERRACOTTA, size);
			case "GOLDEN_SWORD":
				return new ItemStack(Material.GOLD_NUGGET, size);
			case "GOLDEN_SHOWEL":
				return new ItemStack(Material.GOLD_NUGGET, size);
			case "GOLDEN_PICKAXE":
				return new ItemStack(Material.GOLD_NUGGET, size);
			case "GOLDEN_AXE":
				return new ItemStack(Material.GOLD_NUGGET, size);
			case "GOLDEN_HOE":
				return new ItemStack(Material.GOLD_NUGGET, size);
			case "GOLDEN_BOOTS":
				return new ItemStack(Material.GOLD_NUGGET, size);
			case "GOLDEN_CHESTPLATE":
				return new ItemStack(Material.GOLD_NUGGET, size);
			case "GOLDEN_HELMET":
				return new ItemStack(Material.GOLD_NUGGET, size);
			case "GOLDEN_LEGGINGS":
				return new ItemStack(Material.GOLD_NUGGET, size);
			case "IRON_SWORD":
				return new ItemStack(Material.IRON_NUGGET, size);
			case "IRON_SHOWEL":
				return new ItemStack(Material.IRON_NUGGET, size);
			case "IRON_PICKAXE":
				return new ItemStack(Material.IRON_NUGGET, size);
			case "IRON_AXE":
				return new ItemStack(Material.IRON_NUGGET, size);
			case "IRON_HOE":
				return new ItemStack(Material.IRON_NUGGET, size);
			case "IRON_BOOTS":
				return new ItemStack(Material.IRON_NUGGET, size);
			case "IRON_CHESTPLATE":
				return new ItemStack(Material.IRON_NUGGET, size);
			case "IRON_HELMET":
				return new ItemStack(Material.IRON_NUGGET, size);
			case "IRON_LEGGINGS":
				return new ItemStack(Material.IRON_NUGGET, size);
			case "ANCIENT_DEBRIS":
				return new ItemStack(Material.NETHERITE_SCRAP, size);
			case "RAW_COD":
				return new ItemStack(Material.COOKED_COD, size);
			case "RAW_SALMON":
				return new ItemStack(Material.COOKED_SALMON, size);
			case "BEEF":
				return new ItemStack(Material.COOKED_BEEF, size);
			case "CHICKEN":
				return new ItemStack(Material.COOKED_CHICKEN, size);
			case "RABBIT":
				return new ItemStack(Material.COOKED_RABBIT, size);
			case "MUTTON":
				return new ItemStack(Material.COOKED_MUTTON, size);
			case "POTATO":
				return new ItemStack(Material.BAKED_POTATO, size);
		}
		return null;
	}

	public static ItemStack getSmeltedItem(Material material, int size) {
		switch (material.toString()) {
			case "COBBLESTONE":
				return new ItemStack(Material.STONE, size);
			case "SAND":
				return new ItemStack(Material.GLASS, size);
			case "RED_SAND":
				return new ItemStack(Material.GLASS, size);
			case "SANDSTONE":
				return new ItemStack(Material.SMOOTH_SANDSTONE, size);
			case "RED_SANDSTONE":
				return new ItemStack(Material.SMOOTH_RED_SANDSTONE, size);
			case "NETHERRACK":
				return new ItemStack(Material.NETHER_BRICKS, size);
			case "IRON_ORE":
				return new ItemStack(Material.IRON_INGOT, size);
			case "GOLD_ORE":
				return new ItemStack(Material.GOLD_INGOT, size);
			case "ANCIENT_DEBRIS":
				return new ItemStack(Material.NETHERITE_SCRAP, size);
		}
		return null;
	}

	public static boolean isEgg(String name) {
		if (name.contains("SPAWN_EGG"))
			return true;
		return false;
	}

}
