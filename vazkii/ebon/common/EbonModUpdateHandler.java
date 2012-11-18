package vazkii.ebon.common;

import vazkii.codebase.common.VazkiiUpdateHandler;

import net.minecraft.src.ItemStack;

import cpw.mods.fml.common.Mod;

public class EbonModUpdateHandler extends VazkiiUpdateHandler {

	public EbonModUpdateHandler(Mod m) {
		super(m);
	}

	@Override
	public String getChangelogURL() {
		return EbonModReference.CHANGELOG_URL;
	}

	@Override
	public ItemStack getIconStack() {
		return new ItemStack(mod_Ebon.ebonGem);
	}

	@Override
	public String getUpdateURL() {
		return EbonModReference.UPDATE_URL;
	}

	@Override
	public String getModName() {
		return "The Ebon Mod";
	}

	@Override
	public String getUMVersion() {
		return EbonModReference.VERSION;
	}

}
