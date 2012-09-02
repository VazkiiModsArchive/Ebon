package vazkii.ebon.common.item.armor;

import java.util.EnumSet;

import net.minecraft.src.EntityPlayer;
import vazkii.ebon.api.ArmorEffect;
import vazkii.ebon.api.ArmorType;
import vazkii.ebon.common.EbonModReference;
import vazkii.ebon.common.EbonModTickHandler;

public class ArmorEffectRegeneration extends ArmorEffect {

	int ticksElapsed = 0;

	@Override
	public EnumSet<ArmorType> armorTypes() {
		return EnumSet.of(ArmorType.HELM);
	}

	@Override public void onTick(EnumSet<ArmorType> armorType, EntityPlayer player) {
		if (EbonModTickHandler.elapsedTicks % EbonModReference.ARMOR_REGEN_TIME == 0) player.heal(1);
	}

	@Override
	public String name() {
		return "Regeneration";
	}

}
