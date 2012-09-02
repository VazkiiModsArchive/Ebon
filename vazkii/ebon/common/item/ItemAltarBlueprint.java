package vazkii.ebon.common.item;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import net.minecraft.src.WorldClient;
import vazkii.ebon.client.GUIAltarBlueprint;
import cpw.mods.fml.client.FMLClientHandler;

public class ItemAltarBlueprint extends Item {

	public ItemAltarBlueprint(int par1) {
		super(par1);
		setMaxStackSize(1);
	}

	@Override public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		par2World.playSoundAtEntity(par3EntityPlayer, "ebonmod.tome", 1.0F, 1.0F);

		if (par2World instanceof WorldClient) FMLClientHandler.instance().displayGuiScreen(par3EntityPlayer, new GUIAltarBlueprint());

		return par1ItemStack;
	}

	@Override public boolean hasEffect(ItemStack stack) {
		return true;
	}

}
