package vazkii.ebon.common;

import java.util.EnumSet;

import vazkii.ebon.api.ArmorType;
import vazkii.ebon.api.EbonAPIRegistry;
import vazkii.ebon.api.event.EbonStaffEvent;
import vazkii.ebon.api.event.StaffOfSoulsEvent;
import vazkii.ebon.common.block.BlockBloodLeafCrops;

import net.minecraft.src.Block;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityList;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.WorldClient;

import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;

public class EbonModHooks {

	@ForgeSubscribe
	public void onEntityDropItems(LivingDropsEvent event) {
		for (EntityItem item : event.drops)
			if (item.item.itemID == Item.rottenFlesh.shiftedIndex) {
				event.entity.dropItem(mod_Ebon.corpseDust.shiftedIndex, 1);
				return;
			}
	}

	@ForgeSubscribe
	public void onSoundLoad(SoundLoadEvent event) {
		for (String s : EbonModReference.SOUND_NAMES)
			event.manager.soundPoolSounds.addSound(String.format("ebonmod/%s.ogg", s), Block.class.getResource(String.format("/vazkii/ebon/client/resources/sound/%s.ogg", s)));
	}

	private static EntityPlayer interactingPlayer;

				@ForgeSubscribe
				public void onInteract(EntityInteractEvent event) {
					interactingPlayer = event.entityPlayer;
				}

				public synchronized static EntityPlayer getInteractingPlayer() {
					return interactingPlayer;
				}

				@ForgeSubscribe
				public void onEntityEvent(LivingEvent event) {
					if (event.entityLiving instanceof EntityPlayer && !(event.entityLiving.worldObj instanceof WorldClient)) {
						EntityPlayer player = (EntityPlayer) event.entityLiving;

						if (player.inventory.hasItemStack(new ItemStack(mod_Ebon.soulStone, 1, 1))) return;

						ItemStack helm = player.inventory.armorInventory[3];
						ItemStack chest = player.inventory.armorInventory[2];
						ItemStack legs = player.inventory.armorInventory[1];
						ItemStack boots = player.inventory.armorInventory[0];

						if (helm != null && helm.itemID == mod_Ebon.ebonHood.shiftedIndex && helm.hasTagCompound()) EbonAPIRegistry.armorEffectNameMappings.get(helm.getTagCompound().getString("effect")).onPlayerEvent(EnumSet.of(ArmorType.HELM), player, event);

						if (chest != null && chest.itemID == mod_Ebon.ebonRobeTop.shiftedIndex && chest.hasTagCompound()) EbonAPIRegistry.armorEffectNameMappings.get(chest.getTagCompound().getString("effect")).onPlayerEvent(EnumSet.of(ArmorType.CHEST), player, event);

						if (legs != null && legs.itemID == mod_Ebon.ebonRobeBottom.shiftedIndex && legs.hasTagCompound()) EbonAPIRegistry.armorEffectNameMappings.get(legs.getTagCompound().getString("effect")).onPlayerEvent(EnumSet.of(ArmorType.LEGS), player, event);

						if (boots != null && boots.itemID == mod_Ebon.ebonShoes.shiftedIndex && boots.hasTagCompound()) EbonAPIRegistry.armorEffectNameMappings.get(boots.getTagCompound().getString("effect")).onPlayerEvent(EnumSet.of(ArmorType.BOOTS), player, event);
					}
				}

				@ForgeSubscribe
				public void handleEbonStaff(EbonStaffEvent event) {
					int id = event.worldObj.getBlockId(event.posX, event.posY, event.posZ);
					if (id == mod_Ebon.bloodLeafCrops.blockID) {
						handleBLCrops(event);
						event.setCanceled(true);
					} else if (id == Block.stoneBrick.blockID) {
						handleStoneBricks(event);
						event.setCanceled(true);
					} else if (id == Block.bookShelf.blockID) {
						if (handleBookshelves(event)) event.setCanceled(true);
					} else if (EbonAPIRegistry.simpleTransmutations.containsKey(id)) {
						event.worldObj.setBlockWithNotify(event.posX, event.posY, event.posZ, EbonAPIRegistry.simpleTransmutations.get(id));
						event.setCanceled(true);
					}
				}

				public void handleBLCrops(EbonStaffEvent event) {
					if (event.worldObj.getBlockMetadata(event.posX, event.posY, event.posZ) < 7) BlockBloodLeafCrops.fertilizePropperly(event.worldObj, event.posX, event.posY, event.posZ);
				}

				public void handleStoneBricks(EbonStaffEvent event) {
					int meta = event.worldObj.getBlockMetadata(event.posX, event.posY, event.posZ);
					if (meta == 0 || meta == 3) event.worldObj.setBlockAndMetadata(event.posX, event.posY, event.posZ, Block.stoneBrick.blockID, meta == 0 ? 3 : 0);
				}

				public boolean handleBookshelves(EbonStaffEvent event) {
					if (!event.player.inventory.hasItem(mod_Ebon.altarBlueprint.shiftedIndex)) {
						event.player.dropPlayerItem(new ItemStack(mod_Ebon.altarBlueprint));
						return true;
					}
					return false;
				}

				@ForgeSubscribe
				public void handleStaffOfSouls(StaffOfSoulsEvent event) {
					Class clazz = event.entity.getClass();
					if (event.entity.worldObj instanceof WorldClient) return;
					do {
						if (EbonAPIRegistry.simpleTransfigurations.containsKey(clazz)) {
							event.entity.setDead();
							Entity entity = EntityList.createEntityByName(EbonAPIRegistry.simpleTransfigurations.get(clazz), event.entity.worldObj);
							entity.setLocationAndAngles(event.entity.posX, event.entity.posY + 0.33333, event.entity.posZ, event.entity.worldObj.rand.nextFloat() * 360F, 0.0F);
							event.entity.worldObj.spawnEntityInWorld(entity);

							event.setCanceled(true);
							return;
						}
						clazz = clazz.getSuperclass();
					} while (clazz != EntityLiving.class);
				}

}
