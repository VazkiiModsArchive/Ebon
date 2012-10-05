package vazkii.ebon.client;

import net.minecraft.src.Entity;
import net.minecraft.src.ModelBiped;
import net.minecraft.src.RenderBiped;

import org.lwjgl.opengl.GL11;

public class RenderTormentedSoul extends RenderBiped {

	public RenderTormentedSoul(ModelBiped par1ModelBiped) {
		super(par1ModelBiped, 0F);
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		super.doRender(par1Entity, par2, par4, par6, par8, par9);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
	}

}
