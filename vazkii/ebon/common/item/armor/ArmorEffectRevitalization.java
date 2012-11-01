package vazkii.ebon.common.item.armor;

import java.util.EnumSet;

import vazkii.ebon.api.ArmorEffect;
import vazkii.ebon.api.ArmorType;
import vazkii.ebon.common.EbonModReference;
import vazkii.ebon.common.EbonModTickHandler;

import net.minecraft.src.EntityPlayer;

public class ArmorEffectRevitalization extends ArmorEffect {

	@Override
	public EnumSet<ArmorType> armorTypes() {
		return EnumSet.of(ArmorType.HELM);
	}

	@Override
	public void onTick(EnumSet<ArmorType> armorType, EntityPlayer player) {
		if (EbonModTickHandler.elapsedTicks % EbonModReference.ARMOR_REVITALIZAION_TIME == 0) player.getFoodStats().addStats(1, 0.2F);
	}

	@Override
	public String name() {
		return "Revitalization";
	}

}
