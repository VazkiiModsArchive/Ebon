package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class RenderSpecter extends RenderBiped {
	
	public RenderSpecter(ModelBiped par1ModelBiped) {
		super(par1ModelBiped, 0.0F);
	}
	
	public void renderSpecter(EntitySpecter specter, double par2, double par4, double par6, float par8, float par9){
		if(!specter.isVisible()) return;
    	GL11.glPushMatrix();
    	GL11.glEnable(GL11.GL_BLEND);
    	super.doRenderLiving(specter, par2, par4, par6, par8, par9);
    	GL11.glDisable(GL11.GL_BLEND);
    	GL11.glPopMatrix();
	}
	
    public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
    	renderSpecter((EntitySpecter)par1EntityLiving, par2, par4, par6, par8, par9);
    }

}
