package fr.vortezz.survival.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import fr.vortezz.survival.Main;

public class Feed implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		FileConfiguration config = Main.getConfigData();
		FileConfiguration messages = Main.getMessages();
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (config.getBoolean("features.commands.feed")) {
				if (player.hasPermission("survival.feed")) {
					if (args.length > 0 && player.hasPermission("survival.feed.others")) {
						Player target = Bukkit.getPlayer(args[0]);
						if (target != null && target != player) {
							target.setFoodLevel(20);
							target.setSaturation(1);
							target.sendMessage(messages.getString("feedmsg"));
							player.sendMessage(messages.getString("feedother") + target.getName() + " !");
							return true;
						}
					}
					player.setFoodLevel(20);
					player.setSaturation(1);
					player.sendMessage(messages.getString("feedmsg"));
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
