package vazkii.ebon.common.item;

import java.util.List;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumRarity;
import net.minecraft.src.ItemStack;
import net.minecraft.src.TileEntityMobSpawner;
import net.minecraft.src.World;
import net.minecraft.src.WorldClient;
import vazkii.codebase.common.CommonUtils;
import vazkii.ebon.api.EbonAPIRegistry;
import vazkii.ebon.common.EbonModHelper;
import vazkii.ebon.common.EbonModReference;

public class ItemWandOfImprisionment extends ItemSpritesheet {

	public ItemWandOfImprisionment(int par1) {
		super(par1);
		setMaxStackSize(1);
		setFull3D();
	}

	@Override public boolean tryPlaceIntoWorld(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
		if (par3World instanceof WorldClient || EbonModHelper.doesPlayerHaveME(par2EntityPlayer) || !EbonModHelper.doesPlayerHaveLexicon(par2EntityPlayer) || !EbonModHelper.isDarknessEnough(par2EntityPlayer, EbonModReference.DARKNESS_MIN_WAND_IMPRISIONMENT)) return true;

		if (par3World.getBlockId(par4, par5, par6) == Block.mobSpawner.blockID) {
			TileEntityMobSpawner spawner = (TileEntityMobSpawner) par3World.getBlockTileEntity(par4, par5, par6);
			List<EntityLiving> entityList = par3World.getEntitiesWithinAABB(EntityLiving.class, AxisAlignedBB.getBoundingBox(par4, par5 + 1, par6, par4 + 1, par5 + 2, par6 + 1));

			if (entityList.size() == 1) {
				EntityLiving entity = entityList.get(0);
				if (entity instanceof EntityPlayer) {
					par3World.playSoundAtEntity(par2EntityPlayer, "ebonmod.fail", 1.0F, 1.0F);
					return true;
				}
				String name = CommonUtils.getEntityName(entity);
				if (spawner.getMobID() != name && !EbonAPIRegistry.blacklistedSpawners.contains(name)) {
					spawner.setMobID(name);
					entity.setDead();
					par3World.playSoundAtEntity(par2EntityPlayer, "ebonmod.retrieval", 1.0F, 1.0F);
					EbonModHelper.addShadeForPlayer(par2EntityPlayer, EbonModReference.SHADE_WAND_IMPRISIONMENT);
					EbonModHelper.addMEToPlayer(par2EntityPlayer, EbonModReference.ME_WAND_IMPRISIONMENT);
					--par1ItemStack.stackSize;
					return true;
				}
			}
			par3World.playSoundAtEntity(par2EntityPlayer, "ebonmod.fail", 1.0F, 1.0F);
		}
		par3World.playSoundAtEntity(par2EntityPlayer, "ebonmod.fail", 1.0F, 1.0F);
		return true;
	}

	@Override public boolean hasEffect(ItemStack stack) {
		return true;
	}

	@Override public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.rare;
	}

}
