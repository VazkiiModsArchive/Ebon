package vazkii.ebon.common;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import net.minecraft.server.MinecraftServer;
import net.minecraft.src.DamageSource;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import vazkii.codebase.common.CommonUtils;
import vazkii.ebon.api.ArmorType;
import vazkii.ebon.api.EbonAPIRegistry;
import vazkii.ebon.common.item.ItemZeroScepter.BombEntry;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class EbonModTickHandler implements ITickHandler {

	public static int elapsedTicks;

	public static HashMap<EntityPlayer, BombEntry> bombMappings = new HashMap();

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		if (type.equals(EnumSet.of(TickType.SERVER))) {
			++elapsedTicks;
			MinecraftServer server = CommonUtils.getServer();
			if (server == null) return;

			List<EntityPlayer> playerList = CommonUtils.getServer().getConfigurationManager().playerEntityList;
			handleMultipleScepters(playerList, tickData);
			handleLivingBombs(tickData);
			handleArmor(playerList, tickData);
		}
	}

	public void handleMultipleScepters(List<EntityPlayer> playerList, Object... tickData) {
		for (EntityPlayer player : playerList)
			if (hasMultipleScepters(player) && elapsedTicks % 20 == 0) {
				player.worldObj.playSoundAtEntity(player, "ebonmod.fail", 1.0F, 1.0F);
				player.attackEntityFrom(DamageSource.magic, 1);
			}
	}

	public void handleLivingBombs(Object... tickData) {
		Set<EntityPlayer> entriesToRemove = new LinkedHashSet();
		for (EntityPlayer player : bombMappings.keySet()) {
			BombEntry entry = bombMappings.get(player);
			entry.onTick();
			if (entry.isDone()) entriesToRemove.add(player);
		}
		for (EntityPlayer player : entriesToRemove)
			bombMappings.remove(player);
	}

	private boolean hasMultipleScepters(EntityPlayer player) {
		int count = 0;
		for (Item i : EbonAPIRegistry.scepterSet)
			if (player.inventory.hasItem(i.shiftedIndex)) count++;
		return count >= 2;
	}

	public void handleArmor(List<EntityPlayer> playerList, Object... tickData) {
		for (EntityPlayer player : playerList) {
			if (player.inventory.hasItemStack(new ItemStack(mod_Ebon.soulStone, 1, 1))) continue;

			ItemStack helm = player.inventory.armorInventory[3];
			ItemStack chest = player.inventory.armorInventory[2];
			ItemStack legs = player.inventory.armorInventory[1];
			ItemStack boots = player.inventory.armorInventory[0];

			if (helm != null && helm.itemID == mod_Ebon.ebonHood.shiftedIndex && helm.hasTagCompound()) EbonAPIRegistry.armorEffectNameMappings.get(helm.getTagCompound().getString("effect")).onTick(EnumSet.of(ArmorType.HELM), player);

			if (chest != null && chest.itemID == mod_Ebon.ebonRobeTop.shiftedIndex && chest.hasTagCompound()) EbonAPIRegistry.armorEffectNameMappings.get(chest.getTagCompound().getString("effect")).onTick(EnumSet.of(ArmorType.CHEST), player);

			if (legs != null && legs.itemID == mod_Ebon.ebonRobeBottom.shiftedIndex && legs.hasTagCompound()) EbonAPIRegistry.armorEffectNameMappings.get(legs.getTagCompound().getString("effect")).onTick(EnumSet.of(ArmorType.LEGS), player);

			if (boots != null && boots.itemID == mod_Ebon.ebonShoes.shiftedIndex && boots.hasTagCompound()) EbonAPIRegistry.armorEffectNameMappings.get(boots.getTagCompound().getString("effect")).onTick(EnumSet.of(ArmorType.BOOTS), player);
		}
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.SERVER);
	}

	@Override
	public String getLabel() {
		return "Ebon Mod";
	}

}
