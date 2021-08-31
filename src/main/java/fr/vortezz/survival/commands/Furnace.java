package fr.vortezz.survival.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.vortezz.survival.Main;
import fr.vortezz.survival.utils.ItemGenerator;

public class Furnace implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		FileConfiguration config = Main.getConfigData();
		FileConfiguration messages = Main.getMessages();
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (config.getBoolean("features.commands.furnace")) {
				if (player.hasPermission("survival.furnace")) {
					ItemStack item = ItemGenerator.getFurnacedItem(player.getInventory().getItemInMainHand().getType(),
							player.getInventory().getItemInMainHand().getAmount());
					if (item != null) {
						player.getInventory().setItemInMainHand(item);
						player.sendMessage(messages.getString("cooked"));
					}
				} else {
					player.sendMessage(messages.getString("noperms"));
				}
			} else {
				player.sendMessage(messages.getString("featuredisabled"));
			}
			return true;
		}
		return false;
	}

}