package vazkii.ebon.common;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.TreeMap;

import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Material;
import net.minecraft.src.Potion;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import vazkii.ebon.common.block.BlockBloodLeafCrops;
import vazkii.ebon.common.block.BlockEbonGlowstone;
import vazkii.ebon.common.block.BlockEbonObsidian;
import vazkii.ebon.common.block.BlockEbonStone;
import vazkii.ebon.common.block.BlockEbonTorch;
import vazkii.ebon.common.block.BlockQuicksand;
import vazkii.ebon.common.block.BlockSpritesheet;
import vazkii.ebon.common.block.BlockVaseOfSouls;
import vazkii.ebon.common.item.ItemAltarBlueprint;
import vazkii.ebon.common.item.ItemBloodSeeds;
import vazkii.ebon.common.item.ItemBottleOfDarkness;
import vazkii.ebon.common.item.ItemCorpseDust;
import vazkii.ebon.common.item.ItemDarknessImbiber;
import vazkii.ebon.common.item.ItemEbonApple;
import vazkii.ebon.common.item.ItemEbonBroadsword;
import vazkii.ebon.common.item.ItemEbonHatchet;
import vazkii.ebon.common.item.ItemEbonPickaxe;
import vazkii.ebon.common.item.ItemEbonScpeter;
import vazkii.ebon.common.item.ItemEbonSpade;
import vazkii.ebon.common.item.ItemEbonStaff;
import vazkii.ebon.common.item.ItemInfinityScepter;
import vazkii.ebon.common.item.ItemMiniumCharm;
import vazkii.ebon.common.item.ItemOrbOfSouls;
import vazkii.ebon.common.item.ItemOrbOfSoulsCharged;
import vazkii.ebon.common.item.ItemPlusiumCharm;
import vazkii.ebon.common.item.ItemPotionComponent;
import vazkii.ebon.common.item.ItemSoulGrindstone;
import vazkii.ebon.common.item.ItemSoulPowder;
import vazkii.ebon.common.item.ItemSoulStone;
import vazkii.ebon.common.item.ItemSpritesheet;
import vazkii.ebon.common.item.ItemStaffOfSouls;
import vazkii.ebon.common.item.ItemVaseOfSouls;
import vazkii.ebon.common.item.ItemVoidScepter;
import vazkii.ebon.common.item.ItemWandOfImprisionment;
import vazkii.ebon.common.item.ItemWandOfRetrieval;
import vazkii.ebon.common.item.ItemZeroScepter;
import vazkii.ebon.common.item.armor.ItemEbonArmor;

public class EbonModConfig extends Configuration {

	public static final String CATEGORY_OTHERIDS = "otherids";
	public static TreeMap otherIDsProps = new TreeMap();

	//Props Start ============================================================================
	public static boolean canReforgeArmor = true;

	//Props Start ============================================================================

	public EbonModConfig(File file) {
		super(file);
		categories.put(CATEGORY_OTHERIDS, otherIDsProps);

		load();

		Property propCanReforgeArmor = getOrCreateBooleanProperty("canReforgeArmor", CATEGORY_GENERAL, true);
		propCanReforgeArmor.comment = "Set to true to allow reforging of Ebon Armor.";
		canReforgeArmor = propCanReforgeArmor.getBoolean(true);

		mod_Ebon.ebonGlowstone = new BlockEbonGlowstone(getOrCreateIntProperty("ebonGlowstoneID", CATEGORY_BLOCK, 662).getInt(662), 0).setHardness(0.3F).setStepSound(Block.soundGlassFootstep).setLightValue(1.0F).setBlockName("ebonGlowstone");
		mod_Ebon.ebonGemBlock = new BlockSpritesheet(getOrCreateIntProperty("ebonGemBlockID", CATEGORY_BLOCK, 663).getInt(663), 1, Material.iron).setHardness(3.0F).setResistance(5.0F).setLightValue(0.8F).setStepSound(Block.soundStoneFootstep).setBlockName("ebonGemBlock");
		mod_Ebon.bloodGemBlock = new BlockSpritesheet(getOrCreateIntProperty("bloodGemBlockID", CATEGORY_BLOCK, 664).getInt(664), 2, Material.iron).setHardness(3.0F).setResistance(5.0F).setLightValue(0.8F).setStepSound(Block.soundStoneFootstep).setBlockName("bloodGemBlock");
		mod_Ebon.quicksand = new BlockQuicksand(getOrCreateIntProperty("quicksandID", CATEGORY_BLOCK, 665).getInt(665), 3).setHardness(1.2F).setStepSound(Block.soundSandFootstep).setBlockName("quicksand");
		mod_Ebon.ebonObsidian = new BlockEbonObsidian(getOrCreateIntProperty("ebonObsidianID", CATEGORY_BLOCK, 666).getInt(666), 4).setHardness(50.0F).setResistance(2000.0F).setLightValue(0.8F).setStepSound(Block.soundStoneFootstep).setBlockName("ebonObsidian");
		mod_Ebon.ebonStone = new BlockEbonStone(getOrCreateIntProperty("ebonStoneID", CATEGORY_BLOCK, 667).getInt(667), 5).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setBlockName("ebonStone");
		mod_Ebon.ebonTorch = new BlockEbonTorch(getOrCreateIntProperty("ebonTorchID", CATEGORY_BLOCK, 668).getInt(668), 6).setHardness(0.0F).setLightValue(1.0F).setStepSound(Block.soundWoodFootstep).setBlockName("ebonTorch");
		mod_Ebon.bloodLeafCrops = new BlockBloodLeafCrops(getOrCreateIntProperty("bloodLeafCropsID", CATEGORY_BLOCK, 669).getInt(669), 248).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setBlockName("bloodLeafCrops");
		mod_Ebon.vaseOfSouls = new BlockVaseOfSouls(getOrCreateIntProperty("vaseOfSoulsID", CATEGORY_BLOCK, 670).getInt(670)).setHardness(0.9F).setStepSound(Block.soundGravelFootstep).setBlockName("vaseOfSouls");

		mod_Ebon.corpseDust = new ItemCorpseDust(getOrCreateIntProperty("corpseDustID", CATEGORY_ITEM, 8837).getInt(8837)).setIconCoord(0, 5).setItemName("corpseDust").setTabToDisplayOn(CreativeTabs.tabMaterials);
		mod_Ebon.undeadEssence = new ItemSpritesheet(getOrCreateIntProperty("undeadEssenceID", CATEGORY_ITEM, 8838).getInt(8838)).setIconCoord(1, 5).setItemName("undeadEssence").setTabToDisplayOn(CreativeTabs.tabMaterials);
		mod_Ebon.undeadEssenceConcentrated = new ItemSpritesheet(getOrCreateIntProperty("undeadEssenceConcentratedID", CATEGORY_ITEM, 8839).getInt(8839)).setIconCoord(2, 5).setItemName("undeadEssenceConcentrated").setTabToDisplayOn(CreativeTabs.tabMaterials);
		mod_Ebon.ebonGem = new ItemSpritesheet(getOrCreateIntProperty("ebonGemID", CATEGORY_ITEM, 8840).getInt(8840)).setIconCoord(3, 5).setItemName("ebonGem").setTabToDisplayOn(CreativeTabs.tabMaterials);
		mod_Ebon.ebonBroadsword = new ItemEbonBroadsword(getOrCreateIntProperty("ebonBroadswordID", CATEGORY_ITEM, 8841).getInt(8841)).setIconCoord(4, 5).setItemName("ebonBroadsword").setTabToDisplayOn(CreativeTabs.tabCombat);
		mod_Ebon.ebonPickaxe = new ItemEbonPickaxe(getOrCreateIntProperty("ebonPickaxeID", CATEGORY_ITEM, 8842).getInt(8842)).setIconCoord(5, 5).setItemName("ebonPickaxe").setTabToDisplayOn(CreativeTabs.tabTools);
		mod_Ebon.ebonSpade = new ItemEbonSpade(getOrCreateIntProperty("ebonSpadeID", CATEGORY_ITEM, 8843).getInt(8843)).setIconCoord(6, 5).setItemName("ebonSpade").setTabToDisplayOn(CreativeTabs.tabTools);
		mod_Ebon.ebonHatchet = new ItemEbonHatchet(getOrCreateIntProperty("ebonHatchetID", CATEGORY_ITEM, 8844).getInt(8844)).setIconCoord(7, 5).setItemName("ebonHatchet").setTabToDisplayOn(CreativeTabs.tabTools);
		mod_Ebon.darknessImbiber = new ItemDarknessImbiber(getOrCreateIntProperty("darknessImbiberID", CATEGORY_ITEM, 8845).getInt(8845)).setIconCoord(8, 5).setItemName("darknessImbiber").setTabToDisplayOn(CreativeTabs.tabTools);
		mod_Ebon.ebonCoal = new ItemSpritesheet(getOrCreateIntProperty("ebonCoalID", CATEGORY_ITEM, 8846).getInt(8846)).setIconCoord(9, 5).setItemName("ebonCoal").setTabToDisplayOn(CreativeTabs.tabMaterials);
		mod_Ebon.ebonGlowdust = new ItemSpritesheet(getOrCreateIntProperty("ebonGlowdustID", CATEGORY_ITEM, 8847).getInt(8847)).setIconCoord(10, 5).setItemName("ebonGlowdust").setTabToDisplayOn(CreativeTabs.tabMaterials);
		mod_Ebon.darknessIcon = new ItemSpritesheet(getOrCreateIntProperty("darknessIconID", CATEGORY_ITEM, 8848).getInt(8848)).setIconCoord(11, 5).setItemName("darknessIcon");
		mod_Ebon.ebonApple = new ItemEbonApple(getOrCreateIntProperty("ebonAppleID", CATEGORY_ITEM, 8849).getInt(8849)).setIconCoord(12, 5).setItemName("ebonApple").setTabToDisplayOn(CreativeTabs.tabFood);
		mod_Ebon.soul = new ItemSpritesheet(getOrCreateIntProperty("soulID", CATEGORY_ITEM, 8850).getInt(8850)).setIconCoord(13, 5).setItemName("soul").setTabToDisplayOn(CreativeTabs.tabMaterials);
		mod_Ebon.soulPowder = new ItemSoulPowder(getOrCreateIntProperty("soulPowderID", CATEGORY_ITEM, 8851).getInt(8851)).setIconCoord(14, 5).setItemName("soulPowder").setTabToDisplayOn(CreativeTabs.tabMisc);
		mod_Ebon.staffShard = new ItemSpritesheet(getOrCreateIntProperty("staffShardID", CATEGORY_ITEM, 8852).getInt(8852)).setIconCoord(15, 5).setItemName("staffShard").setTabToDisplayOn(CreativeTabs.tabMaterials);
		mod_Ebon.ebonStaff = new ItemSpritesheet(getOrCreateIntProperty("ebonStaffID", CATEGORY_ITEM, 8853).getInt(8853)).setIconCoord(0, 6).setMaxStackSize(1).setItemName("ebonStaff").setTabToDisplayOn(CreativeTabs.tabTools);
		mod_Ebon.ebonStaffCharged = new ItemEbonStaff(getOrCreateIntProperty("ebonStaffChargedID", CATEGORY_ITEM, 8854).getInt(8854)).setIconCoord(1, 6).setItemName("ebonStaffCharged").setTabToDisplayOn(CreativeTabs.tabTools);
		mod_Ebon.orbOfSouls = new ItemOrbOfSouls(getOrCreateIntProperty("orbOfSoulsID", CATEGORY_ITEM, 8855).getInt(8855)).setIconCoord(2, 6).setItemName("orbOfSouls").setTabToDisplayOn(CreativeTabs.tabTools);
		mod_Ebon.orbOfSoulsCharged = new ItemOrbOfSoulsCharged(getOrCreateIntProperty("orbOfSoulsChargedID", CATEGORY_ITEM, 8856).getInt(8856)).setIconCoord(3, 6).setItemName("orbOfSoulsCharged").setTabToDisplayOn(CreativeTabs.tabTools);
		mod_Ebon.staffOfSouls = new ItemSpritesheet(getOrCreateIntProperty("staffOfSoulsID", CATEGORY_ITEM, 8857).getInt(8857)).setIconCoord(4, 6).setItemName("staffOfSouls").setMaxStackSize(1).setTabToDisplayOn(CreativeTabs.tabTools);
		mod_Ebon.staffOfSoulsCharged = new ItemStaffOfSouls(getOrCreateIntProperty("staffOfSoulsChargedID", CATEGORY_ITEM, 8858).getInt(8858)).setIconCoord(5, 6).setItemName("staffOfSoulsCharged").setTabToDisplayOn(CreativeTabs.tabTools);
		mod_Ebon.demonicSoul = new ItemSpritesheet(getOrCreateIntProperty("demonicSoulID", CATEGORY_ITEM, 8859).getInt(8859)).setIconCoord(6, 6).setItemName("demonicSoul").setTabToDisplayOn(CreativeTabs.tabMaterials);
		mod_Ebon.bloodGem = new ItemSpritesheet(getOrCreateIntProperty("bloodGemID", CATEGORY_ITEM, 8860).getInt(8860)).setIconCoord(7, 6).setItemName("bloodGem").setTabToDisplayOn(CreativeTabs.tabMaterials);
		mod_Ebon.ebonScepter = new ItemEbonScpeter(getOrCreateIntProperty("ebonScepterID", CATEGORY_ITEM, 8861).getInt(8861)).setIconCoord(8, 6).setItemName("ebonScepter").setTabToDisplayOn(CreativeTabs.tabTools);
		mod_Ebon.soulGrindstone = new ItemSoulGrindstone(getOrCreateIntProperty("soulGrindstoneID", CATEGORY_ITEM, 8862).getInt(8862)).setIconCoord(9, 6).setItemName("soulGrindstone").setTabToDisplayOn(CreativeTabs.tabMaterials);
		mod_Ebon.wandOfRetrieval = new ItemWandOfRetrieval(getOrCreateIntProperty("wandOfRetrievalID", CATEGORY_ITEM, 8863).getInt(8863)).setIconCoord(10, 6).setItemName("wandOfRetrieval").setTabToDisplayOn(CreativeTabs.tabTools);
		mod_Ebon.bloodPowder = new ItemSpritesheet(getOrCreateIntProperty("bloodPowderID", CATEGORY_ITEM, 8864).getInt(8864)).setIconCoord(11, 6).setItemName("bloodPowder").setTabToDisplayOn(CreativeTabs.tabMaterials);
		mod_Ebon.bloodSeeds = new ItemBloodSeeds(getOrCreateIntProperty("bloodSeedsID", CATEGORY_ITEM, 8865).getInt(8865)).setIconCoord(12, 6).setItemName("bloodSeeds").setTabToDisplayOn(CreativeTabs.tabMaterials);
		mod_Ebon.bloodLeaf = new ItemPotionComponent(getOrCreateIntProperty("bloodLeafID", CATEGORY_ITEM, 8866).getInt(8866), "+4").setIconCoord(13, 6).setItemName("bloodLeaf").setTabToDisplayOn(CreativeTabs.tabBrewing);
		mod_Ebon.necromancerLexicon = new ItemSpritesheet(getOrCreateIntProperty("necromancerLexiconID", CATEGORY_ITEM, 8867).getInt(8867)).setIconCoord(14, 6).setItemName("necromancerLexicon").setTabToDisplayOn(CreativeTabs.tabTools);
		mod_Ebon.ebonScepterInfinity = new ItemInfinityScepter(getOrCreateIntProperty("ebonScepterInfinityID", CATEGORY_ITEM, 8868).getInt(8868)).setIconCoord(15, 6).setItemName("ebonScepterInfinity").setTabToDisplayOn(CreativeTabs.tabTools);
		mod_Ebon.ebonScepterZero = new ItemZeroScepter(getOrCreateIntProperty("ebonScepterZeroID", CATEGORY_ITEM, 8869).getInt(8869)).setIconCoord(0, 7).setItemName("ebonScepterZero").setTabToDisplayOn(CreativeTabs.tabTools);
		mod_Ebon.ebonScepterVoid = new ItemVoidScepter(getOrCreateIntProperty("ebonScepterVoidID", CATEGORY_ITEM, 8870).getInt(8870)).setIconCoord(1, 7).setItemName("ebonScepterVoid").setTabToDisplayOn(CreativeTabs.tabTools);
		mod_Ebon.ebonHood = new ItemEbonArmor(getOrCreateIntProperty("ebonHoodID", CATEGORY_ITEM, 8871).getInt(8871), 0).setIconCoord(2, 7).setItemName("ebonHood").setTabToDisplayOn(CreativeTabs.tabCombat);
		mod_Ebon.ebonRobeTop = new ItemEbonArmor(getOrCreateIntProperty("ebonRobeTopID", CATEGORY_ITEM, 8872).getInt(8872), 1).setIconCoord(3, 7).setItemName("ebonRobeTop").setTabToDisplayOn(CreativeTabs.tabCombat);
		mod_Ebon.ebonRobeBottom = new ItemEbonArmor(getOrCreateIntProperty("ebonRobeBottomID", CATEGORY_ITEM, 8873).getInt(8873), 2).setIconCoord(4, 7).setItemName("ebonRobeBottom").setTabToDisplayOn(CreativeTabs.tabCombat);
		mod_Ebon.ebonShoes = new ItemEbonArmor(getOrCreateIntProperty("ebonShoesID", CATEGORY_ITEM, 8874).getInt(8874), 3).setIconCoord(5, 7).setItemName("ebonShoes").setTabToDisplayOn(CreativeTabs.tabCombat);
		mod_Ebon.soulStone = new ItemSoulStone(getOrCreateIntProperty("soulStoneID", CATEGORY_ITEM, 8875).getInt(8875)).setIconCoord(6, 7).setItemName("soulStone").setTabToDisplayOn(CreativeTabs.tabMaterials);
		mod_Ebon.ebonCloth = new ItemSpritesheet(getOrCreateIntProperty("ebonClothID", CATEGORY_ITEM, 8876).getInt(8876)).setIconCoord(7, 7).setItemName("ebonCloth").setTabToDisplayOn(CreativeTabs.tabMaterials);
		mod_Ebon.plusiumCharm = new ItemPlusiumCharm(getOrCreateIntProperty("plusiumCharmID", CATEGORY_ITEM, 8877).getInt(8877)).setIconCoord(9, 7).setItemName("plusiumCharm").setTabToDisplayOn(CreativeTabs.tabTools);
		mod_Ebon.miniumCharm = new ItemMiniumCharm(getOrCreateIntProperty("miniumCharmID", CATEGORY_ITEM, 8878).getInt(8878)).setIconCoord(10, 7).setItemName("miniumCharm").setTabToDisplayOn(CreativeTabs.tabTools);
		mod_Ebon.gemOfDespair = new ItemSpritesheet(getOrCreateIntProperty("gemOfDespairID", CATEGORY_ITEM, 8879).getInt(8879)).setIconCoord(11, 7).setItemName("gemOfDespair").setTabToDisplayOn(CreativeTabs.tabMaterials);
		mod_Ebon.bottleOfDarkness = new ItemBottleOfDarkness(getOrCreateIntProperty("bottleOfDarknessID", CATEGORY_ITEM, 8880).getInt(8880)).setIconCoord(11, 10).setItemName("bottleOfDarkness");
		mod_Ebon.wandOfImprisionment = new ItemWandOfImprisionment(getOrCreateIntProperty("wandOfImprisionmentID", CATEGORY_ITEM, 8881).getInt(8881)).setIconCoord(12, 7).setItemName("wandOfImprisionment").setTabToDisplayOn(CreativeTabs.tabTools);
		mod_Ebon.altarBlueprint = new ItemAltarBlueprint(getOrCreateIntProperty("altarBlueprintID", CATEGORY_ITEM, 8882).getInt(8882)).setIconCoord(12, 3).setItemName("altarBlueprint");
		mod_Ebon.vaseOfSoulsItem = new ItemVaseOfSouls(getOrCreateIntProperty("vaseOfSoulsItemID", CATEGORY_ITEM, 8883).getInt(8883)).setIconCoord(13, 7).setItemName("vaseOfSoulsItem").setTabToDisplayOn(CreativeTabs.tabBlock);

		mod_Ebon.venomTouch = new EnchantmentVenomTouch(getOrCreateIntProperty("enchantmentVenomTouchID", CATEGORY_OTHERIDS, 133).getInt(133), 2).setName("venomTouch");
		mod_Ebon.magicalExhaustion = constructPotion(getOrCreateIntProperty("potionMagicalExhaustionID", CATEGORY_OTHERIDS, 27).getInt(27), true, 0x65007b, "Magical Exhaustion", 0, 1);

		save();
	}

	public Potion constructPotion(int id, boolean instant, int color, String name, int indexX, int indexY) {
		Constructor<Potion> constructor;
		Potion unModifiedPotion;
		try {
			constructor = (Constructor<Potion>) Potion.class.getDeclaredConstructors()[0];
			constructor.setAccessible(true);
			unModifiedPotion = constructor.newInstance(id, instant, color).setPotionName(name);
			for (Method method : Potion.class.getDeclaredMethods()) {
				method.setAccessible(true);
				if (method.getParameterTypes().length == 2) {
					Class<?>[] types = method.getParameterTypes();
					if (types[0].equals(int.class) && types[1].equals(int.class) && method.getReturnType().equals(Potion.class)) {
						Potion potion = (Potion) method.invoke(unModifiedPotion, indexX, indexY);
						return potion;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
