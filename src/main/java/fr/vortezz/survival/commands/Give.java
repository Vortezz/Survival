package fr.vortezz.survival.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import fr.vortezz.survival.Main;
import fr.vortezz.survival.utils.ItemGenerator;

public class Give implements CommandExecutor, TabCompleter {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		FileConfiguration messages = Main.getMessages();
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("survival.give")) {
				Player target = Bukkit.getPlayer(args[0]);
				// int size = args.length > 2 && Integer.parseInt(args[2]) > 0 &&
				// Integer.parseInt(args[2]) < 65 ? Integer.parseInt(args[2]) : 1;
				if (target != null) {
					switch (args[1]) {
						case "hammer":
							target.getInventory().addItem(ItemGenerator.hammerItem(1));
							player.sendMessage(messages.getString("give") + target.getName().toString() + " !");
							break;
						case "smelt":
							target.getInventory().addItem(ItemGenerator.smeltUpgradeItem(1));
							player.sendMessage(messages.getString("give") + target.getName().toString() + " !");
							break;
						case "fortune":
							target.getInventory().addItem(ItemGenerator.fortuneUpgradeItem(1));
							player.sendMessage(messages.getString("give") + target.getName().toString() + " !");
							break;
						case "speed":
							target.getInventory().addItem(ItemGenerator.speedUpgradeItem(1));
							player.sendMessage(messages.getString("give") + target.getName().toString() + " !");
							break;
						case "hammerfull":
							target.getInventory().addItem(ItemGenerator.maxHammerItem(1));
							player.sendMessage(messages.getString("give") + target.getName().toString() + " !");
							break;
						case "capture":
							target.getInventory().addItem(ItemGenerator.captureEggItem(1));
							player.sendMessage(messages.getString("give") + target.getName().toString() + " !");
							break;
						default:
							player.sendMessage(messages.getString("unknownitem"));
					}

				} else {
					player.sendMessage(messages.getString("nouser"));
				}

			} else {
				player.sendMessage(messages.getString("noperms"));
			}
			return true;
		}
		return false;
	}

	@Override

	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		if (args.length == 2) {
			List<String> arguments = new ArrayList<>();
			arguments.add("hammer");
			arguments.add("smelt");
			arguments.add("fortune");
			arguments.add("speed");
			arguments.add("hammerfull");
			arguments.add("capture");
			return arguments;
		}
		return null;
	}

}
