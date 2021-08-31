package fr.vortezz.survival.listeners;

import java.util.List;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import fr.vortezz.survival.Main;
import fr.vortezz.survival.utils.ItemGenerator;

public class BlockEvents implements Listener {

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		FileConfiguration config = Main.getConfigData();
		Player player = event.getPlayer();
		Block block = event.getBlock();
		if (config.getBoolean("features.autosmelt.activated") && player.getGameMode() == GameMode.SURVIVAL
				&& (player.getInventory().getItemInMainHand().getItemMeta() != null
						? !player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()
						: true)) {
			switch (block.getType().toString()) {
				case "IRON_ORE":
					getSmeltedIngot(Material.IRON_INGOT, event);
					break;
				case "GOLD_ORE":
					getSmeltedIngot(Material.GOLD_INGOT, event);
					break;
				case "ANCIENT_DEBRIS":
					getSmeltedIngot(Material.NETHERITE_SCRAP, event);
					break;
				default:
					break;
			}
		}
		if (config.getBoolean("features.lapis.activated") && block.getType() == Material.ENCHANTING_TABLE) {
			if (block.getState() instanceof InventoryHolder) {
				Inventory i = ((InventoryHolder) block.getState()).getInventory();
				i.setItem(1, new ItemStack(Material.AIR));
			}
		} else if (config.getBoolean("features.coal.activated")
				&& (block.getType() == Material.FURNACE || block.getType() == Material.BLAST_FURNACE
						|| block.getType() == Material.FURNACE_MINECART || block.getType() == Material.SMOKER)) {
			if (block.getState() instanceof InventoryHolder) {
				Inventory i = ((InventoryHolder) block.getState()).getInventory();
				i.setItem(1, new ItemStack(Material.AIR));
			}
		} else if (player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()
				&& player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1
				&& player.getInventory().getItemInMainHand().getType().equals(Material.DIAMOND_PICKAXE)
				&& player.getGameMode() == GameMode.SURVIVAL && config.getBoolean("features.hammers.activated")) {
			getHammerUpgrades(event, player);
			event.setCancelled(true);
		} else if (block.getType() == Material.SPAWNER && config.getBoolean("features.spawners.activated")) {
			if (player.getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH)
					&& config.getBoolean("features.spawners.silktouch")) {
				dropSpawner(block, config.getBoolean("features.spawners.xp"), event);
			} else if (!config.getBoolean("features.spawners.silktouch")) {
				dropSpawner(block, config.getBoolean("features.spawners.xp"), event);
			}
		}
	}

	public void getHammerUpgrades(BlockBreakEvent event, Player player) {
		if (InventoriesEvents.haveUpgrade(player.getInventory().getItemInMainHand(), "Smelt")) {
			doUpgrades(1, event, player);
		} else {
			doUpgrades(0, event, player);
		}
	}

	public void doUpgrades(int number, BlockBreakEvent event, Player player) {
		Location location = event.getBlock().getLocation();

		if (number == 1) {
			chooseHammerDirection(player, event, location, true);
		} else {
			chooseHammerDirection(player, event, location, false);
		}
	}

	public void chooseHammerDirection(Player player, BlockBreakEvent event, Location location, boolean smelt) {
		switch (getBlockFace(player).toString()) {
			case "WEST":
				breakZAxis(player, location, smelt, event);
				break;
			case "EAST":
				breakZAxis(player, location, smelt, event);
				break;
			case "NORTH":
				breakXAxis(player, location, smelt, event);
				break;
			case "SOUTH":
				breakXAxis(player, location, smelt, event);
				break;
			case "UP":
				breakYAxis(player, location, smelt, event);
				break;
			case "DOWN":
				breakYAxis(player, location, smelt, event);
				break;
		}
	}

	public void breakXAxis(Player player, Location location, boolean smelt, BlockBreakEvent event) {
		applyToolEffect(event, location.getBlockX() - 1, location.getBlockY() + 1, location.getBlockZ(), smelt);
		applyToolEffect(event, location.getBlockX() - 1, location.getBlockY(), location.getBlockZ(), smelt);
		applyToolEffect(event, location.getBlockX() - 1, location.getBlockY() - 1, location.getBlockZ(), smelt);
		applyToolEffect(event, location.getBlockX() + 1, location.getBlockY() + 1, location.getBlockZ(), smelt);
		applyToolEffect(event, location.getBlockX() + 1, location.getBlockY(), location.getBlockZ(), smelt);
		applyToolEffect(event, location.getBlockX() + 1, location.getBlockY() - 1, location.getBlockZ(), smelt);
		applyToolEffect(event, location.getBlockX(), location.getBlockY() + 1, location.getBlockZ(), smelt);
		applyToolEffect(event, location.getBlockX(), location.getBlockY(), location.getBlockZ(), smelt);
		applyToolEffect(event, location.getBlockX(), location.getBlockY() - 1, location.getBlockZ(), smelt);
	}

	public void breakYAxis(Player player, Location location, boolean smelt, BlockBreakEvent event) {
		applyToolEffect(event, location.getBlockX() + 1, location.getBlockY(), location.getBlockZ() - 1, smelt);
		applyToolEffect(event, location.getBlockX(), location.getBlockY(), location.getBlockZ() - 1, smelt);
		applyToolEffect(event, location.getBlockX() - 1, location.getBlockY(), location.getBlockZ() - 1, smelt);
		applyToolEffect(event, location.getBlockX() + 1, location.getBlockY(), location.getBlockZ() + 1, smelt);
		applyToolEffect(event, location.getBlockX(), location.getBlockY(), location.getBlockZ() + 1, smelt);
		applyToolEffect(event, location.getBlockX() - 1, location.getBlockY(), location.getBlockZ() + 1, smelt);
		applyToolEffect(event, location.getBlockX() + 1, location.getBlockY(), location.getBlockZ(), smelt);
		applyToolEffect(event, location.getBlockX(), location.getBlockY(), location.getBlockZ(), smelt);
		applyToolEffect(event, location.getBlockX() - 1, location.getBlockY(), location.getBlockZ(), smelt);
	}

	public void breakZAxis(Player player, Location location, boolean smelt, BlockBreakEvent event) {
		applyToolEffect(event, location.getBlockX(), location.getBlockY() + 1, location.getBlockZ() - 1, smelt);
		applyToolEffect(event, location.getBlockX(), location.getBlockY(), location.getBlockZ() - 1, smelt);
		applyToolEffect(event, location.getBlockX(), location.getBlockY() - 1, location.getBlockZ() - 1, smelt);
		applyToolEffect(event, location.getBlockX(), location.getBlockY() + 1, location.getBlockZ() + 1, smelt);
		applyToolEffect(event, location.getBlockX(), location.getBlockY(), location.getBlockZ() + 1, smelt);
		applyToolEffect(event, location.getBlockX(), location.getBlockY() - 1, location.getBlockZ() + 1, smelt);
		applyToolEffect(event, location.getBlockX(), location.getBlockY() + 1, location.getBlockZ(), smelt);
		applyToolEffect(event, location.getBlockX(), location.getBlockY(), location.getBlockZ(), smelt);
		applyToolEffect(event, location.getBlockX(), location.getBlockY() - 1, location.getBlockZ(), smelt);
	}

	public void applyToolEffect(BlockBreakEvent event, int x, int y, int z, boolean smelt) {
		int size = 0;
		if (smelt) {
			Material type = event.getPlayer().getWorld().getBlockAt(x, y, z).getType();
			if (ItemGenerator.getSmeltedItem(type, 1) != null) {
				for (ItemStack item : event.getPlayer().getWorld().getBlockAt(x, y, z).getDrops()) {
					if (event.getPlayer().getInventory().getItemInMainHand()
							.containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS)) {
						size = ((int) (Math.random() * (2 + 2)) - 1);
						if (size < 0) {
							size = 0;
						}
						event.getBlock().getWorld().dropItemNaturally(
								event.getPlayer().getWorld().getBlockAt(x, y, z).getLocation(),
								ItemGenerator.getFurnacedItem(item.getType(), size + 1));

					} else {
						event.getBlock().getWorld().dropItemNaturally(
								event.getPlayer().getWorld().getBlockAt(x, y, z).getLocation(),
								ItemGenerator.getFurnacedItem(item.getType(), size + 1));
					}

				}
				event.getPlayer().getWorld().getBlockAt(x, y, z).setType(Material.AIR);

			} else {
				event.getPlayer().getWorld().getBlockAt(x, y, z)
						.breakNaturally(event.getPlayer().getInventory().getItemInMainHand());
			}
		} else {
			event.getPlayer().getWorld().getBlockAt(x, y, z)
					.breakNaturally(event.getPlayer().getInventory().getItemInMainHand());
		}
	}

	public BlockFace getBlockFace(Player player) {
		List<Block> lastBlocks = player.getLastTwoTargetBlocks(null, 100);
		if (lastBlocks.size() != 2 || !lastBlocks.get(1).getType().isOccluding())
			return null;
		Block targetBlock = lastBlocks.get(1);
		Block adjacentBlock = lastBlocks.get(0);
		return targetBlock.getFace(adjacentBlock);
	}

	public void getSmeltedIngot(Material ingot, BlockBreakEvent event) {
		FileConfiguration config = Main.getConfigData();
		Location location = event.getBlock().getLocation();
		Player player = event.getPlayer();
		event.setCancelled(true);
		event.getBlock().setType(Material.AIR);
		int size = 0;
		if (config.getBoolean("features.autosmelt.fortune")) {
			int level = player.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);
			size = ((int) (Math.random() * (level + 2)) - 1);
			if (size < 0) {
				size = 0;
			}
		}
		ItemStack item = new ItemStack(ingot, size + 1);
		player.getWorld().dropItemNaturally(location, item);
	}

	public void dropSpawner(Block block, boolean xp, BlockBreakEvent event) {
		if (!xp) {
			event.setCancelled(true);
			block.setType(Material.AIR);
		} else {

			ItemStack item = new ItemStack(Material.SPAWNER);
			ItemStack egg = new ItemStack(Material
					.getMaterial(((CreatureSpawner) block.getState()).getSpawnedType().toString() + "_SPAWN_EGG"));

			block.getWorld().dropItemNaturally(block.getLocation(), item);
			if (egg.getType() != Material.PIG_SPAWN_EGG)
				block.getWorld().dropItemNaturally(block.getLocation(), egg);
		}
	}

}
