package vazkii.ebon.common.block;

import java.util.ArrayList;
import java.util.Random;

import vazkii.codebase.common.CommonUtils;
import vazkii.ebon.common.EbonModPacketHandler;
import vazkii.ebon.common.EbonModReference;
import vazkii.ebon.common.mod_Ebon;

import net.minecraft.src.BlockCrops;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public class BlockBloodLeafCrops extends BlockCrops {

	public BlockBloodLeafCrops(int par1, int par2) {
		super(par1, par2);
		disableStats();
		setRequiresSelfNotify();
	}

	@Override
	public void fertilize(World world, int i, int j, int k) {
		world.createExplosion(null, i + 0.5F, j + 0.5F, k + 0.5F, 2, true);
	}

	public static void fertilizePropperly(World world, int i, int j, int k) {
		Random r = new Random();
		world.playSoundEffect(i + 0.5F, j + 0.5F, k + 0.5F, "mob.blaze.death", 1.0F + r.nextFloat(), r.nextFloat() * 0.7F + 0.3F);
		world.setBlockMetadataWithNotify(i, j, k, 7);
	}

	@Override
	protected boolean canThisPlantGrowOnThisBlockID(int i) {
		return i == mod_Ebon.quicksand.blockID;
	}

	@Override
	public void randomDisplayTick(World world, int i, int j, int k, Random random) {
		double d = i + random.nextDouble();
		double d1 = j + random.nextDouble() * 0.5F + 0.5F;
		double d2 = k + random.nextDouble();

		if (world.getBlockMetadata(i, j, k) == 7) EbonModPacketHandler.sendParticlePacket("portal", d, d1, d2, 0D, 0D, 0D, true);
	}

	private float getGrowthRate(World par1World, int par2, int par3, int par4) {
		float var5 = 1.0F;
		int var6 = par1World.getBlockId(par2, par3, par4 - 1);
		int var7 = par1World.getBlockId(par2, par3, par4 + 1);
		int var8 = par1World.getBlockId(par2 - 1, par3, par4);
		int var9 = par1World.getBlockId(par2 + 1, par3, par4);
		int var10 = par1World.getBlockId(par2 - 1, par3, par4 - 1);
		int var11 = par1World.getBlockId(par2 + 1, par3, par4 - 1);
		int var12 = par1World.getBlockId(par2 + 1, par3, par4 + 1);
		int var13 = par1World.getBlockId(par2 - 1, par3, par4 + 1);
		boolean var14 = var8 == blockID || var9 == blockID;
		boolean var15 = var6 == blockID || var7 == blockID;
		boolean var16 = var10 == blockID || var11 == blockID || var12 == blockID || var13 == blockID;

		for (int var17 = par2 - 1; var17 <= par2 + 1; ++var17)
			for (int var18 = par4 - 1; var18 <= par4 + 1; ++var18) {
				int var19 = par1World.getBlockId(var17, par3 - 1, var18);
				float var20 = 0.0F;

				if (var19 == mod_Ebon.quicksand.blockID) {
					var20 = 1.0F;

					if (par1World.getBlockMetadata(var17, par3 - 1, var18) > 0) var20 = 3.0F;
				}

				if (var17 != par2 || var18 != par4) var20 /= 4.0F;

				var5 += var20;
			}

		if (var16 || var14 && var15) var5 /= 2.0F;

		return var5;
	}

	@Override
	public void updateTick(World world, int i, int j, int k, Random random) {
		super.updateTick(world, i, j, k, random);

		if (world.getBlockLightValue(i, j, k) <= 5) {
			int l = world.getBlockMetadata(i, j, k);

			if (l < 7) {
				float f = getGrowthRate(world, i, j, k);

				if (random.nextInt((int) (25F / f) + 1) == 0) world.setBlockMetadataWithNotify(i, j, k, ++l);
			}
		}
	}

	@Override
	public ArrayList<ItemStack> getBlockDropped(World world, int i, int j, int k, int meta, int fortune) {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();

		if (meta == 7) ret.add(new ItemStack(mod_Ebon.bloodLeaf, fortune > 0 ? CommonUtils.nextIntMinMax(1, fortune) + 1 : 1));

		ret.add(new ItemStack(mod_Ebon.bloodSeeds));
		return ret;
	}

	@Override
	public int getBlockTextureFromSideAndMetadata(int i, int j) {
		return blockIndexInTexture + (j < 0 ? 7 : j);
	}

	@Override
	public int idDropped(int i, Random random, int j) {
		return i == 7 ? mod_Ebon.bloodLeaf.shiftedIndex : -1;
	}

	@Override
	public int quantityDropped(Random random) {
		return random.nextInt(3);
	}

	@Override
	public String getTextureFile() {
		return EbonModReference.SPRITESHEET_PATH;
	}

}
