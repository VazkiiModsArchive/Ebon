package vazkii.ebon.client;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.FontRenderer;
import net.minecraft.src.GuiContainerCreative;
import net.minecraft.src.GuiInventory;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.ItemStack;
import net.minecraft.src.RenderHelper;
import net.minecraft.src.RenderItem;
import net.minecraft.src.ScaledResolution;

import org.lwjgl.opengl.GL11;

import vazkii.codebase.client.ClientUtils;
import vazkii.codebase.common.CommonUtils;
import vazkii.ebon.common.EbonModHelper;
import vazkii.ebon.common.EbonModPacketHandler;
import vazkii.ebon.common.EbonModReference;
import vazkii.ebon.common.mod_Ebon;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class EbonModClientTickHandler implements ITickHandler {

	public static int lastDarknessPacket = 0;
	boolean hasMELastTick = false;

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		if (type.equals(EnumSet.of(TickType.CLIENT))) {
			lastDarknessPacket = lastDarknessPacket == 0 ? 0 : --lastDarknessPacket;
			return;
		}

		Minecraft mc = CommonUtils.getMc();
		GuiScreen currentScreen = mc.currentScreen;
		if (currentScreen != null && (currentScreen instanceof GuiInventory || currentScreen instanceof GuiContainerCreative)) {
			if (currentScreen instanceof GuiContainerCreative && ((GuiContainerCreative) currentScreen).func_74230_h() != CreativeTabs.tabInventory.getTabIndex()) return;

			if (EbonModHelper.clientDarkness < 0) EbonModPacketHandler.sendRequestDarknessPacket();

			if (EbonModHelper.clientDarkness > 0) {
				int offsetY = currentScreen instanceof GuiContainerCreative ? 18 : 0;
				FontRenderer font = mc.fontRenderer;
				ScaledResolution rez = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);

				int xCenter = rez.getScaledWidth() / 2;
				int yCenter = rez.getScaledHeight() / 2;
				int x = xCenter - EbonModReference.INVENTORY_DARKNESS_OFFSET + (ClientUtils.getClientPlayer().getActivePotionEffects().isEmpty() /*|| currentScreen instanceof GuiContainerCreative*/? 0 : 60);
				int y = yCenter - 18 - offsetY;

				RenderHelper.enableStandardItemLighting();
				RenderItem renderItem = new RenderItem();
				renderItem.renderItemIntoGUI(font, mc.renderEngine, new ItemStack(mod_Ebon.darknessIcon), x, y);
				if (EbonModHelper.clientLexicon > 0) {
					GL11.glPushMatrix();
					GL11.glScalef(0.5F, 0.5F, 0.5F);
					renderItem.renderItemIntoGUI(font, mc.renderEngine, new ItemStack(mod_Ebon.necromancerLexicon), (xCenter - 19 - (currentScreen instanceof GuiContainerCreative ? 26 : 0) + (ClientUtils.getClientPlayer().getActivePotionEffects().isEmpty() ? 0 : 60)) * 2, (yCenter - 13 - (currentScreen instanceof GuiContainerCreative ? 14 : 0)) * 2);
					GL11.glScalef(1.0F, 1.0F, 1.0F);
					GL11.glPopMatrix();
				}
				RenderHelper.disableStandardItemLighting();
				font.drawStringWithShadow("" + EbonModHelper.clientDarkness, x + 18, y + 8, 0x400b4a);
			}
		}
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.RENDER, TickType.CLIENT);
	}

	@Override
	public String getLabel() {
		return "Ebon Mod";
	}

}
