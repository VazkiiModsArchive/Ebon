package vazkii.ebon.common.item;

import java.util.Random;

import vazkii.ebon.common.EbonModHelper;
import vazkii.ebon.common.EbonModHooks;
import vazkii.ebon.common.EbonModPacketHandler;
import vazkii.ebon.common.EbonModReference;
import vazkii.ebon.common.EntityTormentedSoul;
import vazkii.ebon.common.mod_Ebon;

import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.WorldClient;

public class ItemOrbOfSouls extends ItemSpritesheet {

	public ItemOrbOfSouls(int par1) {
		super(par1);
		setNoRepair();
		setMaxStackSize(1);
		setMaxDamage(EbonModReference.ORB_OF_SOULS_DAMAGE);
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack itemstack, EntityLiving entityliving) {
		if (entityliving.worldObj instanceof WorldClient) return true;

		EntityPlayer player = EbonModHooks.getInteractingPlayer();
		if (EbonModHelper.doesPlayerHaveME(player) || !EbonModHelper.isDarknessEnough(player, EbonModReference.DARKNESS_MIN_ORB) || entityliving.worldObj instanceof WorldClient || !(entityliving instanceof EntityTormentedSoul || entityliving.getHealth() >= entityliving.getMaxHealth())) {
			entityliving.worldObj.playSoundAtEntity(entityliving, "ebonmod.fail", 1.0F, 1.0F);
			return true;
		}

		if (!itemstack.isItemDamaged()) itemstack.damageItem(EbonModReference.ORB_OF_SOULS_DAMAGE, entityliving);

		Random random = new Random();

		for (int i = 0; i < EbonModReference.PARTICLE_COUNT * 2; i++)
			EbonModPacketHandler.sendParticlePacket("portal", entityliving.posX + (random.nextDouble() - 0.5D) * entityliving.width, entityliving.posY + random.nextDouble() * entityliving.height - 0.25D, entityliving.posZ + (random.nextDouble() - 0.5D) * entityliving.width, (random.nextDouble() - 0.5D) * 2D, -random.nextDouble(), (random.nextDouble() - 0.5D) * 2D, true);
		entityliving.worldObj.playSoundAtEntity(entityliving, "ebonmod.absorb", 1.0F, 1.0F);
		entityliving.setDead();
		itemstack.damageItem(-1, player);
		EbonModHelper.addShadeForPlayer(player, EbonModReference.SHADE_ORB);

		if (!itemstack.isItemDamaged()) itemstack.itemID = mod_Ebon.orbOfSoulsCharged.shiftedIndex;

		return true;
	}

}
