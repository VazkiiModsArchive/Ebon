package vazkii.ebon.common;

import java.util.Set;

import net.minecraft.src.DamageSource;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityMob;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumCreatureAttribute;
import net.minecraft.src.World;
import net.minecraft.src.WorldClient;
import vazkii.ebon.common.item.ItemVoidScepter;

public class EntityVoidInsect extends EntityMob {

	private EntityLiving target;
	private EntityPlayer summoner;

	int ticksElapsed = 0;

	public EntityVoidInsect(World par1World) {
		super(par1World);
		texture = "/vazkii/ebon/client/resources/voidInsect.png";
		setSize(0.3F, 0.7F);
		moveSpeed = 0.6F;
		attackStrength = 2;
		experienceValue = 0;
	}

	public void setTechincalTarget(EntityLiving entity) {
		target = entity;
	}

	@Override protected void attackEntity(Entity par1Entity, float par2) {
		if (attackTime <= 0 && par2 < 1.2F && par1Entity.boundingBox.maxY > boundingBox.minY && par1Entity.boundingBox.minY < boundingBox.maxY) {
			attackTime = 20;
			par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), attackStrength);
		}
	}

	public void setSummoner(EntityPlayer player) {
		summoner = player;
	}

	@Override public void onUpdate() {
		renderYawOffset = rotationYaw;
		super.onUpdate();
	}

	@Override public void updateEntityActionState() {
		super.updateEntityActionState();

		if (worldObj instanceof WorldClient) return;

		if (++ticksElapsed >= EbonModReference.VOID_INSECT_DURATION) {
			for (int i = 0; i < EbonModReference.PARTICLE_COUNT / 4; i++)
				EbonModPacketHandler.sendParticlePacket("explode", posX + (rand.nextDouble() - 0.5D) * width, posY + rand.nextDouble() + 0.5D * height, posZ + (rand.nextDouble() - 0.5D) * width, 0.0D, 0.0D, 0.0D, true);
			setDead();
			return;
		}

		if (target != null && target.isEntityAlive() && target != summoner) setTarget(target);

		if (getAttackTarget() == summoner) setTarget(null);
	}

	@Override public void setDead() {
		if (summoner != null) {
			Set<EntityVoidInsect> minionSet = ItemVoidScepter.minions.get(summoner);
			minionSet.remove(this);
			if (minionSet.isEmpty()) ItemVoidScepter.minions.remove(summoner);
		}
		super.setDead();
	}

	@Override
	public int getMaxHealth() {
		return 12;
	}

	@Override protected String getLivingSound() {
		return "mob.silverfish.say";
	}

	@Override protected String getHurtSound() {
		return "mob.silverfish.hit";
	}

	@Override protected String getDeathSound() {
		return "mob.silverfish.kill";
	}

	@Override protected int getDropItemId() {
		return 0;
	}

	@Override public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

}
