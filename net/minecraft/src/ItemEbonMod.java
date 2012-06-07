package net.minecraft.src;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.src.forge.*;

public class ItemEbonMod extends Item implements ITextureProvider
{
    private String description = "clean";

    public ItemEbonMod(int i)
    {
        super(i);
    }

    public ItemEbonMod(int i, String desc)
    {
        super(i);
        description = desc;
    }

    public void addInformation(ItemStack itemstack, List list)
    {
        if (description != "clean")
        {
            list.add(description);
        }
    }

    public String getTextureFile()
    {
        return "/vazkii/ebonmod/sprites.png";
    }
}
