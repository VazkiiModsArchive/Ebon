package vazkii.ebon.common;

import net.minecraft.src.World;
import vazkii.codebase.common.CommonUtils;

public class EntityDemonicSoul extends EntityTormentedSoul {

	public EntityDemonicSoul(World par1World) {
		super(par1World);
		health = 24000;
		moveSpeed = 1F;
		attackStrength = 18;
		experienceValue = 30;
	}

	@Override public int getMaxHealth() {
		return 24000;
	}

	@Override public void onLivingUpdate() {
		EbonModPacketHandler.sendParticlePacket("flame", posX + (rand.nextDouble() - 0.5D) * width, posY + rand.nextDouble() * height, posZ + (rand.nextDouble() - 0.5D) * width, 0.0D, 0.0D, 0.0D, true);
		super.onLivingUpdate();
	}

	@Override protected int getDropItemId() {
		return mod_Ebon.demonicSoul.shiftedIndex;
	}

	@Override protected void dropFewItems(boolean flag, int i) {
		dropItem(mod_Ebon.demonicSoul.shiftedIndex, CommonUtils.nextIntMinMax(1, 3));
	}

}
