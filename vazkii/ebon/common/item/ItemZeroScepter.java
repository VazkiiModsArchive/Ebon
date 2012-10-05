package vazkii.ebon.common.item;

import java.util.Random;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import net.minecraft.src.WorldClient;
import vazkii.ebon.common.EbonModHelper;
import vazkii.ebon.common.EbonModHooks;
import vazkii.ebon.common.EbonModPacketHandler;
import vazkii.ebon.common.EbonModReference;
import vazkii.ebon.common.EbonModTickHandler;

public class ItemZeroScepter extends ItemEbonScpeter {

	public ItemZeroScepter(int par1) {
		super(par1);
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack par1ItemStack, EntityLiving par2EntityLiving) {
		if (par2EntityLiving.worldObj instanceof WorldClient) return true;

		EntityPlayer player = EbonModHooks.getInteractingPlayer();
		if (EbonModHelper.doesPlayerHaveLexicon(player) && !EbonModHelper.doesPlayerHaveME(player) && EbonModHelper.isDarknessEnough(player, EbonModReference.DARKNESS_MIN_SCEPTER) && !EbonModTickHandler.bombMappings.containsKey(player)) {
			EbonModTickHandler.bombMappings.put(player, new BombEntry(par2EntityLiving));
			par2EntityLiving.worldObj.playSoundEffect(par2EntityLiving.posX, par2EntityLiving.posY, par2EntityLiving.posZ, "ebonmod.spell", 1.0F, 1.0F);
			EbonModHelper.addMEToPlayer(player, EbonModReference.ME_SCEPTER);
			EbonModHelper.addShadeForPlayer(player, EbonModReference.SHADE_SCEPTER);
		} else par2EntityLiving.worldObj.playSoundEffect(par2EntityLiving.posX, par2EntityLiving.posY, par2EntityLiving.posZ, "ebonmod.fail", 1.0F, 1.0F);
		return true;
	}

	public static class BombEntry {

		int ticksElapsed = 0;
		int secondsElapsed = 0;
		World world;
		Entity entity;
		boolean done = false;

		public BombEntry(EntityLiving entity) {
			this.entity = entity;
			world = entity.worldObj;
		}

		public void onTick() {
			++ticksElapsed;
			if (!entity.isEntityAlive()) done = true;
			Random rand = new Random();
			int maxSeconds = EbonModReference.ZERO_SCEPTER_TIME;

			EbonModPacketHandler.sendParticlePacket("reddust", entity.posX + (rand.nextDouble() - 0.5D) * entity.width, entity.posY + rand.nextDouble() * entity.height - 0.25D, entity.posZ + (rand.nextDouble() - 0.5D) * entity.width, 0.0D, 0.0D, 0.0D, true);
			if (secondsElapsed >= maxSeconds - 1) EbonModPacketHandler.sendParticlePacket("flame", entity.posX + (rand.nextDouble() - 0.5D) * entity.width, entity.posY + rand.nextDouble() * entity.height - 0.25D, entity.posZ + (rand.nextDouble() - 0.5D) * entity.width, 0.0D, 0.0D, 0.0D, true);

			if (ticksElapsed % 20 == 0) {
				world.playSoundAtEntity(entity, "random.click", 1.0F, 1.0F);
				++secondsElapsed;
				if (secondsElapsed >= maxSeconds) {
					world.createExplosion(entity, entity.posX, entity.posY, entity.posZ, EbonModReference.ZERO_SCEPTER_POWER);
					done = true;
				}
			}
		}

		public boolean isDone() {
			return done;
		}

	}

}
