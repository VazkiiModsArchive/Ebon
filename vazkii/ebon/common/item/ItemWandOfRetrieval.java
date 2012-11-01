package vazkii.ebon.common.item;

import java.util.List;

import vazkii.codebase.common.ColorCode;
import vazkii.codebase.common.CommonUtils;
import vazkii.ebon.api.EbonAPIRegistry;
import vazkii.ebon.common.EbonModHelper;
import vazkii.ebon.common.EbonModHooks;
import vazkii.ebon.common.EbonModReference;
import vazkii.ebon.common.mod_Ebon;

import net.minecraft.src.Block;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityList;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumRarity;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.TileEntityMobSpawner;
import net.minecraft.src.World;
import net.minecraft.src.WorldClient;

import cpw.mods.fml.relauncher.ReflectionHelper;

public class ItemWandOfRetrieval extends ItemSpritesheet {

	public ItemWandOfRetrieval(int par1) {
		super(par1);
		setMaxStackSize(1);
		setFull3D();
	}

	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
		if (par3World instanceof WorldClient || EbonModHelper.doesPlayerHaveME(par2EntityPlayer) || !EbonModHelper.doesPlayerHaveLexicon(par2EntityPlayer) || !EbonModHelper.isDarknessEnough(par2EntityPlayer, EbonModReference.DARKNESS_MIN_WAND_RETRIEVAL)) return true;

		if (par1ItemStack.hasTagCompound()) {
			int var11 = par3World.getBlockId(par4, par5, par6);

			if (var11 == Block.snow.blockID) par7 = 1;
			else if (var11 != Block.vine.blockID && var11 != Block.tallGrass.blockID && var11 != Block.deadBush.blockID) {
				if (par7 == 0) --par5;

				if (par7 == 1) ++par5;

				if (par7 == 2) --par6;

				if (par7 == 3) ++par6;

				if (par7 == 4) --par4;

				if (par7 == 5) ++par4;
			}

			if (!par3World.canMineBlock(par2EntityPlayer, par4, par5, par6)) return false;

			else {
				if (par3World.canPlaceEntityOnSide(Block.mobSpawner.blockID, par4, par5, par6, false, par7, (Entity) null)) if (par3World.setBlockWithNotify(par4, par5, par6, Block.mobSpawner.blockID)) {
					if (par3World.getBlockId(par4, par5, par6) == Block.mobSpawner.blockID) {
						TileEntityMobSpawner spawner = (TileEntityMobSpawner) par3World.getBlockTileEntity(par4, par5, par6);
						NBTTagCompound cmp = par1ItemStack.getTagCompound();
						spawner.setMobID(cmp.getString("entityName"));
						System.out.println("Placing");
						if (cmp.hasKey("hasExtraInfo") && cmp.getBoolean("hasExtraInfo")) ReflectionHelper.setPrivateValue(TileEntityMobSpawner.class, spawner, cmp.getCompoundTag("extraInfo"), 2);
						Block.mobSpawner.updateBlockMetadata(par3World, par4, par5, par6, par7, par8, par9, par10);
						Block.mobSpawner.onBlockPlacedBy(par3World, par4, par5, par6, par2EntityPlayer);
					}

					par3World.playSoundAtEntity(par2EntityPlayer, "ebonmod.retrieval", 1.0F, 1.0F);
					par2EntityPlayer.renderBrokenItemStack(par1ItemStack);
					EbonModHelper.addShadeForPlayer(par2EntityPlayer, EbonModReference.SHADE_STAFF_SOULS);
					EbonModHelper.addMEToPlayer(par2EntityPlayer, EbonModReference.ME_WAND_RETRIEVAL);
					--par1ItemStack.stackSize;
				}
				return true;
			}
		}

		String entityName;
		Entity entity;
		NBTTagCompound compound;
		int id = par3World.getBlockId(par4, par5, par6);
		if (id == Block.mobSpawner.blockID) {
			TileEntityMobSpawner spawner = (TileEntityMobSpawner) par3World.getBlockTileEntity(par4, par5, par6);
			entityName = spawner.getMobID();
			entity = EntityList.createEntityByName(entityName, par3World);
			EntityList.getEntityID(entity);
			compound = ReflectionHelper.<NBTTagCompound, TileEntityMobSpawner> getPrivateValue(TileEntityMobSpawner.class, spawner, 2);

			if (EbonAPIRegistry.blacklistedSpawners.contains(entityName) || EbonModHelper.doesPlayerHaveME(par2EntityPlayer)) {
				par3World.playSoundAtEntity(par2EntityPlayer, "ebonmod.fail", 1.0F, 1.0F);
				return true;
			}

			par3World.setBlockWithNotify(par4, par5, par6, 0);
			par3World.playSoundAtEntity(par2EntityPlayer, "ebonmod.retrieval", 1.0F, 1.0F);
			EbonModHelper.addShadeForPlayer(par2EntityPlayer, EbonModReference.SHADE_WAND_RETRIEVAL);
			EbonModHelper.addMEToPlayer(par2EntityPlayer, EbonModReference.ME_WAND_RETRIEVAL);
			setSpawner(par1ItemStack, entityName, compound);
		}

		return true;
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack par1ItemStack, EntityLiving par2EntityLiving) {
		EntityPlayer player = EbonModHooks.getInteractingPlayer();
		if (par2EntityLiving.worldObj instanceof WorldClient || EbonModHelper.doesPlayerHaveME(player) || !EbonModHelper.doesPlayerHaveLexicon(player) || !EbonModHelper.isDarknessEnough(player, EbonModReference.DARKNESS_MIN_WAND_RETRIEVAL)) return true;

		if (EbonAPIRegistry.blacklistedSpawners.contains(CommonUtils.getEntityName(par2EntityLiving))) {
			par2EntityLiving.worldObj.playSoundAtEntity(player, "ebonmod.fail", 1.0F, 1.0F);
			return true;
		}

		--par1ItemStack.stackSize;
		player.inventory.addItemStackToInventory(new ItemStack(mod_Ebon.gemOfDespair));
		par2EntityLiving.worldObj.playSoundAtEntity(player, "ebonmod.retrieval", 1.0F, 1.0F);
		EbonModHelper.addShadeForPlayer(player, EbonModReference.SHADE_WAND_RETRIEVAL);
		EbonModHelper.addMEToPlayer(player, EbonModReference.ME_WAND_RETRIEVAL);
		par2EntityLiving.setDead();

		return true;
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.rare;
	}

	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		if (!par1ItemStack.hasTagCompound()) return;

		NBTTagCompound cmp = par1ItemStack.getTagCompound();
		par3List.add(cmp.hasKey("entityName") ? ColorCode.RED + cmp.getString("entityName") : "");
		if (cmp.hasKey("hasExtraInfo") && cmp.getBoolean("hasExtraInfo")) par3List.add(ColorCode.BRIGHT_GREEN + "Extra Info");

	}

	public void setSpawner(ItemStack stack, String name, NBTTagCompound extraInfo) {
		if (!stack.hasTagCompound()) stack.setTagCompound(new NBTTagCompound());

		NBTTagCompound cmp = stack.getTagCompound();
		cmp.setString("entityName", name);
		cmp.setBoolean("hasExtraInfo", extraInfo != null);

		if (extraInfo != null) cmp.setCompoundTag("extraInfo", extraInfo);
	}
}
