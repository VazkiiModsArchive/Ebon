package vazkii.ebon.api.event;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import net.minecraftforge.event.Cancelable;

@Cancelable public class EbonStaffEvent extends EbonModEvent {

	public World worldObj;
	public EntityPlayer player;
	public ItemStack stack;
	public int posX;
	public int posY;
	public int posZ;

	public EbonStaffEvent(World world, EntityPlayer player, ItemStack stack, int x, int y, int z) {
		worldObj = world;
		this.player = player;
		this.stack = stack;
		posX = x;
		posY = y;
		posZ = z;
	}

}
