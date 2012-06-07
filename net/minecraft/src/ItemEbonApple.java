package net.minecraft.src;
import net.minecraft.client.Minecraft;
import net.minecraft.src.forge.ITextureProvider;

public class ItemEbonApple extends ItemAppleGold implements ITextureProvider
{
    public ItemEbonApple(int i, int j, float f, boolean flag)
    {
        super(i, j, f, flag);
    }

    public String getTextureFile()
    {
        return "/vazkii/ebonmod/sprites.png";
    }

    public ItemStack onFoodEaten(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        entityplayer.heal(20);
        return super.onFoodEaten(itemstack, world, entityplayer);
    }
}
