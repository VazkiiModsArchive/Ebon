package vazkii.ebon.client;

import java.awt.image.BufferedImage;
import java.io.IOException;

import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiScreen;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.TextureFXManager;

public class GUIAltarBlueprint extends GuiScreen {

	private BufferedImage background;
	private final String[] EXAMINE_MESSAGES = new String[] { "", "Examination of the Scroll reveals that the block in the middle isn't similar to the others.", "Further examination reveals that the floor is an ornament.", "Even further examination reveals that the middle pillar is somehow connecting to the other ones." };
	private int examineCount;

	@Override
	public void initGui() {
		try {
			background = TextureFXManager.instance().loadImageFromTexturePack(mc.renderEngine, "/vazkii/ebon/client/resources/scroll.png");
			mc.renderEngine.setupTexture(background, 1);
		} catch (IOException e) {
			e.printStackTrace();
		}

		controlList.clear();
		controlList.add(new GuiButton(1, width / 2 - 30, height / 2 - 150, 60, 20, "Examine"));
	}

	@Override
	protected void actionPerformed(GuiButton guibutton) {
		if (guibutton.id == 1 && examineCount < EXAMINE_MESSAGES.length - 1) examineCount++;
	}

	@Override
	public void drawScreen(int par1, int par2, float par3) {
		if (examineCount >= EXAMINE_MESSAGES.length - 1) controlList.clear();

		drawRect(-mc.fontRenderer.getStringWidth(EXAMINE_MESSAGES[examineCount]), mc.fontRenderer.getStringWidth(EXAMINE_MESSAGES[examineCount]), width / 2, width / 2 - 10, 0x000000);
		mc.fontRenderer.drawStringWithShadow(EXAMINE_MESSAGES[examineCount], width / 2 - mc.fontRenderer.getStringWidth(EXAMINE_MESSAGES[examineCount]) / 2, height - 42, 0xFFFFFF);
		int x = width / 2 - 128;
		int y = height / 2 - 128;

		int backgroundImage = mc.renderEngine.getTexture("/vazkii/ebon/client/resources/scroll.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(backgroundImage);
		drawTexturedModalRect(x, y, 0, 0, 256, 256);

		super.drawScreen(par1, par2, par3);
	}

}
