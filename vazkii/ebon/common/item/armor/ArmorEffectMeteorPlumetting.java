package vazkii.ebon.common.item.armor;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Random;

import vazkii.ebon.api.ArmorEffect;
import vazkii.ebon.api.ArmorType;
import vazkii.ebon.common.EbonModPacketHandler;
import vazkii.ebon.common.EbonModReference;

import net.minecraft.src.Block;
import net.minecraft.src.EntityPlayer;

public strictfp class ArmorEffectMeteorPlumetting extends ArmorEffect {

	static HashMap<EntityPlayer, Float> playersToCrash = new HashMap();

	@Override
	public EnumSet<ArmorType> armorTypes() {
		return EnumSet.of(ArmorType.HELM);
	}

	@Override
	public void onTick(EnumSet<ArmorType> armorType, EntityPlayer player) {
		int x = (int) Math.round(player.posX);
		int y = (int) Math.round(player.posY);
		int z = (int) Math.round(player.posZ);
		int id = player.worldObj.getBlockId(x, y - 1, z);
		Random random = new Random();
		if (player.motionY <= EbonModReference.ARMOR_METEOR_MIN_MOTION && player.isSneaking() && (id == 0 || Block.blocksList[id].isAirBlock(player.worldObj, x, y - 1, z))) {
			playersToCrash.put(player, playersToCrash.containsKey(player) ? playersToCrash.get(player) + EbonModReference.ARMOR_METEOR_TICK_ADD : EbonModReference.ARMOR_METEOR_TICK_ADD);
			for (int i = 0; i < EbonModReference.PARTICLE_COUNT / 4; i++)
				EbonModPacketHandler.sendParticlePacket("flame", player.posX + (random.nextDouble() - 0.5D) * player.width, player.posY + random.nextDouble() * player.height - 0.5D, player.posZ + (random.nextDouble() - 0.5D) * player.width, (random.nextDouble() - 0.5D) * 2D, -random.nextDouble(), (random.nextDouble() - 0.5D) * 2D, true);
		}

		if (playersToCrash.containsKey(player)) {
			player.fallDistance = 0F;
			if (player.onGround) {
				float motion = playersToCrash.get(player);
				float potency = (motion >= 0 ? motion : -motion) * EbonModReference.ARMOR_METEOR_MOTION_MOD;
				player.worldObj.createExplosion(player, player.posX, player.posY, player.posZ, potency, true);
				playersToCrash.remove(player);
			}
		}

	}

	@Override
	public String name() {
		return "Meteor Plumetting";
	}

}
