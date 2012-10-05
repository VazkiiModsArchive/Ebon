package vazkii.ebon.common.block;

import java.util.Random;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.BlockSand;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityFallingSand;
import net.minecraft.src.EntityItem;
import net.minecraft.src.Material;
import net.minecraft.src.World;
import vazkii.ebon.common.EbonModReference;

public class BlockQuicksand extends BlockSpritesheet {

	public BlockQuicksand(int par1, int par2) {
		super(par1, par2, Material.sand);
	}

	@Override
	public void onBlockAdded(World par1World, int par2, int par3, int par4) {
		par1World.scheduleBlockUpdate(par2, par3, par4, blockID, tickRate());
	}

	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {
		par1World.scheduleBlockUpdate(par2, par3, par4, blockID, tickRate());
	}

	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		fallIfPossible(par1World, par2, par3, par4);
	}

	@Override
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity) {
		par5Entity.setInWeb();
		if (!(par5Entity instanceof EntityItem)) par5Entity.motionY -= EbonModReference.QUICKSAND_SPEED;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
		return null;
	}

	private void fallIfPossible(World par1World, int par2, int par3, int par4) {
		if (BlockSand.canFallBelow(par1World, par2, par3 - 1, par4) && par3 >= 0) {
			byte var5 = 32;

			if (!BlockSand.fallInstantly && par1World.checkChunksExist(par2 - var5, par3 - var5, par4 - var5, par2 + var5, par3 + var5, par4 + var5)) {
				EntityFallingSand var6 = new EntityFallingSand(par1World, par2 + 0.5F, par3 + 0.5F, par4 + 0.5F, blockID);
				par1World.spawnEntityInWorld(var6);
			} else {
				par1World.setBlockWithNotify(par2, par3, par4, 0);

				while (BlockSand.canFallBelow(par1World, par2, par3 - 1, par4) && par3 > 0)
					--par3;

				if (par3 > 0) par1World.setBlockWithNotify(par2, par3, par4, blockID);
			}
		}
	}

}
