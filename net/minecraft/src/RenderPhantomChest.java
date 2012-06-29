package net.minecraft.src;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class RenderPhantomChest extends TileEntitySpecialRenderer
{
    private ModelChest chestModel = new ModelChest();

    public void renderTileEntityPhantomChestAt(TileEntityPhantomChest par1TileEntityChest, double par2, double par4, double par6, float par8)
    {
        bindTextureByName("/vazkii/ebonmod/phantomChest.png");
        GL11.glPushMatrix();
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glTranslatef((float)par2, (float)par4 + 1.0F, (float)par6 + 1.0F);
        GL11.glScalef(1.0F, -1.0F, -1.0F);
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        float var12 = par1TileEntityChest.prevLidAngle + (par1TileEntityChest.lidAngle - par1TileEntityChest.prevLidAngle) * par8;
        var12 = 1.0F - var12;
        var12 = 1.0F - var12 * var12 * var12;
        chestModel.chestLid.rotateAngleX = -(var12 * (float)Math.PI / 2.0F);
        chestModel.renderAll();
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    public void renderTileEntityAt(TileEntity var1, double var2, double var4,
            double var6, float var8)
    {
        renderTileEntityPhantomChestAt((TileEntityPhantomChest)var1, var2, var4, var6, var8);
    }
}
