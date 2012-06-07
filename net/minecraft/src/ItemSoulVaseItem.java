package net.minecraft.src;

import net.minecraft.src.forge.ITextureProvider;

public class ItemSoulVaseItem extends ItemReed implements ITextureProvider
{
    public ItemSoulVaseItem(int par1, Block par2Block)
    {
        super(par1, par2Block);
    }

    public String getTextureFile()
    {
        return "/vazkii/ebonmod/sprites.png";
    }
}
