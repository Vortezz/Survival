package fr.vortezz.survival.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import fr.vortezz.survival.Main;
import fr.vortezz.survival.utils.HeadsGenerator;
import fr.vortezz.survival.utils.ItemGenerator;

public class ConfigInventories {

	public static Inventory getConfigInv(Player player, int page) {
		FileConfiguration config = Main.getConfigData();
		FileConfiguration messages = Main.getMessages();
		String[] menuNames = { messages.getString("menuname") + " - " + messages.getString("one"),
				messages.getString("menuname") + " - " + messages.getString("two"),
				messages.getString("menuname") + " - " + messages.getString("three") };
		Inventory configInventory = Bukkit.createInventory(player, 36, menuNames[page - 1]);
		configInventory.setItem(28, HeadsGenerator.getHead("1"));
		configInventory.setItem(29, HeadsGenerator.getHead("2"));
		configInventory.setItem(30, HeadsGenerator.getHead("3"));
		configInventory.setItem(35, ItemGenerator.createItem(Material.BARRIER, "§c" + messages.getString("close")));
		switch (page) {
			case 1:
				configInventory.setItem(10,
						ItemGenerator.createItem(Material.FURNACE, "§7" + messages.getString("autosmelt")));
				configInventory.setItem(19, config.getBoolean("features.autosmelt.activated") ? ItemGenerator.trueItem()
						: ItemGenerator.falseItem());
				configInventory.setItem(11,
						ItemGenerator.createItem(Material.DIAMOND, "§6" + messages.getString("fortune")));
				configInventory.setItem(20, config.getBoolean("features.autosmelt.fortune") ? ItemGenerator.trueItem()
						: ItemGenerator.falseItem());
				configInventory.setItem(13,
						ItemGenerator.createItem(Material.ENCHANTING_TABLE, "§9" + messages.getString("lapis")));
				configInventory.setItem(22, config.getBoolean("features.lapis.activated") ? ItemGenerator.trueItem()
						: ItemGenerator.falseItem());
				configInventory.setItem(14, ItemGenerator.createItem(Material.COAL, "§9" + messages.getString("coal")));
				configInventory.setItem(23, config.getBoolean("features.coal.activated") ? ItemGenerator.trueItem()
						: ItemGenerator.falseItem());
				configInventory.setItem(16,
						ItemGenerator.createItem(Material.LAVA_BUCKET, "§a" + messages.getString("furnace")));
				configInventory.setItem(25, config.getBoolean("features.commands.furnace") ? ItemGenerator.trueItem()
						: ItemGenerator.falseItem());
				configInventory.setItem(34, HeadsGenerator.getHead("next"));
				break;
			case 2:
				configInventory.setItem(10,
						ItemGenerator.createItem(Material.COOKED_BEEF, "§6" + messages.getString("feed")));
				configInventory.setItem(19, config.getBoolean("features.commands.feed") ? ItemGenerator.trueItem()
						: ItemGenerator.falseItem());
				configInventory.setItem(11,
						ItemGenerator.createItem(Material.APPLE, "§c" + messages.getString("heal")));
				configInventory.setItem(20, config.getBoolean("features.commands.heal") ? ItemGenerator.trueItem()
						: ItemGenerator.falseItem());
				configInventory.setItem(13,
						ItemGenerator.createItem(Material.DIAMOND_PICKAXE, "§6" + messages.getString("hammer")));
				configInventory.setItem(22, config.getBoolean("features.hammers.activated") ? ItemGenerator.trueItem()
						: ItemGenerator.falseItem());
				configInventory.setItem(14, ItemGenerator.createItem(Material.FURNACE,
						"§6" + messages.getString("smelt") + " " + messages.getString("upgrade")));
				configInventory.setItem(23,
						config.getBoolean("features.hammers.upgrades.smelt") ? ItemGenerator.trueItem()
								: ItemGenerator.falseItem());
				configInventory.setItem(15, ItemGenerator.createItem(Material.SUGAR,
						"§6" + messages.getString("speed") + " " + messages.getString("upgrade")));
				configInventory.setItem(24,
						config.getBoolean("features.hammers.upgrades.speed") ? ItemGenerator.trueItem()
								: ItemGenerator.falseItem());
				configInventory.setItem(16, ItemGenerator.createItem(Material.DIAMOND,
						"§6" + messages.getString("fortune") + " " + messages.getString("upgrade")));
				configInventory.setItem(25,
						config.getBoolean("features.hammers.upgrades.fortune") ? ItemGenerator.trueItem()
								: ItemGenerator.falseItem());
				configInventory.setItem(27, HeadsGenerator.getHead("previous"));
				configInventory.setItem(34, HeadsGenerator.getHead("next"));
				break;
			case 3:
				configInventory.setItem(10,
						ItemGenerator.createItem(Material.SPAWNER, "§7" + messages.getString("spawner")));
				configInventory.setItem(19, config.getBoolean("features.spawners.activated") ? ItemGenerator.trueItem()
						: ItemGenerator.falseItem());
				configInventory.setItem(11,
						ItemGenerator.createItem(Material.NETHERITE_PICKAXE, "§e" + messages.getString("silktouch")));
				configInventory.setItem(20, config.getBoolean("features.spawners.silktouch") ? ItemGenerator.trueItem()
						: ItemGenerator.falseItem());
				configInventory.setItem(12,
						ItemGenerator.createItem(Material.EXPERIENCE_BOTTLE, "§a" + messages.getString("xp")));
				configInventory.setItem(21, config.getBoolean("features.spawners.xp") ? ItemGenerator.trueItem()
						: ItemGenerator.falseItem());
				configInventory.setItem(14,
						ItemGenerator.createItem(Material.EGG, "§a" + messages.getString("egg")));
				configInventory.setItem(23, config.getBoolean("features.capture.activated") ? ItemGenerator.trueItem()
						: ItemGenerator.falseItem());
				configInventory.setItem(27, HeadsGenerator.getHead("previous"));

		}
		return configInventory;
	}

}
