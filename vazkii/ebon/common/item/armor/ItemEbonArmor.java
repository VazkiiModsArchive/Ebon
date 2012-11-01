package vazkii.ebon.common.item.armor;

import java.util.List;

import vazkii.codebase.common.ColorCode;
import vazkii.codebase.common.CommonUtils;
import vazkii.ebon.api.ArmorType;
import vazkii.ebon.common.EbonModReference;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumArmorMaterial;
import net.minecraft.src.ItemArmor;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;

import net.minecraftforge.common.IArmorTextureProvider;

public class ItemEbonArmor extends ItemArmor implements IArmorTextureProvider {

	private ArmorType type;

	public ItemEbonArmor(int par1, int par4) {
		super(par1, CommonUtils.<EnumArmorMaterial> getEnumConstant("EBON", EnumArmorMaterial.class), 0, par4);
		type = ArmorType.getFromIndex(par4);
	}

	@Override
	public String getTextureFile() {
		return EbonModReference.SPRITESHEET_PATH;
	}

	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		effects: {
		if (!par1ItemStack.hasTagCompound()) break effects;

		NBTTagCompound cmp = par1ItemStack.getTagCompound();
		par3List.add(cmp.hasKey("effect") ? ColorCode.GREY + "Soul of " + cmp.getString("effect") : "");
	}

	super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
	}

	public ArmorType getType() {
		return type;
	}

	@Override
	public String getArmorTextureFile(ItemStack itemstack) {
		return "/vazkii/ebon/client/resources/" + (type == ArmorType.LEGS ? "armor2.png" : "armor1.png");
	}

}
