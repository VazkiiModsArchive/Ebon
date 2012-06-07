package net.minecraft.src;

import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import codechicken.nei.ItemMobSpawner;
import codechicken.nei.NEIUtils;

import net.minecraft.src.forge.IItemRenderer;
import net.minecraft.src.forge.ITextureProvider;
import net.minecraft.src.forge.IItemRenderer.ItemRenderType;
import net.minecraft.src.forge.IItemRenderer.ItemRendererHelper;

public class ItemMobSpawnerItem extends Item implements ITextureProvider, IItemRenderer
{
    public ItemMobSpawnerItem(int par1)
    {
        super(par1);
        setMaxDamage(0);
        setHasSubtypes(true);
    }

    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7)
    {
        int var8 = par3World.getBlockId(par4, par5, par6);

        if (var8 == Block.snow.blockID)
        {
            par7 = 1;
        }
        else if (var8 != Block.vine.blockID && var8 != Block.tallGrass.blockID && var8 != Block.deadBush.blockID)
        {
            if (par7 == 0)
            {
                --par5;
            }

            if (par7 == 1)
            {
                ++par5;
            }

            if (par7 == 2)
            {
                --par6;
            }

            if (par7 == 3)
            {
                ++par6;
            }

            if (par7 == 4)
            {
                --par4;
            }

            if (par7 == 5)
            {
                ++par4;
            }
        }

        if (par1ItemStack.stackSize == 0)
        {
            return false;
        }
        else if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6))
        {
            return false;
        }
        else if (par5 == 255 && Block.blocksList[Block.mobSpawner.blockID].blockMaterial.isSolid())
        {
            return false;
        }
        else if (par3World.canBlockBePlacedAt(Block.mobSpawner.blockID, par4, par5, par6, false, par7))
        {
            Block var9 = Block.blocksList[Block.mobSpawner.blockID];

            if (par3World.setBlockAndMetadataWithNotify(par4, par5, par6, Block.mobSpawner.blockID, this.getMetadata(par1ItemStack.getItemDamage())))
            {
                if (par3World.getBlockId(par4, par5, par6) == Block.mobSpawner.blockID)
                {
                    Block.blocksList[Block.mobSpawner.blockID].onBlockPlaced(par3World, par4, par5, par6, par7);
                    Block.blocksList[Block.mobSpawner.blockID].onBlockPlacedBy(par3World, par4, par5, par6, par2EntityPlayer);
                    setSpawnerMetadata(par3World, par4, par5, par6, par1ItemStack.getItemDamage());
                }

                par3World.playSoundEffect((double)((float)par4 + 0.5F), (double)((float)par5 + 0.5F), (double)((float)par6 + 0.5F), var9.stepSound.getStepSound(), (var9.stepSound.getVolume() + 1.0F) / 2.0F, var9.stepSound.getPitch() * 0.8F);
                --par1ItemStack.stackSize;
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    public void setSpawnerMetadata(World world, int x, int y, int z, int meta)
    {
        TileEntityMobSpawner entity = ((TileEntityMobSpawner)world.getBlockTileEntity(x, y, z));
        entity.setMobID(EntityList.getStringFromID(meta));
    }

    public void addInformation(ItemStack itemstack, List list)
    {
        list.add(EntityList.getStringFromID(itemstack.getItemDamage()));
    }

    public String getTextureFile()
    {
        return "/terrain.png";
    }

    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        return true;
    }

    public void renderInventoryItem(RenderBlocks render, ItemStack item)
    {
        int meta = item.getItemDamage();

        if (meta == 0)
        {
            meta = ItemMobSpawner.idPig;
        }

        World world = NEIUtils.getMinecraft().theWorld;
        ItemMobSpawner.loadSpawners(world);
        render.renderBlockAsItem(Block.mobSpawner, 0, 1F);
        GL11.glPushMatrix();
        Entity entity = ItemMobSpawner.getEntity(meta);

        if (entity != null)
        {
            entity.setWorld(world);
            float f1 = 0.4375F;

            if (entity.getShadowSize() > 1.5)
            {
                f1 = 0.1F;
            }

            GL11.glRotatef(world.getWorldTime() * 10, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-20F, 1.0F, 0.0F, 0.0F);
            GL11.glTranslatef(0.0F, -0.4F, 0.0F);
            GL11.glScalef(f1, f1, f1);
            entity.setLocationAndAngles(0, 0, 0, 0.0F, 0.0F);
            RenderManager.instance.renderEntityWithPosYaw(entity, 0.0D, 0.0D, 0.0D, 0.0F, 0);
        }

        GL11.glPopMatrix();
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        switch (type)
        {
            case EQUIPPED:
                GL11.glTranslatef(0.5F, 0.5F, 0.5F);

            case INVENTORY:
            case ENTITY:
                renderInventoryItem((RenderBlocks)data[0], item);
                break;
        }
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
    {
        return true;
    }
}
