package net.minecraft.src;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Set;

import net.minecraft.src.forge.IChunkLoadHandler;
import net.minecraft.src.forge.MinecraftForge;
import net.minecraft.src.forge.MinecraftForgeClient;

public class TileEntityPhantomChest extends TileEntity implements IInventory, IChunkLoadHandler
{
    private ItemStack[] chestContents = new ItemStack[36];
    public float lidAngle;
    public float prevLidAngle;
    private int ticksSinceSync;
    public boolean isChestOpen;
    private boolean locked;

    private int ticksUntilDespawn;
    private int chestRank;

    public boolean hasDoneBadThing = false;

    public void initializeEntity(int ticks, int rank)
    {
    	MinecraftForge.registerChunkLoadHandler(this);
        locked = true;
        ticksUntilDespawn = ticks;
        chestRank = rank;

        if (chestRank == 4)
        {
            return;
        }

        Random rand = new Random();

        for (int i = 0; i < 50; i++)
        {
            float f = (float)xCoord + rand.nextFloat();
            float f2 = (float)yCoord + rand.nextFloat() * 0.5F + 0.5F;
            float f3 = (float)zCoord + rand.nextFloat();
            worldObj.spawnParticle("explode", f, f2, f3, 0.0D, 0.0D, 0.0D);
        }

        placeLoot(rank);
    }

    public void initializeEntity(Random rand)
    {
        int rankRand = rand.nextInt(100);
        int rank;
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        
        
        if (rankRand > 89 || (c.get(2)==3 && c.get(5)==1)) //Happy April Fools!
        {
            rank = 4;
        }
        else if (rankRand > 74)
        {
            rank = 3;
        }
        else if (rankRand > 49)
        {
            rank = 2;
        }
        else
        {
            rank = 1;
        }

        initializeEntity((rand.nextInt(15)+1) * 600, rank);
    }

    private boolean isSlotOccupied(int slot)
    {
        return slot > chestContents.length || getStackInSlot(slot) != null;
    }

    public boolean hasItemStack(ItemStack par1ItemStack)
    {
        int var2;

        for (var2 = 0; var2 < chestContents.length; ++var2)
        {
            if (chestContents[var2] != null && chestContents[var2].isStackEqual(par1ItemStack))
            {
                return true;
            }
        }

        return false;
    }

    private void placeLoot(int rank)
    {
        Random rand = new Random();
        int itemCount;
        int slotToPlace;
        EbonAPI_PhantomChestLoot loot;
        int lootListLength = EbonAPI.phantomLootList.size();
        int listIndexToPlace;

        do
        {
            itemCount = rand.nextInt(8);
        }
        while (itemCount < 4);

        int slotToPlaceGrindstone = rand.nextInt(chestContents.length);
        setInventorySlotContents(slotToPlaceGrindstone, new ItemStack(mod_Ebon.mortarPestle, 1));

        for (int i = 0; i < itemCount; i++)
        {
            int iterations = 0;

            do
            {
                slotToPlace = rand.nextInt(chestContents.length);
            }
            while (isSlotOccupied(slotToPlace));

            do
            {
                if (++iterations == 50)
                {
                    MinecraftForge.killMinecraft("Ebon Mod", "Chest Spawn: Too Many Iterations!");
                }

                do
                {
                    listIndexToPlace = rand.nextInt(lootListLength);
                }
                while (listIndexToPlace == 0);

                loot = EbonAPI.phantomLootList.get(listIndexToPlace);
            }
            while (loot.getRank() != chestRank || loot.createItem() == null);

            setInventorySlotContents(slotToPlace, loot.createItem());
        }

        if (!hasItemStack(new ItemStack(mod_Ebon.mortarPestle, 1))) //Fail Safe
            for (int i = 0; i < itemCount; i++)
                if (!isSlotOccupied(i))
                {
                    setInventorySlotContents(i, new ItemStack(mod_Ebon.mortarPestle, 1));
                }
    }

    public int getSizeInventory()
    {
        return chestContents.length;
    }

    public ItemStack getStackInSlot(int par1)
    {
        return this.chestContents[par1];
    }

    public ItemStack decrStackSize(int par1, int par2)
    {
        if (this.chestContents[par1] != null)
        {
            ItemStack var3;

            if (this.chestContents[par1].stackSize <= par2)
            {
                var3 = this.chestContents[par1];
                this.chestContents[par1] = null;
                this.onInventoryChanged();
                return var3;
            }
            else
            {
                var3 = this.chestContents[par1].splitStack(par2);

                if (this.chestContents[par1].stackSize == 0)
                {
                    this.chestContents[par1] = null;
                }

                this.onInventoryChanged();
                return var3;
            }
        }
        else
        {
            return null;
        }
    }

    public ItemStack getStackInSlotOnClosing(int par1)
    {
        if (this.chestContents[par1] != null)
        {
            ItemStack var2 = this.chestContents[par1];
            this.chestContents[par1] = null;
            return var2;
        }
        else
        {
            return null;
        }
    }

    public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
    {
        this.chestContents[par1] = par2ItemStack;

        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
        {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }

        this.onInventoryChanged();
    }

    public String getInvName()
    {
        return "Phantom Chest";
    }

    public int getInventoryStackLimit()
    {
        return 64;
    }

    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
    {
        return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

    public void openChest()
    {
        isChestOpen = true;
    }
    public void closeChest()
    {
        isChestOpen = false;
    }

    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList var2 = par1NBTTagCompound.getTagList("Items");
        this.chestContents = new ItemStack[this.getSizeInventory()];

        for (int var3 = 0; var3 < var2.tagCount(); ++var3)
        {
            NBTTagCompound var4 = (NBTTagCompound)var2.tagAt(var3);
            int var5 = var4.getByte("Slot") & 255;

            if (var5 >= 0 && var5 < this.chestContents.length)
            {
                this.chestContents[var5] = ItemStack.loadItemStackFromNBT(var4);
            }
        }

        chestRank = par1NBTTagCompound.getInteger("Rank");
        ticksUntilDespawn = par1NBTTagCompound.getInteger("ticksRemaining");
        locked = par1NBTTagCompound.getBoolean("locked");
    }

    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        NBTTagList var2 = new NBTTagList();

        for (int var3 = 0; var3 < this.chestContents.length; ++var3)
        {
            if (this.chestContents[var3] != null)
            {
                NBTTagCompound var4 = new NBTTagCompound();
                var4.setByte("Slot", (byte)var3);
                this.chestContents[var3].writeToNBT(var4);
                var2.appendTag(var4);
            }
        }

        par1NBTTagCompound.setInteger("Rank", chestRank);
        par1NBTTagCompound.setInteger("ticksRemaining", ticksUntilDespawn);
        par1NBTTagCompound.setBoolean("locked", locked);
        par1NBTTagCompound.setTag("Items", var2);
    }

    public void updateEntity()
    {
        super.updateEntity();

        if (locked)
            if (--ticksUntilDespawn <= 0)
            {
                worldObj.setBlockWithNotify(xCoord, yCoord, zCoord, 0);
                worldObj.removeBlockTileEntity(xCoord,  yCoord,  zCoord);
                worldObj.updateLightByType(EnumSkyBlock.Block, xCoord, yCoord, zCoord);
                Random rand = new Random();

                for (int i = 0; i < 50; i++)
                {
                    float f = (float) xCoord + rand.nextFloat();
                    float f2 = (float) yCoord + rand.nextFloat() * 0.5F + 0.5F;
                    float f3 = (float) zCoord + rand.nextFloat();
                    worldObj.spawnParticle("explode", f, f2, f3, 0.0D, 0.0D, 0.0D);
                }

                worldObj.playSoundEffect(xCoord,  yCoord, zCoord, "mob.endermen.portal", 1.0F, 1.0F);
            };

        this.prevLidAngle = this.lidAngle;

        float var1 = 0.1F;

        double var4;

        if (this.isChestOpen && this.lidAngle == 0.0F)
        {
            double var2 = (double)this.xCoord + 0.5D;
            var4 = (double)this.zCoord + 0.5D;
            this.worldObj.playSoundEffect(var2, (double)this.yCoord + 0.5D, var4, "vazkii.ebonmod.pcClose", 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
        }

        if (this.isChestOpen && this.lidAngle > 0.0F || this.isChestOpen && this.lidAngle < 1.0F)
        {
            float var8 = this.lidAngle;

            if (this.isChestOpen)
            {
                this.lidAngle += var1;
            }
            else
            {
                this.lidAngle -= var1;
            }

            if (this.lidAngle > 1.0F)
            {
                this.lidAngle = 1.0F;
            }

            float var3 = 0.5F;

            if (this.lidAngle < var3 && var8 >= var3)
            {
                var4 = (double)this.xCoord + 0.5D;
                double var6 = (double)this.zCoord + 0.5D;
                this.worldObj.playSoundEffect(var4, (double)this.yCoord + 0.5D, var6, "vazkii.ebonmod.pcClose", 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
            }

            if (this.lidAngle < 0.0F)
            {
                this.lidAngle = 0.0F;
            }
        }
    }

    public int getRank()
    {
        return chestRank;
    }

    public int getTicksUntilDespawn()
    {
        return ticksUntilDespawn;
    }

    public boolean isLocked()
    {
        return locked;
    }

    public void unlock()
    {
        locked = false;
        worldObj.playSoundEffect(xCoord, yCoord, zCoord, "vazkii.ebonmod.pcOpen", 1.0F, 1.0F);
    }

    public boolean getBadThing()
    {
        return hasDoneBadThing;
    }

    public void setDoneBadThing()
    {
        hasDoneBadThing = true;
    }

	public void addActiveChunks(World world, Set<ChunkCoordIntPair> chunkList) {	
	}

	public boolean canUnloadChunk(Chunk chunk) {
		Chunk pair = worldObj.getChunkFromBlockCoords(xCoord, yCoord);
		return chunk != pair;
	}

	public boolean canUpdateEntity(Entity entity) {
		return false;
	}
}
