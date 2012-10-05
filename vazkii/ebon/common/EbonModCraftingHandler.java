package vazkii.ebon.common;

import java.util.List;
import java.util.Random;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.WorldClient;
import vazkii.ebon.api.ArmorEffect;
import vazkii.ebon.api.ArmorType;
import vazkii.ebon.api.EbonAPIRegistry;
import vazkii.ebon.common.item.armor.ItemEbonArmor;
import cpw.mods.fml.common.ICraftingHandler;

public class EbonModCraftingHandler implements ICraftingHandler {

	@Override
	public void onCrafting(EntityPlayer player, ItemStack item, IInventory craftMatrix) {
		if (player.worldObj instanceof WorldClient) return;

		if (item.getItem() instanceof ItemEbonArmor) {
			ItemEbonArmor armor = (ItemEbonArmor) item.getItem();
			ArmorType type = armor.getType();
			List<ArmorEffect> effectsForType = EbonAPIRegistry.armorEffects.get(type);
			if (effectsForType == null || effectsForType.size() <= 0) return;

			ArmorEffect effect = effectsForType.get(new Random().nextInt(effectsForType.size()));
			NBTTagCompound itemCmp = new NBTTagCompound();
			itemCmp.setString("effect", effect.name());
			item.setTagCompound(itemCmp);
		}
	}

	@Override
	public void onSmelting(EntityPlayer player, ItemStack item) {
	}

}
