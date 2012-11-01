package vazkii.ebon.common.item.armor;

import java.util.EnumSet;
import java.util.List;
import java.util.Random;

import vazkii.codebase.common.CommonUtils;
import vazkii.ebon.api.ArmorEffect;
import vazkii.ebon.api.ArmorType;
import vazkii.ebon.common.EbonModReference;
import vazkii.ebon.common.EbonModTickHandler;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IMob;
import net.minecraft.src.Potion;
import net.minecraft.src.PotionEffect;

public class ArmorEffectDebilitation extends ArmorEffect {

	int[] possiblePotionEffects = new int[] { Potion.moveSlowdown.id, Potion.poison.id, Potion.weakness.id };

	@Override
	public EnumSet<ArmorType> armorTypes() {
		return EnumSet.of(ArmorType.CHEST);
	}

	@Override
	public void onTick(EnumSet<ArmorType> armorType, EntityPlayer player) {
		if (EbonModTickHandler.elapsedTicks % EbonModReference.ARMOR_DEBILITATION_TIME == 0) {
			List<EntityLiving> entityLivingList = player.worldObj.getEntitiesWithinAABB(EntityLiving.class, AxisAlignedBB.getBoundingBox(player.posX - EbonModReference.ARMOR_DEBIITATION_RANGE, player.posY - EbonModReference.ARMOR_DEBIITATION_RANGE, player.posZ - EbonModReference.ARMOR_DEBIITATION_RANGE, player.posX + EbonModReference.ARMOR_DEBIITATION_RANGE, player.posY + EbonModReference.ARMOR_DEBIITATION_RANGE, player.posZ + EbonModReference.ARMOR_DEBIITATION_RANGE));
			for (EntityLiving entity : entityLivingList) {
				if (!(entity instanceof IMob || entity instanceof EntityPlayer && CommonUtils.getServer().isPVPEnabled()) || entity == player || !entity.getActivePotionEffects().isEmpty()) continue;

				entity.addPotionEffect(new PotionEffect(possiblePotionEffects[new Random().nextInt(possiblePotionEffects.length)], EbonModReference.ARMOR_DEBILITATION_POTION_TIME, 0));
			}
		}
	}

	@Override
	public String name() {
		return "Debilitation";
	}

}
