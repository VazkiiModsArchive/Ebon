package vazkii.ebon.common.block;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.BlockContainer;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Material;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import vazkii.ebon.common.EbonModPacketHandler;
import vazkii.ebon.common.EbonModReference;
import vazkii.ebon.common.mod_Ebon;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockEbonObsidian extends BlockContainer {

	public BlockEbonObsidian(int par1, int par2) {
		super(par1, par2, Material.rock);
		setTickRandomly(true);
		GameRegistry.registerBlock(this);
		setCreativeTab(CreativeTabs.tabBlock);
	}

	@Override
	public void randomDisplayTick(World world, int i, int j, int k, Random random) {
		if (isAltar(world, i, j, k)) for (int it = 0; it < EbonModReference.PARTICLE_COUNT / 8; it++) {
			double d = i + random.nextDouble();
			double d1 = j + random.nextDouble() * 0.5F + 1F;
			double d2 = k + random.nextDouble();
			EbonModPacketHandler.sendParticlePacket("darkflame", d, d1, d2, 0D, 0D, 0D, false);
		}
	}

	@Override
	public TileEntity createNewTileEntity(World par1World) {
		return new TileEntityWard(false);
	}

	@Override
	public String getTextureFile() {
		return EbonModReference.SPRITESHEET_PATH;
	}

	public static boolean isAltar(World world, int x, int y, int z) {
		return world.getBlockId(x, y, z) == mod_Ebon.ebonObsidian.blockID && world.getBlockId(x, y - 1, z) == Block.obsidian.blockID && world.getBlockId(x + 4, y + 1, z) == mod_Ebon.ebonGlowstone.blockID && world.getBlockId(x + 4, y, z) == Block.obsidian.blockID && world.getBlockId(x + 4, y - 1, z) == Block.obsidian.blockID && world.getBlockId(x - 4, y + 1, z) == mod_Ebon.ebonGlowstone.blockID && world.getBlockId(x - 4, y, z) == Block.obsidian.blockID && world.getBlockId(x - 4, y - 1, z) == Block.obsidian.blockID && world.getBlockId(x, y + 1, z + 4) == mod_Ebon.ebonGlowstone.blockID && world.getBlockId(x, y, z + 4) == Block.obsidian.blockID && world.getBlockId(x, y - 1, z + 4) == Block.obsidian.blockID && world.getBlockId(x, y + 1, z + 4) == mod_Ebon.ebonGlowstone.blockID && world.getBlockId(x, y, z + 4) == Block.obsidian.blockID && world.getBlockId(x, y - 1, z + 4) == Block.obsidian.blockID && world.getBlockId(x, y + 1, z) == 0 && world.getBlockId(x + 1, y + 1, z) == 0 && world.getBlockId(x + 2, y + 1, z) == 0 && world.getBlockId(x + 3, y + 1, z) == 0 && world.getBlockId(x - 1, y + 1, z) == 0 && world.getBlockId(x - 2, y + 1, z) == 0 && world.getBlockId(x - 3, y + 1, z) == 0 && world.getBlockId(x, y + 1, z + 1) == 0 && world.getBlockId(x, y + 1, z + 2) == 0 && world.getBlockId(x, y + 1, z + 3) == 0 && world.getBlockId(x, y + 1, z - 1) == 0 && world.getBlockId(x, y + 1, z - 2) == 0 && world.getBlockId(x, y + 1, z - 3) == 0;
	}

}
