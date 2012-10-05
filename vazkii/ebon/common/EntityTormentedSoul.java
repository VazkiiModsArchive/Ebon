package vazkii.ebon.common;

import net.minecraft.src.DamageSource;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityMob;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumCreatureAttribute;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import net.minecraft.src.WorldClient;
import vazkii.codebase.common.CommonUtils;
import vazkii.ebon.common.item.ItemEbonScpeter;

public class EntityTormentedSoul extends EntityMob {

	public EntityTormentedSoul(World par1World) {
		super(par1World);
		texture = "/vazkii/ebon/client/resources/tormentedSoul.png";
		moveSpeed = CommonUtils.nextIntMinMax(2, 4) * 0.1F;
		attackStrength = 14;
		health = 20000;
		isImmuneToFire = true;
		experienceValue = 20;
	}

	@Override
	public int getMaxHealth() {
		return 2000;
	}

	@Override
	protected String getHurtSound() {
		return null;
	}

	@Override
	public void onLivingUpdate() {
		if (worldObj instanceof WorldClient) return;

		EbonModPacketHandler.sendParticlePacket("townaura", posX + (rand.nextDouble() - 0.5D) * width, posY + rand.nextDouble() + 0.5D * height, posZ + (rand.nextDouble() - 0.5D) * width, 0.0D, 0.0D, 0.0D, true);
		super.onLivingUpdate();
	}

	@Override
	protected String getDeathSound() {
		return "ebonmod.tsDeath";
	}

	@Override
	protected Entity findPlayerToAttack() {
		EntityPlayer player = worldObj.getClosestVulnerablePlayerToEntity(this, 16D);
		ItemStack stack = player == null ? null : player.getCurrentEquippedItem();
		return player == null || player.isSneaking() || stack != null && stack.getItem() instanceof ItemEbonScpeter ? null : player;
	}

	@Override
	public boolean attackEntityFrom(DamageSource damagesource, int i) {
		Entity entity = damagesource.getEntity();

		if (super.attackEntityFrom(damagesource, i) && entity instanceof EntityPlayer) {
			EntityPlayer ep = (EntityPlayer) entity;
			ItemStack itemstack = ep.inventory.getCurrentItem();

			if (itemstack == null || itemstack.itemID != mod_Ebon.ebonBroadsword.shiftedIndex) CommonUtils.sendChatMessage(ep, "This weapon doesn't seem to be very effective, maybe I should attack it with something else...");

			return true;
		}
		return false;
	}

	@Override
	protected int getDropItemId() {
		return mod_Ebon.soul.shiftedIndex;
	}

	@Override
	protected void dropFewItems(boolean flag, int i) {
		dropItem(mod_Ebon.soul.shiftedIndex, CommonUtils.nextIntMinMax(1, 3));
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEAD;
	}

}
