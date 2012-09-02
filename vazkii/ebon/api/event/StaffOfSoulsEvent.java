package vazkii.ebon.api.event;

import net.minecraft.src.EntityLiving;
import net.minecraft.src.ItemStack;
import net.minecraftforge.event.Cancelable;

@Cancelable public class StaffOfSoulsEvent extends EbonModEvent {

	public ItemStack stack;
	public EntityLiving entity;

	public StaffOfSoulsEvent(ItemStack stack, EntityLiving entity) {
		this.stack = stack;
		this.entity = entity;
	}
}
