package vazkii.ebon.common.item;

import java.util.Random;

import vazkii.ebon.api.event.StaffOfSoulsEvent;
import vazkii.ebon.common.EbonModHelper;
import vazkii.ebon.common.EbonModHooks;
import vazkii.ebon.common.EbonModPacketHandler;
import vazkii.ebon.common.EbonModReference;
import vazkii.ebon.common.mod_Ebon;

import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumRarity;
import net.minecraft.src.ItemStack;
import net.minecraft.src.WorldClient;

import net.minecraftforge.common.MinecraftForge;

public class ItemStaffOfSouls extends ItemSpritesheet {

	public ItemStaffOfSouls(int par1) {
		super(par1);
		setMaxStackSize(1);
		setMaxDamage(EbonModReference.STAFF_OF_SOULS_DAMAGE);
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack itemstack, EntityLiving entityliving) {
		EntityPlayer player = EbonModHooks.getInteractingPlayer();
		System.out.println(entityliving.worldObj);
		if (entityliving.worldObj instanceof WorldClient) return false;

		if (!EbonModHelper.doesPlayerHaveME(player) && EbonModHelper.isDarknessEnough(player, EbonModReference.DARKNESS_MIN_STAFF_SOULS) && entityliving != null && !entityliving.isDead) {
			if (MinecraftForge.EVENT_BUS.post(new StaffOfSoulsEvent(itemstack, entityliving))) {
				Random random = new Random();
				for (int i = 0; i < EbonModReference.PARTICLE_COUNT; i++)
					EbonModPacketHandler.sendParticlePacket("portal", entityliving.posX + (random.nextDouble() - 0.5D) * entityliving.width, entityliving.posY + random.nextDouble() * entityliving.height, entityliving.posZ + (random.nextDouble() - 0.5D) * entityliving.width, (random.nextDouble() - 0.5D) * 2D, -random.nextDouble(), (random.nextDouble() - 0.5D) * 2D, true);
				EbonModHelper.addMEToPlayer(player, EbonModReference.ME_STAFF_SOULS);
				EbonModHelper.addShadeForPlayer(player, EbonModReference.SHADE_STAFF_SOULS);
				entityliving.worldObj.playSoundAtEntity(entityliving, "ebonmod.transmute", 1.0F, 1.0F);
				if (itemstack.getItemDamage() == EbonModReference.STAFF_OF_SOULS_DAMAGE) {
					ItemStack dropStack = new ItemStack(mod_Ebon.staffOfSouls);
					itemstack.damageItem(1, player);
					if (!player.inventory.addItemStackToInventory(dropStack)) player.dropPlayerItem(dropStack);
				}
			}
		} else {
			entityliving.worldObj.playSoundAtEntity(entityliving, "ebonmod.fail", 1.0F, 1.0F);
			return true;
		}
		return true;
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.rare;
	}
}
