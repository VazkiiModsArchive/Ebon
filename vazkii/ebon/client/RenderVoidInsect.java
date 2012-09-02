package vazkii.ebon.client;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.ModelSilverfish;
import net.minecraft.src.RenderLiving;

import org.lwjgl.opengl.GL11;

public class RenderVoidInsect extends RenderLiving {

	public RenderVoidInsect() {
		super(new ModelSilverfish(), 3F);
	}

	@Override public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		super.doRender(par1Entity, par2, par4, par6, par8, par9);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
	}

	@Override protected float getDeathMaxRotation(EntityLiving par1EntityLiving) {
		return 180.0F;
	}

}
