package vazkii.ebon.common.block;

import java.util.Random;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.DamageSource;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityXPOrb;
import net.minecraft.src.Material;
import net.minecraft.src.World;
import vazkii.codebase.common.CommonUtils;
import vazkii.ebon.common.EbonModPacketHandler;
import vazkii.ebon.common.EbonModReference;
import vazkii.ebon.common.MarkedEntityHelper;

public class BlockEbonStone extends BlockSpritesheet {

	public BlockEbonStone(int par1, int par2) {
		super(par1, par2, Material.rock);
		setTickRandomly(true);
	}

	@Override
	public boolean isOpaqueCube() {
		return true;
	}

	@Override
	public void randomDisplayTick(World world, int i, int j, int k, Random random) {
		for (int l = 0; l < EbonModReference.PARTICLE_COUNT / 2; l++) {
			double d = i + random.nextDouble();
			double d1 = j + random.nextDouble() * 0.5 + 0.733333333;
			double d2 = k + random.nextDouble();
			EbonModPacketHandler.sendParticlePacket("townaura", d, d1, d2, 0.0D, 0.0D, 0.0D, true);
		}
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k) {
		return AxisAlignedBB.getBoundingBox(i + 0.0625F, j, k + 0.0625F, i + 1 - 0.0625F, j + 1 - 0.0625F, k + 1 - 0.0625F);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity) {
		if (entity instanceof EntityLiving) {
			if (((EntityLiving) entity).getHealth() == 1 && !MarkedEntityHelper.isMarked(entity)) {
				MarkedEntityHelper.markEntity(entity);
				world.spawnEntityInWorld(new EntityXPOrb(world, entity.posX, entity.posY, entity.posZ, CommonUtils.getEntityXPValue((EntityLiving) entity) / 2));
			}

			entity.attackEntityFrom(DamageSource.magic, 1);
		}
	}

}
