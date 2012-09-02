package vazkii.ebon.common.block;

import java.util.Random;

import net.minecraft.src.BlockContainer;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Material;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import vazkii.ebon.common.EbonModPacketHandler;
import vazkii.ebon.common.EbonModReference;
import vazkii.ebon.common.mod_Ebon;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockEbonGlowstone extends BlockContainer {

	public BlockEbonGlowstone(int par1, int par2) {
		super(par1, par2, Material.glass);
		setTickRandomly(true);
		GameRegistry.registerBlock(this);
		setCreativeTab(CreativeTabs.tabBlock);
	}

	@Override public void randomDisplayTick(World world, int i, int j, int k, Random random) {
		if (isAltar(world, i, j, k)) for (int it = 0; it < EbonModReference.PARTICLE_COUNT / 4; it++) {
			double d = i + random.nextDouble();
			double d1 = j + random.nextDouble() * 0.5F + 0.5F;
			double d2 = k + random.nextDouble();
			EbonModPacketHandler.sendParticlePacket("portal", d, d1, d2, 0D, 0D, 0D, true);
		}
	}

	@Override public int quantityDropped(Random random) {
		return 2 + random.nextInt(3);
	}

	@Override public int idDropped(int par1, Random par2Random, int par3) {
		return mod_Ebon.ebonGlowdust.shiftedIndex;
	}

	@Override
	public String getTextureFile() {
		return EbonModReference.SPRITESHEET_PATH;
	}

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TileEntityWard(true);
	}

	public static boolean isAltar(World world, int x, int y, int z) {
		return BlockEbonObsidian.isAltar(world, x + 4, y - 1, z) || BlockEbonObsidian.isAltar(world, x - 4, y - 1, z) || BlockEbonObsidian.isAltar(world, x, y - 1, z - 4) || BlockEbonObsidian.isAltar(world, x, y - 1, z + 4);
	}

}
