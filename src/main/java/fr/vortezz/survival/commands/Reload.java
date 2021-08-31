package fr.vortezz.survival.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import fr.vortezz.survival.Main;

public class Reload implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("survival.reload")) {
				Main.reloadAll();
				FileConfiguration messages = Main.getMessages();
				player.sendMessage(messages.getString("reload"));
			} else {
				FileConfiguration messages = Main.getMessages();
				player.sendMessage(messages.getString("noperms"));
			}
			return true;
		}
		return false;
	}

}
