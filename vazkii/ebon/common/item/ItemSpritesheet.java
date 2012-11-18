package vazkii.ebon.common.item;

import vazkii.ebon.common.EbonModReference;

import net.minecraft.src.Item;

public class ItemSpritesheet extends Item {

	public ItemSpritesheet(int par1) {
		super(par1);
	}

	@Override
	public String getTextureFile() {
		return EbonModReference.SPRITESHEET_PATH;
	}

}
