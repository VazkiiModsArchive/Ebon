package vazkii.ebon.common.block;

import vazkii.ebon.common.EbonModReference;

import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Material;

import cpw.mods.fml.common.registry.GameRegistry;

public class BlockSpritesheet extends Block {

	public BlockSpritesheet(int par1, int par2, Material par3Material) {
		super(par1, par2, par3Material);
		GameRegistry.registerBlock(this);
		setCreativeTab(CreativeTabs.tabBlock);
	}

	@Override
	public String getTextureFile() {
		return EbonModReference.SPRITESHEET_PATH;
	}
}
