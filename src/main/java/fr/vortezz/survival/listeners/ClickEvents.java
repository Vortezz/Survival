package fr.vortezz.survival.listeners;

import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.vortezz.survival.Main;
import fr.vortezz.survival.utils.EggGenerator;
import fr.vortezz.survival.utils.ItemGenerator;

public class ClickEvents implements Listener {

	@EventHandler
	public void onPlayerClickEntity(PlayerInteractEntityEvent event) {
		FileConfiguration config = Main.getConfigData();
		Player player = event.getPlayer();
		if (player.getInventory().getItemInMainHand().getType() == Material.MUSHROOM_STEW
				&& (player.getInventory().getItemInMainHand().getItemMeta() != null
						? player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()
								? player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 4
								: false
						: false)
				&& event.getRightClicked().getType() != EntityType.PLAYER
				&& config.getBoolean("features.capture.activated")) {
			event.setCancelled(true);
			ItemStack egg = EggGenerator.getEgg(event.getRightClicked());
			if (egg == null) return;
			ItemMeta meta = egg.getItemMeta();
			event.getRightClicked().getWorld().dropItemNaturally(event.getRightClicked().getLocation(), egg);
			event.getRightClicked().getWorld().playEffect(event.getRightClicked().getLocation(), Effect.FIREWORK_SHOOT, 1);
			event.getRightClicked().remove();
			player.getInventory().getItemInMainHand()
					.setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
		}
	}

	@EventHandler
	public void onPlayerClick(PlayerInteractEvent event) {
		if (event.getClickedBlock() == null) return;
		Player player = event.getPlayer();
		if (ItemGenerator.isEgg(player.getInventory().getItemInMainHand().getType().toString())
				&& (player.getInventory().getItemInMainHand().getItemMeta() != null
						? player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()
						: false)
				&& event.getClickedBlock().getType() == Material.SPAWNER && player.getGameMode() != GameMode.CREATIVE) {
			event.setCancelled(true);
		} else if (event.getClickedBlock().getType() != Material.SPAWNER && ItemGenerator.isEgg(player.getInventory().getItemInMainHand().getType().toString())) {
			EggGenerator.resolveEggNBTTags(event);
		}
	}

}
