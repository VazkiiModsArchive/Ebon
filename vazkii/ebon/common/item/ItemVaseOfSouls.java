package vazkii.ebon.common.item;

import net.minecraft.src.ItemReed;
import vazkii.ebon.common.EbonModReference;
import vazkii.ebon.common.mod_Ebon;

public class ItemVaseOfSouls extends ItemReed {

	public ItemVaseOfSouls(int par1) {
		super(par1, mod_Ebon.vaseOfSouls);
	}

	@Override
	public String getTextureFile() {
		return EbonModReference.SPRITESHEET_PATH;
	}

}
