package vazkii.ebon.api;

import java.util.EnumSet;

import net.minecraft.src.EntityPlayer;

import net.minecraftforge.event.entity.living.LivingEvent;

public abstract class ArmorEffect {

	public abstract EnumSet<ArmorType> armorTypes();

	public void onTick(EnumSet<ArmorType> armorType, EntityPlayer player) {
	}

	public void onPlayerEvent(EnumSet<ArmorType> armorType, EntityPlayer player, LivingEvent event) {
	}

	public abstract String name();

	@Override
	public String toString() {
		return name() + " [" + super.toString() + "]";
	}

}
