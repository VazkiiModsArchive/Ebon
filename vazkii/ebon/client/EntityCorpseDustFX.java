package vazkii.ebon.client;

import net.minecraft.src.EntityPortalFX;
import net.minecraft.src.World;

public class EntityCorpseDustFX extends EntityPortalFX {

	public EntityCorpseDustFX(World par1World, double par2, double par4, double par6, double par8, double par9, double par10) {
		super(par1World, par2, par4, par6, par8, par9, par10);
		particleRed = 0.99F;
		particleGreen = 0.99F;
		particleBlue = 0.99F;
		motionX *= 6;
		motionY *= 6;
		motionZ *= 6;
	}

}
