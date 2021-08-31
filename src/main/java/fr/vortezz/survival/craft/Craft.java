package fr.vortezz.survival.craft;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import fr.vortezz.survival.Main;
import fr.vortezz.survival.utils.ItemGenerator;

public class Craft {

	public static void addRecipe() {
		FileConfiguration config = Main.getConfigData();
		if (config.getBoolean("features.hammers.activated"))
			addHammerRecipe();
		if (config.getBoolean("features.hammers.upgrades.smelt"))
			addSmeltUpgradeRecipe();
		if (config.getBoolean("features.hammers.upgrades.fortune"))
			addFortuneUpgradeRecipe();
		if (config.getBoolean("features.hammers.upgrades.speed"))
			addSpeedUpgradeRecipe();
		if (config.getBoolean("features.capture.activated"))
			addCaptureEggRecipe();
	}

	public static void addHammerRecipe() {

		ItemStack item = ItemGenerator.hammerItem(1);

		NamespacedKey key = new NamespacedKey(Main.getInstance(), "hammer");

		ShapedRecipe recipe = new ShapedRecipe(key, item);

		recipe.shape("DND", "DND", " S ");

		recipe.setIngredient('D', Material.DIAMOND);
		recipe.setIngredient('N', Material.NETHERITE_INGOT);
		recipe.setIngredient('S', Material.STICK);

		Bukkit.addRecipe(recipe);
	}

	public static void addSmeltUpgradeRecipe() {

		ItemStack item = ItemGenerator.smeltUpgradeItem(1);

		NamespacedKey key = new NamespacedKey(Main.getInstance(), "smelt_upgrade");

		ShapedRecipe recipe = new ShapedRecipe(key, item);

		recipe.shape("LCL", "C C", "LCL");

		recipe.setIngredient('C', Material.COAL_BLOCK);
		recipe.setIngredient('L', Material.LAVA_BUCKET);

		Bukkit.addRecipe(recipe);
	}

	public static void addSpeedUpgradeRecipe() {

		ItemStack item = ItemGenerator.speedUpgradeItem(1);

		NamespacedKey key = new NamespacedKey(Main.getInstance(), "speed_upgrade");

		ShapedRecipe recipe = new ShapedRecipe(key, item);

		recipe.shape("RSR", "S S", "RSR");

		recipe.setIngredient('R', Material.RABBIT_FOOT);
		recipe.setIngredient('S', Material.SUGAR);

		Bukkit.addRecipe(recipe);
	}

	public static void addFortuneUpgradeRecipe() {

		ItemStack item = ItemGenerator.fortuneUpgradeItem(1);

		NamespacedKey key = new NamespacedKey(Main.getInstance(), "fortune_upgrade");

		ShapedRecipe recipe = new ShapedRecipe(key, item);

		recipe.shape("GDG", "D D", "GDG");

		recipe.setIngredient('D', Material.DIAMOND);
		recipe.setIngredient('G', Material.GOLD_BLOCK);

		Bukkit.addRecipe(recipe);
	}

	public static void addCaptureEggRecipe() {

		ItemStack item = ItemGenerator.captureEggItem(1);

		NamespacedKey key = new NamespacedKey(Main.getInstance(), "captureegg");

		ShapedRecipe recipe = new ShapedRecipe(key, item);

		recipe.shape("III", "ISI", "III");

		recipe.setIngredient('I', Material.IRON_INGOT);
		recipe.setIngredient('S', Material.SNOWBALL);

		Bukkit.addRecipe(recipe);
	}

}
