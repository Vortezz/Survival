package fr.vortezz.survival.utils;

import java.lang.reflect.Field;
import java.util.Base64;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import fr.vortezz.survival.Main;

public class HeadsGenerator {
	
	public static ItemStack getHead(String name) {
		FileConfiguration messages = Main.getMessages();
		GameProfile profile = new GameProfile(UUID.randomUUID(), null);
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", "http://textures.minecraft.net/texture/366a5c98928fa5d4b5d5b8efb490155b4dda3956bcaa9371177814532cfc").getBytes());
		switch (name) {
			case "next":
                encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", "http://textures.minecraft.net/texture/2a3b8f681daad8bf436cae8da3fe8131f62a162ab81af639c3e0644aa6abac2f").getBytes());
                meta.setDisplayName("§6" + messages.getString("next"));
                break;
			case "previous":
                encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", "http://textures.minecraft.net/texture/8652e2b936ca8026bd28651d7c9f2819d2e923697734d18dfdb13550f8fdad5f").getBytes());
                meta.setDisplayName("§6" + messages.getString("previous"));
                break;
			case "1":
                encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", "http://textures.minecraft.net/texture/71bc2bcfb2bd3759e6b1e86fc7a79585e1127dd357fc202893f9de241bc9e530").getBytes());
                meta.setDisplayName("§6" + messages.getString("one"));
                break;
			case "2":
                encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", "http://textures.minecraft.net/texture/4cd9eeee883468881d83848a46bf3012485c23f75753b8fbe8487341419847").getBytes());
                item.setAmount(2);
                meta.setDisplayName("§6" + messages.getString("two"));
                break;
			case "3":
                encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", "http://textures.minecraft.net/texture/1d4eae13933860a6df5e8e955693b95a8c3b15c36b8b587532ac0996bc37e5").getBytes());
                item.setAmount(3);
                meta.setDisplayName("§6" + messages.getString("two"));
                break;
		}
		profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField = null;
        try {
            profileField = meta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(meta, profile);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1) {
            e1.printStackTrace();
        }
        item.setItemMeta(meta);
		return item;
	}
}
