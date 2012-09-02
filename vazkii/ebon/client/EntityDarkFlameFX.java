package vazkii.ebon.client;

import net.minecraft.src.EntityFlameFX;
import net.minecraft.src.World;
import vazkii.codebase.client.ColorRGB;

public class EntityDarkFlameFX extends EntityFlameFX {

	public EntityDarkFlameFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12) {
		super(par1World, par2, par4, par6, par8, par10, par12);
		ColorRGB color = new ColorRGB(23, 0, 55);
		particleRed = color.getRedF();
		particleGreen = color.getGreenF();
		particleBlue = color.getBlueF();
	}

}
