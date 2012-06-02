package net.minecraft.src;

import java.awt.image.BufferedImage;
import org.lwjgl.opengl.GL11;

public class GuiEbonAltarBlueprint extends GuiScreen {

	private BufferedImage background;
	private String[] examineMessages = new String[]{
			"",
			"Examination of the Scroll reveals that the picture is in prespective.",
			"Further examination reveals that the floor is an ornament.",
			"Even further examination reveals that the middle pillar is somehow connecting to the other ones."
	};
	private int examineCount;

    public void initGui() {
    	 try{
             background = ModLoader.loadImage(mc.renderEngine, "/vazkii/ebonmod/altarBlueprint.png");
             mc.renderEngine.setupTexture(background, 1);
     }catch(Exception e){
             e.printStackTrace();
     }
    	 
    	 controlList.clear();
    	 controlList.add(new GuiButton(1, width / 2 - 30, height / 2 - 150, 60, 20, "Examine"));

    }
    
    protected void actionPerformed(GuiButton guibutton)
    {
            if(guibutton.id == 1 && examineCount < 3)
            	examineCount++;
    }
            
    public void drawScreen(int par1, int par2, float par3){
    	if(examineCount == 3) controlList.clear();
        drawRect(-mc.fontRenderer.getStringWidth(examineMessages[examineCount]), mc.fontRenderer.getStringWidth(examineMessages[examineCount]), width / 2, width / 2 - 10, 0x000000);
    	mc.fontRenderer.drawStringWithShadow(examineMessages[examineCount], width / 2 - mc.fontRenderer.getStringWidth(examineMessages[examineCount]) / 2, height - 42, 0xFFFFFF);
    	int x = width / 2 - 128;
        int y = height / 2 - 128;
        try{
                int backgroundImage = mc.renderEngine.getTexture("/vazkii/ebonmod/altarBlueprint.png");
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                mc.renderEngine.bindTexture(backgroundImage);
                drawTexturedModalRect(x, y, 0, 0, 256, 256);
        }finally{}
    	
    	super.drawScreen(par1, par2, par3);
    }
}
