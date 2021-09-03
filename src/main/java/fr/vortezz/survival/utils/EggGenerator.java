package fr.vortezz.survival.utils;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.entity.Panda.Gene;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import de.tr7zw.changeme.nbtapi.NBTItem;

public class EggGenerator {

	public static ItemStack getEgg(Entity entity) {
		System.out.println(entity.getType().toString());
		if (Material.getMaterial(entity.getType().toString() + "_SPAWN_EGG") == null
				&& entity.getType().toString() != "MUSHROOM_COW")
			return null;
		ItemStack item = new ItemStack(
				entity.getType().toString() == "MUSHROOM_COW" ? Material.getMaterial("MOOSHROOM_SPAWN_EGG")
						: Material.getMaterial(entity.getType().toString() + "_SPAWN_EGG"),
				1);
		ItemMeta meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.setCustomModelData(1);
		item.setItemMeta(meta);
		item.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
		NBTItem nbtItem = new NBTItem(item);
		nbtItem.setString("CustomName", entity.getCustomName());
		nbtItem.setBoolean("Silent", entity.isSilent());
		nbtItem.setBoolean("NoAI", ((LivingEntity) entity).hasAI());
		nbtItem.setDouble("Health", ((Damageable) entity).getHealth());
		switch (entity.getType().toString()) {
			case "CAT":
				Cat cat = (Cat) entity;
				nbtItem.setString("Type", cat.getCatType().toString());
				nbtItem.setString("Owner", cat.getOwner().toString());
				nbtItem.setString("CollarColor", cat.getCollarColor().toString());
				break;
			case "CREEPER":
				Creeper creeper = (Creeper) entity;
				nbtItem.setBoolean("powered", creeper.isPowered());
				break;
			case "DONKEY":
				Donkey donkey = (Donkey) entity;
				nbtItem.setBoolean("Tame", donkey.isTamed());
				break;
			case "ENDERMAN":
				Enderman enderman = (Enderman) entity;
				nbtItem.setString("carriedMaterialState", enderman.getCarriedMaterial().toString());
				break;
			case "EVOKER":
				Evoker evoker = (Evoker) entity;
				nbtItem.setBoolean("PatrolLeader", evoker.isPatrolLeader());
				break;
			case "HORSE":
				Horse horse = (Horse) entity;
				nbtItem.setBoolean("Tame", horse.isTamed());
				nbtItem.setString("Variant", horse.getColor().toString());
				break;
			case "LLAMA":
				Llama llama = (Llama) entity;
				nbtItem.setBoolean("Tame", llama.isTamed());
				nbtItem.setString("Variant", llama.getColor().toString());
				break;
			case "MAGMA_CUBE":
				MagmaCube mcube = (MagmaCube) entity;
				nbtItem.setInteger("Size", mcube.getSize());
				break;
			case "MULE":
				Mule mule = (Mule) entity;
				nbtItem.setBoolean("Tame", mule.isTamed());
				nbtItem.setString("Variant", ((Horse) mule).getColor().toString());
				break;
			case "MUSHROOM_COW":
				MushroomCow mushroomCow = (MushroomCow) entity;
				nbtItem.setString("Variant", mushroomCow.getVariant().toString());
				break;
			case "PANDA":
				Panda panda = (Panda) entity;
				nbtItem.setString("MainGene", panda.getMainGene().toString());
				nbtItem.setString("HiddenGene", panda.getHiddenGene().toString());
				break;
			case "RABBIT":
				Rabbit rabbit = (Rabbit) entity;
				nbtItem.setString("Type", rabbit.getRabbitType().toString());
				break;
			case "SHEEP":
				Sheep sheep = (Sheep) entity;
				nbtItem.setString("Color", sheep.getColor().toString());
				break;
			case "SHULKER":
				Shulker shulker = (Shulker) entity;
				nbtItem.setString("Color", shulker.getColor().toString());
				break;
			case "SLIME":
				Slime slime = (Slime) entity;
				nbtItem.setInteger("Size", slime.getSize());
				break;
			case "WOLF":
				Wolf wolf = (Wolf) entity;
				nbtItem.setString("Owner", wolf.getOwner().toString());
				nbtItem.setString("CollarColor", wolf.getCollarColor().toString());
				break;
			case "ZOMBIE_HORSE":
				ZombieHorse zhorse = (ZombieHorse) entity;
				nbtItem.setBoolean("Tame", zhorse.isTamed());
				break;
			case "FOX":
				Fox fox = (Fox) entity;
				nbtItem.setString("Type", fox.getFoxType().toString());
				break;
			case "PARROT":
				Parrot parrot = (Parrot) entity;
				nbtItem.setString("Variant", parrot.getVariant().toString());
				break;
			case "PILLAGER":
				Pillager pillager = (Pillager) entity;
				nbtItem.setBoolean("PatrolLeader", pillager.isPatrolLeader());
				break;
			case "TRADER_LLAMA":
				TraderLlama tllama = (TraderLlama) entity;
				nbtItem.setBoolean("Tame", tllama.isTamed());
				nbtItem.setString("Variant", tllama.getColor().toString());
				break;
		}
		ItemStack egg = nbtItem.getItem();
		return egg;
	}

	public static String getEggEntityType(String name) {
		return name.split("_SPAWN_EGG")[0];
	}

	public static void resolveEggNBTTags(PlayerInteractEvent event) {
		event.setCancelled(true);
		ItemStack egg = event.getItem();
		if (egg.getEnchantmentLevel(Enchantment.DURABILITY) != 3)
			return;
		NBTItem nbtItem = new NBTItem(egg);
		String entityType = getEggEntityType(egg.getType().toString());
		Entity entity = event.getPlayer().getWorld().spawnEntity(event.getPlayer().getLocation(),
				EntityType.fromName(entityType));
		((LivingEntity) entity).setAI(nbtItem.getBoolean("NoAI"));
		entity.setSilent(nbtItem.getBoolean("Silent"));
		entity.setCustomName(nbtItem.getString("CustomName"));
		((Damageable) entity).setHealth(nbtItem.getDouble("Health"));
		switch (entityType) {
			case "CAT":
				Cat cat = (Cat) entity;
				cat.setCatType(org.bukkit.entity.Cat.Type.valueOf(nbtItem.getString("Type")));
				cat.setOwner(Bukkit.getServer().getPlayer(nbtItem.getString("Owner")));
				cat.setCollarColor(DyeColor.valueOf(nbtItem.getString("CollarColor")));
				break;
			case "CREEPER":
				Creeper creeper = (Creeper) entity;
				creeper.setPowered(nbtItem.getBoolean("powered"));
				break;
			case "DONKEY":
				Donkey donkey = (Donkey) entity;
				donkey.setTamed(nbtItem.getBoolean("Tame"));
				break;
			case "ENDERMAN":
				Enderman enderman = (Enderman) entity;
				enderman.setCarriedMaterial(
						new MaterialData(Material.getMaterial(nbtItem.getString("carriedMaterialState"))));
				break;
			case "EVOKER":
				Evoker evoker = (Evoker) entity;
				evoker.setPatrolLeader(nbtItem.getBoolean("PatrolLeader"));
				break;
			case "HORSE":
				Horse horse = (Horse) entity;
				horse.setTamed(nbtItem.getBoolean("Tame"));
				horse.setColor(org.bukkit.entity.Horse.Color.valueOf(nbtItem.getString("Variant")));
				break;
			case "LLAMA":
				Llama llama = (Llama) entity;
				llama.setTamed(nbtItem.getBoolean("Tame"));
				llama.setColor(org.bukkit.entity.Llama.Color.valueOf(nbtItem.getString("Variant")));
				break;
			case "MAGMA_CUBE":
				MagmaCube mcube = (MagmaCube) entity;
				mcube.setSize(nbtItem.getInteger("Size"));
				break;
			case "MULE":
				Mule mule = (Mule) entity;
				mule.setTamed(nbtItem.getBoolean("Tame"));
				((Horse) mule).setColor(org.bukkit.entity.Horse.Color.valueOf(nbtItem.getString("Variant")));
				break;
			case "MUSHROOM_COW":
				MushroomCow mushroomCow = (MushroomCow) entity;
				mushroomCow.setVariant(org.bukkit.entity.MushroomCow.Variant.valueOf(nbtItem.getString("Variant")));
				break;
			case "PANDA":
				Panda panda = (Panda) entity;
				panda.setMainGene(Gene.valueOf(nbtItem.getString("MainGene")));
				panda.setHiddenGene(Gene.valueOf(nbtItem.getString("HiddenGene")));
				break;
			case "RABBIT":
				Rabbit rabbit = (Rabbit) entity;
				rabbit.setRabbitType(org.bukkit.entity.Rabbit.Type.valueOf(nbtItem.getString("Type")));
				break;
			case "SHEEP":
				Sheep sheep = (Sheep) entity;
				sheep.setColor(DyeColor.valueOf(nbtItem.getString("Color")));
				break;
			case "SHULKER":
				Shulker shulker = (Shulker) entity;
				shulker.setColor(DyeColor.valueOf(nbtItem.getString("Color")));
				break;
			case "SKELETON_HORSE":
				SkeletonHorse shorse = (SkeletonHorse) entity;
				shorse.setTamed(nbtItem.getBoolean("Tame"));
				break;
			case "SLIME":
				Slime slime = (Slime) entity;
				slime.setSize(nbtItem.getInteger("Size"));
				break;
			case "WOLF":
				Wolf wolf = (Wolf) entity;
				wolf.setOwner(Bukkit.getServer().getPlayer(nbtItem.getString("Owner")));
				wolf.setCollarColor(DyeColor.valueOf(nbtItem.getString("CollarColor")));
				break;
			case "ZOMBIE_HORSE":
				ZombieHorse zhorse = (ZombieHorse) entity;
				zhorse.setTamed(nbtItem.getBoolean("Tame"));
				break;
			case "FOX":
				Fox fox = (Fox) entity;
				fox.setFoxType(org.bukkit.entity.Fox.Type.valueOf(nbtItem.getString("Type")));
				break;
			case "PARROT":
				Parrot parrot = (Parrot) entity;
				parrot.setVariant(org.bukkit.entity.Parrot.Variant.valueOf(nbtItem.getString("Variant")));
				break;
			case "PILLAGER":
				Pillager pillager = (Pillager) entity;
				pillager.setPatrolLeader(nbtItem.getBoolean("PatrolLeader"));
				break;
			case "TRADER_LLAMA":
				TraderLlama tllama = (TraderLlama) entity;
				tllama.setTamed(nbtItem.getBoolean("Tame"));
				tllama.setColor(org.bukkit.entity.TraderLlama.Color.valueOf(nbtItem.getString("Variant")));
				break;
		}
		if (event.getPlayer().getGameMode() != GameMode.CREATIVE)
			event.getPlayer().getInventory().getItemInMainHand()
					.setAmount(event.getPlayer().getInventory().getItemInMainHand().getAmount() - 1);
	}

}
