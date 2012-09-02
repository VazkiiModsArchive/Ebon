package vazkii.ebon.common.block;

import java.util.List;
import java.util.Random;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.BlockTorch;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.DamageSource;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityXPOrb;
import net.minecraft.src.World;
import vazkii.codebase.common.CommonUtils;
import vazkii.ebon.client.ParticleHelper;
import vazkii.ebon.common.EbonModPacketHandler;
import vazkii.ebon.common.EbonModReference;
import vazkii.ebon.common.MarkedEntityHelper;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockEbonTorch extends BlockTorch {

	public BlockEbonTorch(int par1, int par2) {
		super(par1, par2);
		GameRegistry.registerBlock(this);
		setCreativeTab(CreativeTabs.tabBlock);
		setRequiresSelfNotify();
	}

	@Override public int tickRate() {
		return 20;
	}

	private boolean isIndirectlyPowered(World par1World, int par2, int par3, int par4, int par5) {
		return par5 != 0 && par1World.isBlockIndirectlyProvidingPowerTo(par2, par3 - 1, par4, 0) ? true : par5 != 1 && par1World.isBlockIndirectlyProvidingPowerTo(par2, par3 + 1, par4, 1) ? true : par5 != 2 && par1World.isBlockIndirectlyProvidingPowerTo(par2, par3, par4 - 1, 2) ? true : par5 != 3 && par1World.isBlockIndirectlyProvidingPowerTo(par2, par3, par4 + 1, 3) ? true : par5 != 5 && par1World.isBlockIndirectlyProvidingPowerTo(par2 + 1, par3, par4, 5) ? true : par5 != 4 && par1World.isBlockIndirectlyProvidingPowerTo(par2 - 1, par3, par4, 4) ? true : par1World.isBlockIndirectlyProvidingPowerTo(par2, par3, par4, 0) ? true : par1World.isBlockIndirectlyProvidingPowerTo(par2, par3 + 2, par4, 1) ? true : par1World.isBlockIndirectlyProvidingPowerTo(par2, par3 + 1, par4 - 1, 2) ? true : par1World.isBlockIndirectlyProvidingPowerTo(par2, par3 + 1, par4 + 1, 3) ? true : par1World.isBlockIndirectlyProvidingPowerTo(par2 - 1, par3 + 1, par4, 4) ? true : par1World.isBlockIndirectlyProvidingPowerTo(par2 + 1, par3 + 1, par4, 5);
	}

	@Override public void onBlockAdded(World par1World, int par2, int par3, int par4) {
		par1World.scheduleBlockUpdate(par2, par3, par4, blockID, tickRate());
	}

	@Override public void randomDisplayTick(World world, int i, int j, int k, Random random) {
		int meta = world.getBlockMetadata(i, j, k);
		double d = i + 0.5;
		double d1 = j + 0.7;
		double d2 = k + 0.5;
		double d3 = 0.2199999988079071D;
		double d4 = 0.27000001072883606D;

		switch (meta) {
		case 1: {
			world.spawnParticle("smoke", d - d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
			ParticleHelper.constructParticle("darkflame", world, d - d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D);

			break;
		}
		case 2: {
			world.spawnParticle("smoke", d + d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
			ParticleHelper.constructParticle("darkflame", world, d + d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D);

			break;
		}
		case 3: {
			world.spawnParticle("smoke", d, d1 + d3, d2 - d4, 0.0D, 0.0D, 0.0D);
			ParticleHelper.constructParticle("darkflame", world, d, d1 + d3, d2 - d4, 0.0D, 0.0D, 0.0D);

			break;
		}

		case 4: {
			world.spawnParticle("smoke", d, d1 + d3, d2 + d4, 0.0D, 0.0D, 0.0D);
			ParticleHelper.constructParticle("darkflame", world, d, d1 + d3, d2 + d4, 0.0D, 0.0D, 0.0D);

			break;
		}

		default: {
			world.spawnParticle("smoke", d, d1, d2, 0.0D, 0.0D, 0.0D);
			ParticleHelper.constructParticle("darkflame", world, d, d1, d2, 0.0D, 0.0D, 0.0D);

			break;
		}
		}
	}

	@Override public void updateTick(World world, int i, int j, int k, Random random) {
		int meta = world.getBlockMetadata(i, j, k);
		if (!isIndirectlyPowered(world, i, j, k, meta)) {
			List<EntityLiving> livingEntities = world.getEntitiesWithinAABB(net.minecraft.src.EntityLiving.class, AxisAlignedBB.getBoundingBox(i, j, k, i + 1.0D, j + 1.0D, k + 1.0D).expand(EbonModReference.EBON_TORCH_RANGE, EbonModReference.EBON_TORCH_RANGE, EbonModReference.EBON_TORCH_RANGE));
			for (EntityLiving entity : livingEntities)
				if (!(entity instanceof EntityPlayer) && random.nextInt(EbonModReference.EBON_TORCH_SPEED_MOD) == 0 && !isIndirectlyPowered(world, i, j, k, meta)) {
					if (entity.getHealth() == 1 && !MarkedEntityHelper.isMarked(entity)) {
						MarkedEntityHelper.markEntity(entity);
						world.spawnEntityInWorld(new EntityXPOrb(world, entity.posX, entity.posY, entity.posZ, CommonUtils.getEntityXPValue(entity) / 2));
					}
					entity.attackEntityFrom(DamageSource.magic, 1);
					EbonModPacketHandler.sendParticlePacket("portal", entity.posX + (random.nextDouble() - 0.5D) * entity.width, entity.posY + random.nextDouble() * entity.height - 0.25D, entity.posZ + (random.nextDouble() - 0.5D) * entity.width, (random.nextDouble() - 0.5D) * 2D, -random.nextDouble(), (random.nextDouble() - 0.5D) * 2D, true);
				}
		}
		world.scheduleBlockUpdate(i, j, k, blockID, tickRate());
	}

	@Override
	public String getTextureFile() {
		return EbonModReference.SPRITESHEET_PATH;
	}

}
