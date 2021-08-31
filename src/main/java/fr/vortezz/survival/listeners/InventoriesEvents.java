package fr.vortezz.survival.listeners;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.vortezz.survival.Main;
import fr.vortezz.survival.inventories.ConfigInventories;
import net.md_5.bungee.api.ChatColor;

public class InventoriesEvents implements Listener {

	@EventHandler
	public void onInventoryOpenEvent(InventoryOpenEvent event) {
		FileConfiguration config = Main.getConfigData();
		Inventory inv = event.getInventory();
		if (inv.getType() == InventoryType.ENCHANTING && config.getBoolean("features.lapis.activated")) {
			inv.setItem(1, new ItemStack(Material.LAPIS_LAZULI, 64));
		} else if (inv != null && (inv.getType() == InventoryType.FURNACE
				|| inv.getType() == InventoryType.BLAST_FURNACE || inv.getType() == InventoryType.SMOKER)
				&& config.getBoolean("features.coal.activated")) {
			inv.setItem(1, new ItemStack(Material.COAL, 64));
		}
	}

	@EventHandler
	public void onInventoryCloseEvent(InventoryCloseEvent event) {
		FileConfiguration config = Main.getConfigData();
		Inventory inv = event.getInventory();
		if (inv.getType() == InventoryType.ENCHANTING && config.getBoolean("features.lapis.activated")) {
			inv.setItem(1, new ItemStack(Material.AIR));
		} else if (inv != null && (inv.getType() == InventoryType.FURNACE
				|| inv.getType() == InventoryType.BLAST_FURNACE || inv.getType() == InventoryType.SMOKER)
				&& config.getBoolean("features.coal.activated")) {

			inv.setItem(1, new ItemStack(Material.AIR));
		}
	}

	@EventHandler
	public void onFoodConsume(PlayerItemConsumeEvent event) {
		if (event.getItem().getType() == Material.MUSHROOM_STEW && event.getItem().hasItemMeta()
				? event.getItem().getItemMeta().hasCustomModelData()
				: false) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onAnvilPrepare(PrepareAnvilEvent event) {
		FileConfiguration messages = Main.getMessages();

		AnvilInventory anvil = event.getInventory();

		ItemStack one = anvil.getItem(0);
		ItemStack two = anvil.getItem(1);

		if (one == null || two == null)
			return;

		if (!one.getItemMeta().hasCustomModelData() || !two.getItemMeta().hasCustomModelData())
			return;

		if (one.getItemMeta().getCustomModelData() == 1 && one.getType() == Material.DIAMOND_PICKAXE
				&& (two.getItemMeta().getCustomModelData() > 0 && two.getItemMeta().getCustomModelData() < 4
						&& two.getType() == Material.MUSHROOM_STEW)) {

			ItemStack result = one;
			ItemMeta meta = result.getItemMeta();
			List<String> lore = new ArrayList<String>();
			switch (two.getItemMeta().getCustomModelData()) {
				case 1:
					if (haveUpgrade(one, ChatColor.AQUA + messages.getString("smelt"))) {
						generateRecipe(meta, one, lore, result, "smelt", event);
					} else
						return;
					break;
				case 2:
					if (haveUpgrade(one, ChatColor.AQUA + messages.getString("speed"))) {
						generateRecipe(meta, one, lore, result, "speed", event);
					} else
						return;
					break;
				case 3:
					if (haveUpgrade(one, ChatColor.AQUA + messages.getString("fortune"))) {
						generateRecipe(meta, one, lore, result, "fortune", event);
					} else
						return;
					break;
			}
		} else if (one.getType() == Material.DIAMOND_PICKAXE || one.getType() == Material.MUSHROOM_STEW
				|| one.getType() == Material.SLIME_BALL) {

			((Cancellable) event).setCancelled(true);

		}
	}

	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent event) throws IOException {
		FileConfiguration config = Main.getConfigData();
		FileConfiguration messages = Main.getMessages();
		Player player = (Player) event.getWhoClicked();
		Inventory inv = event.getClickedInventory();
		if (inv != null && inv.getType() == InventoryType.ENCHANTING && config.getBoolean("features.lapis.activated")) {
			if (event.getCurrentItem().getType() == Material.LAPIS_LAZULI) {
				event.setCancelled(true);
			}
		} else if (inv != null && (inv.getType() == InventoryType.FURNACE
				|| inv.getType() == InventoryType.BLAST_FURNACE || inv.getType() == InventoryType.SMOKER)
				&& config.getBoolean("features.coal.activated")) {
			if (event.getCurrentItem().getType() == Material.COAL) {
				event.setCancelled(true);
			}
		} else if (event.getView().getType() == InventoryType.GRINDSTONE && (((event.getInventory().getItem(0) != null
				? event.getInventory().getItem(0).getType() == Material.MUSHROOM_STEW
				: false)
				|| (event.getInventory().getItem(1) != null
						? event.getInventory().getItem(1).getType() == Material.MUSHROOM_STEW
						: false))
				|| (((event.getInventory().getItem(0) != null
						? event.getInventory().getItem(0).getType() == Material.DIAMOND_PICKAXE
						: false)
						&& (event.getInventory().getItem(0).hasItemMeta()
								? event.getInventory().getItem(0).getItemMeta().hasCustomModelData()
								: false))
						|| ((event.getInventory().getItem(1) != null
								? event.getInventory().getItem(1).getType() == Material.DIAMOND_PICKAXE
								: false)
								&& (event.getInventory().getItem(1).hasItemMeta()
										? event.getInventory().getItem(1).getItemMeta().hasCustomModelData()
										: false))))
				&& event.getRawSlot() == 2) {
			event.setCancelled(true);
		} else if (event.getView().getType() == InventoryType.ANVIL && (((event.getInventory().getItem(1) != null
				? event.getInventory().getItem(1).getType() != Material.MUSHROOM_STEW
				: true))
				&& (((event.getInventory().getItem(0) != null
						? event.getInventory().getItem(0).getType() == Material.DIAMOND_PICKAXE
						: false)
						&& (event.getInventory().getItem(0).hasItemMeta()
								? event.getInventory().getItem(0).getItemMeta().hasCustomModelData()
								: false))))
				&& event.getRawSlot() == 2) {

			event.setCancelled(true);

		} else if (event.getView().getType() == InventoryType.ANVIL && ((event.getInventory().getItem(0) != null
				? event.getInventory().getItem(0).getType() != Material.MUSHROOM_STEW
				: true)
				&& (event.getInventory().getItem(0).hasItemMeta()
						? event.getInventory().getItem(0).getItemMeta().hasCustomModelData()
						: false)
				&& event.getRawSlot() == 2)) {

			event.setCancelled(true);

		} else if (event.getView().getType() == InventoryType.ANVIL && ((event.getInventory().getItem(0) != null
				? event.getInventory().getItem(0).getType() != Material.SLIME_BALL
				: true)
				&& (event.getInventory().getItem(0).hasItemMeta()
						? event.getInventory().getItem(0).getItemMeta().hasCustomModelData()
						: false)
				&& event.getRawSlot() == 2)) {

			event.setCancelled(true);

		} else if (event.getView().getTitle().split(" - ")[0].equals(messages.getString("menuname"))) {
			if (player.hasPermission("survival.config.edit")) {
				File file = new File(Main.getInstance().getDataFolder(), "config.yml");
				int pageNumber = 1;
				if (event.getView().getTitle()
						.equals(messages.getString("menuname") + " - " + messages.getString("one"))) {
					pageNumber = 1;
					switch (event.getSlot()) {
						case 19:
							Main.getInstance().getConfig().set("features.autosmelt.activated",
									config.get("features.autosmelt.activated").equals(true) ? false : true);
							break;
						case 20:
							Main.getInstance().getConfig().set("features.autosmelt.fortune",
									config.get("features.autosmelt.fortune").equals(true) ? false : true);
							break;
						case 22:
							Main.getInstance().getConfig().set("features.lapis.activated",
									config.get("features.lapis.activated").equals(true) ? false : true);
							break;
						case 23:
							Main.getInstance().getConfig().set("features.coal.activated",
									config.get("features.coal.activated").equals(true) ? false : true);
							break;
						case 25:
							Main.getInstance().getConfig().set("features.commands.furnace",
									config.get("features.commands.furnace").equals(true) ? false : true);
							break;
						case 28:
							pageNumber = 1;
							break;
						case 29:
							pageNumber = 2;
							break;
						case 30:
							pageNumber = 3;
							break;
						case 34:
							pageNumber = 2;
							break;
						case 35:
							event.getView().close();
							return;
					}
				} else if (event.getView().getTitle()
						.equals(messages.getString("menuname") + " - " + messages.getString("two"))) {
					pageNumber = 2;
					switch (event.getSlot()) {
						case 19:
							Main.getInstance().getConfig().set("features.commands.feed",
									config.get("features.commands.feed").equals(true) ? false : true);
							break;
						case 20:
							Main.getInstance().getConfig().set("features.commands.heal",
									config.get("features.commands.heal").equals(true) ? false : true);
							break;
						case 22:
							Main.getInstance().getConfig().set("features.hammers.activated",
									config.get("features.hammers.activated").equals(true) ? false : true);
							break;
						case 23:
							Main.getInstance().getConfig().set("features.hammers.upgrades.smelt",
									config.get("features.hammers.upgrades.smelt").equals(true) ? false : true);
							break;
						case 24:
							Main.getInstance().getConfig().set("features.hammers.upgrades.speed",
									config.get("features.hammers.upgrades.speed").equals(true) ? false : true);
							break;
						case 25:
							Main.getInstance().getConfig().set("features.hammers.upgrades.fortune",
									config.get("features.hammers.upgrades.fortune").equals(true) ? false : true);
							break;
						case 27:
							pageNumber = 1;
							break;
						case 28:
							pageNumber = 1;
							break;
						case 29:
							pageNumber = 2;
							break;
						case 30:
							pageNumber = 3;
							break;
						case 34:
							pageNumber = 3;
							break;
						case 35:
							event.getView().close();
							return;
					}
				} else if (event.getView().getTitle()
						.equals(messages.getString("menuname") + " - " + messages.getString("three"))) {
					pageNumber = 3;
					switch (event.getSlot()) {
						case 19:
							Main.getInstance().getConfig().set("features.spawners.activated",
									config.get("features.spawners.activated").equals(true) ? false : true);
							break;
						case 20:
							Main.getInstance().getConfig().set("features.spawners.silktouch",
									config.get("features.spawners.silktouch").equals(true) ? false : true);
							break;
						case 21:
							Main.getInstance().getConfig().set("features.spawners.xp",
									config.get("features.spawners.xp").equals(true) ? false : true);
							break;
						case 23:
							Main.getInstance().getConfig().set("features.capture.activated",
									config.get("features.capture.activated").equals(true) ? false : true);
							break;
						case 27:
							pageNumber = 2;
							break;
						case 28:
							pageNumber = 1;
							break;
						case 29:
							pageNumber = 2;
							break;
						case 30:
							pageNumber = 3;
							break;
						case 35:
							event.getView().close();
							return;
					}
				}
				Main.getInstance().getConfig().save(file);
				Inventory configInventory = ConfigInventories.getConfigInv(player, pageNumber);
				player.openInventory(configInventory);
			} else {
				player.sendMessage(messages.getString("configperms"));
			}
			event.setCancelled(true);
		}
	}

	public static boolean haveUpgrade(ItemStack item, String upgrade) {
		List<String> lore = item.getItemMeta().getLore();
		if (lore != null) {
			if (!lore.contains(upgrade))
				return true;
		} else
			return true;
		return false;
	}

	public void sendAnvilRecipe(ItemStack item, PrepareAnvilEvent event) {
		Bukkit.getScheduler().runTask(Main.getInstance(), () -> {
			event.setResult(item);
			event.getInventory().setRepairCost(1);
			event.getInventory().setItem(2, item);
			((Player) event.getView().getPlayer()).updateInventory();
		});
	}

	public void generateRecipe(ItemMeta meta, ItemStack one, List<String> lore, ItemStack result, String type,
			PrepareAnvilEvent event) {
		FileConfiguration messages = Main.getMessages();
		FileConfiguration config = Main.getConfigData();
		if (!config.getBoolean("features.hammers.activated"))
			return;
		if (!config.getBoolean("features.hammers.upgrades.smelt") && type == "smelt")
			return;
		if (!config.getBoolean("features.hammers.upgrades.fortune") && type == "fortune")
			return;
		if (!config.getBoolean("features.hammers.upgrades.speed") && type == "speed")
			return;
		if (one.getItemMeta().getLore() == null) {
			lore.add(ChatColor.AQUA + messages.getString(type));
			meta.setLore(lore);
			result.setItemMeta(meta);
			if (type.equals("speed")) {
				result.addEnchantment(Enchantment.DIG_SPEED, 3);
				sendAnvilRecipe(result, event);
			} else if (type.equals("fortune")) {
				result.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 2);
				sendAnvilRecipe(result, event);
			} else {
				sendAnvilRecipe(result, event);
			}
		} else {
			lore = one.getItemMeta().getLore();
			lore.add(ChatColor.AQUA + messages.getString(type));
			meta.setLore(lore);
			result.setItemMeta(meta);
			if (type.equals("speed")) {
				result.addEnchantment(Enchantment.DIG_SPEED, 3);
				sendAnvilRecipe(result, event);
			} else if (type.equals("fortune")) {
				result.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 2);
				sendAnvilRecipe(result, event);
			} else {
				sendAnvilRecipe(result, event);
			}
		}
	}
}
