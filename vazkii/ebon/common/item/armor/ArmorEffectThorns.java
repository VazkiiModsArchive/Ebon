package vazkii.ebon.common.item.armor;

import java.util.EnumSet;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import vazkii.ebon.api.ArmorEffect;
import vazkii.ebon.api.ArmorType;

public class ArmorEffectThorns extends ArmorEffect {

	@Override
	public EnumSet<ArmorType> armorTypes() {
		return EnumSet.of(ArmorType.HELM);
	}

	@Override
	public void onPlayerEvent(EnumSet<ArmorType> armorType, EntityPlayer player, LivingEvent event) {
		if (event instanceof LivingHurtEvent) {
			LivingHurtEvent hurtEvent = (LivingHurtEvent) event;
			Entity sourceEntity = hurtEvent.source.getEntity();
			if (sourceEntity == null) return;

			sourceEntity.attackEntityFrom(hurtEvent.source, (int) Math.floor(hurtEvent.ammount / 4));
		}

	}

	@Override
	public String name() {
		return "Thorns";
	}

}
