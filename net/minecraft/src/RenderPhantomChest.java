package net.minecraft.src;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class RenderPhantomChest extends TileEntitySpecialRenderer
{
    private ModelChest chestModel = new ModelChest();

    public void renderTileEntityPhantomChestAt(TileEntityPhantomChest par1TileEntityChest, double par2, double par4, double par6, float par8)
    {
        int var9;

        if (par1TileEntityChest.worldObj == null)
        {
            var9 = 0;
        }
        else
        {
            Block var10 = par1TileEntityChest.getBlockType();
            var9 = par1TileEntityChest.getBlockMetadata();

            if (var10 != null && var9 == 0)
            {
                //((BlockChest)var10).unifyAdjacentChests(par1TileEntityChest.worldObj, par1TileEntityChest.xCoord, par1TileEntityChest.yCoord, par1TileEntityChest.zCoord);
                var9 = par1TileEntityChest.getBlockMetadata();
            }
        }

        this.bindTextureByName("/vazkii/ebonmod/phantomChest.png");
        GL11.glPushMatrix();
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glTranslatef((float)par2, (float)par4 + 1.0F, (float)par6 + 1.0F);
        GL11.glScalef(1.0F, -1.0F, -1.0F);
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        short var11 = 0;

        if (var9 == 2)
        {
            var11 = 180;
        }

        if (var9 == 3)
        {
            var11 = 0;
        }

        if (var9 == 4)
        {
            var11 = 90;
        }

        if (var9 == 5)
        {
            var11 = -90;
        }

        GL11.glRotatef((float)var11, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        float var12 = par1TileEntityChest.prevLidAngle + (par1TileEntityChest.lidAngle - par1TileEntityChest.prevLidAngle) * par8;
        var12 = 1.0F - var12;
        var12 = 1.0F - var12 * var12 * var12;
        chestModel.chestLid.rotateAngleX = -(var12 * (float)Math.PI / 2.0F);
        chestModel.renderAll();
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    public void renderTileEntityAt(TileEntity var1, double var2, double var4,
            double var6, float var8)
    {
        renderTileEntityPhantomChestAt((TileEntityPhantomChest)var1, var2, var4, var6, var8);
    }
}
