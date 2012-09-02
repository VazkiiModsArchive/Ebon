package vazkii.ebon.common.item;

import net.minecraft.src.Item;
import vazkii.ebon.common.EbonModReference;

public class ItemSpritesheet extends Item {

	public ItemSpritesheet(int par1) {
		super(par1);
	}

	@Override
	public String getTextureFile() {
		return EbonModReference.SPRITESHEET_PATH;
	}

}
