package net.minecraft.src;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.src.forge.ITextureProvider;

public class ItemPLDisc extends ItemEbonMod {

	public ItemPLDisc(int i) {
		super(i);
		setMaxStackSize(1);
	}
	
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7)
    {
        if (par3World.getBlockId(par4, par5, par6) == Block.jukebox.blockID && par3World.getBlockMetadata(par4, par5, par6) == 0)
        {
        		TileEntityRecordPlayer j = (TileEntityRecordPlayer)par3World.getBlockTileEntity(par4, par5, par6);
        		Minecraft mc = ModLoader.getMinecraftInstance();
        		mc.ingameGUI.setRecordPlayingMessage("Theme of the Devil's Flute");
        		mc.sndManager.playStreaming("specterDisc", (float)j.xCoord, (float)j.yCoord, (float)j.zCoord, 1.0F, 1.0F);
        		j.record = mod_Ebon.plDisc.shiftedIndex;
        		mc.theWorld.setBlockMetadataWithNotify(j.xCoord, j.yCoord, j.zCoord, 1);
        		j.onInventoryChanged();
                par3World.playAuxSFXAtEntity((EntityPlayer)null, 1005, par4, par5, par6, this.shiftedIndex);
                --par1ItemStack.stackSize;
                return true;
        }
        else
        {
            return false;
        }
    }

    public void addInformation(ItemStack par1ItemStack, List par2List)
    {
        par2List.add("Theme of the Devil's Flute");
    }
    
	public boolean hasEffect(ItemStack stack){
		return true;
	}
	
    public String getTextureFile()
    {
        return "/vazkii/ebonmod/sprites.png";
    }

}
