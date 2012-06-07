package net.minecraft.src;

import java.awt.image.BufferedImage;
import java.util.Random;

import org.lwjgl.opengl.GL11;

public class GuiPhantomChest extends GuiContainer
{
    private int inventoryRows = 0;
    private TileEntityPhantomChest inventory;

    public GuiPhantomChest(IInventory par1IInventory, IInventory par2IInventory)
    {
        super(new ContainerPhantomChest(par1IInventory, par2IInventory));
        inventory = (TileEntityPhantomChest)par2IInventory;
        this.allowUserInput = false;
        short var3 = 222;
        int var4 = var3 - 108;
        this.inventoryRows = par2IInventory.getSizeInventory() / 9;
        this.ySize = var4 + this.inventoryRows * 18;
    }

    protected void drawGuiContainerForegroundLayer()
    {
        this.fontRenderer.drawString("Phantom Chest", 8, 6, 0xFFFFFF);
        this.fontRenderer.drawString("Inventory", 8, this.ySize - 97 + 2, 0xFFFFFF);
    }

    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        int var4 = this.mc.renderEngine.getTexture("/vazkii/ebonmod/phantomChestGui.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(var4);
        int var5 = (this.width - this.xSize) / 2;
        int var6 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.inventoryRows * 18 + 17);
        this.drawTexturedModalRect(var5, var6 + this.inventoryRows * 18 + 17, 0, 126, this.xSize, 96);
        this.drawTexturedModalRect(var5 + 152, var6 + 3, 176, (inventory.getRank() - 1) * 14, 18, 14);
    }

    public void onGuiClosed()
    {
        super.onGuiClosed();
        TileEntityPhantomChest tile = inventory;
        //ModLoader.getMinecraftInstance().thePlayer.addChatMessage("" + tile.getTicksUntilDespawn());
        tile.worldObj.setBlockWithNotify(tile.xCoord, tile.yCoord, tile.zCoord, 0);
        tile.worldObj.removeBlockTileEntity(tile.xCoord, tile.yCoord, tile.zCoord);
        tile.worldObj.updateAllLightTypes(tile.xCoord, tile.yCoord, tile.zCoord);
        Random rand = new Random();

        for (int i = 0; i < 50; i++)
        {
            float f = (float)tile.xCoord + rand.nextFloat();
            float f2 = (float)tile.yCoord + rand.nextFloat() * 0.5F + 0.5F;
            float f3 = (float)tile.zCoord + rand.nextFloat();
            tile.worldObj.spawnParticle("explode", f, f2, f3, 0.0D, 0.0D, 0.0D);
        }

        tile.worldObj.playSoundEffect(tile.xCoord, tile.yCoord, tile.zCoord, "mob.endermen.portal", 1.0F, 1.0F);
    }
}
