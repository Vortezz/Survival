package fr.vortezz.survival.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import fr.vortezz.survival.Main;
import fr.vortezz.survival.inventories.ConfigInventories;

public class Config implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		FileConfiguration messages = Main.getMessages();
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("survival.config.view")) {
				Inventory inv = ConfigInventories.getConfigInv(player, 1);
				player.openInventory(inv);
			} else {
				player.sendMessage(messages.getString("noperms"));
			}
			return true;
		}
		return false;
	}

}
