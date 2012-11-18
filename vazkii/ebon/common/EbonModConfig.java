package vazkii.ebon.common;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.TreeMap;

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

import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Material;
import net.minecraft.src.Potion;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

public class EbonModConfig extends Configuration {

	public static final String CATEGORY_OTHERIDS = "otherids";
	public static TreeMap otherIDsProps = new TreeMap();

	// Props Start
	// ============================================================================
	public static boolean canReforgeArmor = true;

	// Props Start
	// ============================================================================

	public EbonModConfig(File file) {
		super(file);
		categories.put(CATEGORY_OTHERIDS, otherIDsProps);

		load();

		Property propCanReforgeArmor = get("canReforgeArmor", CATEGORY_GENERAL, true);
		propCanReforgeArmor.comment = "Set to true to allow reforging of Ebon Armor.";
		canReforgeArmor = propCanReforgeArmor.getBoolean(true);

		mod_Ebon.ebonGlowstone = new BlockEbonGlowstone(get("ebonGlowstoneID", CATEGORY_BLOCK, 662).getInt(662), 0).setHardness(0.3F).setStepSound(Block.soundGlassFootstep).setLightValue(1.0F).setBlockName("ebonGlowstone");
		mod_Ebon.ebonGemBlock = new BlockSpritesheet(get("ebonGemBlockID", CATEGORY_BLOCK, 663).getInt(663), 1, Material.iron).setHardness(3.0F).setResistance(5.0F).setLightValue(0.8F).setStepSound(Block.soundStoneFootstep).setBlockName("ebonGemBlock");
		mod_Ebon.bloodGemBlock = new BlockSpritesheet(get("bloodGemBlockID", CATEGORY_BLOCK, 664).getInt(664), 2, Material.iron).setHardness(3.0F).setResistance(5.0F).setLightValue(0.8F).setStepSound(Block.soundStoneFootstep).setBlockName("bloodGemBlock");
		mod_Ebon.quicksand = new BlockQuicksand(get("quicksandID", CATEGORY_BLOCK, 665).getInt(665), 3).setHardness(1.2F).setStepSound(Block.soundSandFootstep).setBlockName("quicksand");
		mod_Ebon.ebonObsidian = new BlockEbonObsidian(get("ebonObsidianID", CATEGORY_BLOCK, 666).getInt(666), 4).setHardness(50.0F).setResistance(2000.0F).setLightValue(0.8F).setStepSound(Block.soundStoneFootstep).setBlockName("ebonObsidian");
		mod_Ebon.ebonStone = new BlockEbonStone(get("ebonStoneID", CATEGORY_BLOCK, 667).getInt(667), 5).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setBlockName("ebonStone");
		mod_Ebon.ebonTorch = new BlockEbonTorch(get("ebonTorchID", CATEGORY_BLOCK, 668).getInt(668), 6).setHardness(0.0F).setLightValue(1.0F).setStepSound(Block.soundWoodFootstep).setBlockName("ebonTorch");
		mod_Ebon.bloodLeafCrops = new BlockBloodLeafCrops(get("bloodLeafCropsID", CATEGORY_BLOCK, 669).getInt(669), 248).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setBlockName("bloodLeafCrops");
		mod_Ebon.vaseOfSouls = new BlockVaseOfSouls(get("vaseOfSoulsID", CATEGORY_BLOCK, 670).getInt(670)).setHardness(0.9F).setStepSound(Block.soundGravelFootstep).setBlockName("vaseOfSouls");

		mod_Ebon.corpseDust = new ItemCorpseDust(get("corpseDustID", CATEGORY_ITEM, 8837).getInt(8837)).setIconCoord(0, 5).setItemName("corpseDust").setCreativeTab(CreativeTabs.tabMaterials);
		mod_Ebon.undeadEssence = new ItemSpritesheet(get("undeadEssenceID", CATEGORY_ITEM, 8838).getInt(8838)).setIconCoord(1, 5).setItemName("undeadEssence").setCreativeTab(CreativeTabs.tabMaterials);
		mod_Ebon.undeadEssenceConcentrated = new ItemSpritesheet(get("undeadEssenceConcentratedID", CATEGORY_ITEM, 8839).getInt(8839)).setIconCoord(2, 5).setItemName("undeadEssenceConcentrated").setCreativeTab(CreativeTabs.tabMaterials);
		mod_Ebon.ebonGem = new ItemSpritesheet(get("ebonGemID", CATEGORY_ITEM, 8840).getInt(8840)).setIconCoord(3, 5).setItemName("ebonGem").setCreativeTab(CreativeTabs.tabMaterials);
		mod_Ebon.ebonBroadsword = new ItemEbonBroadsword(get("ebonBroadswordID", CATEGORY_ITEM, 8841).getInt(8841)).setIconCoord(4, 5).setItemName("ebonBroadsword").setCreativeTab(CreativeTabs.tabCombat);
		mod_Ebon.ebonPickaxe = new ItemEbonPickaxe(get("ebonPickaxeID", CATEGORY_ITEM, 8842).getInt(8842)).setIconCoord(5, 5).setItemName("ebonPickaxe").setCreativeTab(CreativeTabs.tabTools);
		mod_Ebon.ebonSpade = new ItemEbonSpade(get("ebonSpadeID", CATEGORY_ITEM, 8843).getInt(8843)).setIconCoord(6, 5).setItemName("ebonSpade").setCreativeTab(CreativeTabs.tabTools);
		mod_Ebon.ebonHatchet = new ItemEbonHatchet(get("ebonHatchetID", CATEGORY_ITEM, 8844).getInt(8844)).setIconCoord(7, 5).setItemName("ebonHatchet").setCreativeTab(CreativeTabs.tabTools);
		mod_Ebon.darknessImbiber = new ItemDarknessImbiber(get("darknessImbiberID", CATEGORY_ITEM, 8845).getInt(8845)).setIconCoord(8, 5).setItemName("darknessImbiber").setCreativeTab(CreativeTabs.tabTools);
		mod_Ebon.ebonCoal = new ItemSpritesheet(get("ebonCoalID", CATEGORY_ITEM, 8846).getInt(8846)).setIconCoord(9, 5).setItemName("ebonCoal").setCreativeTab(CreativeTabs.tabMaterials);
		mod_Ebon.ebonGlowdust = new ItemSpritesheet(get("ebonGlowdustID", CATEGORY_ITEM, 8847).getInt(8847)).setIconCoord(10, 5).setItemName("ebonGlowdust").setCreativeTab(CreativeTabs.tabMaterials);
		mod_Ebon.darknessIcon = new ItemSpritesheet(get("darknessIconID", CATEGORY_ITEM, 8848).getInt(8848)).setIconCoord(11, 5).setItemName("darknessIcon");
		mod_Ebon.ebonApple = new ItemEbonApple(get("ebonAppleID", CATEGORY_ITEM, 8849).getInt(8849)).setIconCoord(12, 5).setItemName("ebonApple").setCreativeTab(CreativeTabs.tabFood);
		mod_Ebon.soul = new ItemSpritesheet(get("soulID", CATEGORY_ITEM, 8850).getInt(8850)).setIconCoord(13, 5).setItemName("soul").setCreativeTab(CreativeTabs.tabMaterials);
		mod_Ebon.soulPowder = new ItemSoulPowder(get("soulPowderID", CATEGORY_ITEM, 8851).getInt(8851)).setIconCoord(14, 5).setItemName("soulPowder").setCreativeTab(CreativeTabs.tabMisc);
		mod_Ebon.staffShard = new ItemSpritesheet(get("staffShardID", CATEGORY_ITEM, 8852).getInt(8852)).setIconCoord(15, 5).setItemName("staffShard").setCreativeTab(CreativeTabs.tabMaterials);
		mod_Ebon.ebonStaff = new ItemSpritesheet(get("ebonStaffID", CATEGORY_ITEM, 8853).getInt(8853)).setIconCoord(0, 6).setMaxStackSize(1).setItemName("ebonStaff").setCreativeTab(CreativeTabs.tabTools);
		mod_Ebon.ebonStaffCharged = new ItemEbonStaff(get("ebonStaffChargedID", CATEGORY_ITEM, 8854).getInt(8854)).setIconCoord(1, 6).setItemName("ebonStaffCharged").setCreativeTab(CreativeTabs.tabTools);
		mod_Ebon.orbOfSouls = new ItemOrbOfSouls(get("orbOfSoulsID", CATEGORY_ITEM, 8855).getInt(8855)).setIconCoord(2, 6).setItemName("orbOfSouls").setCreativeTab(CreativeTabs.tabTools);
		mod_Ebon.orbOfSoulsCharged = new ItemOrbOfSoulsCharged(get("orbOfSoulsChargedID", CATEGORY_ITEM, 8856).getInt(8856)).setIconCoord(3, 6).setItemName("orbOfSoulsCharged").setCreativeTab(CreativeTabs.tabTools);
		mod_Ebon.staffOfSouls = new ItemSpritesheet(get("staffOfSoulsID", CATEGORY_ITEM, 8857).getInt(8857)).setIconCoord(4, 6).setItemName("staffOfSouls").setMaxStackSize(1).setCreativeTab(CreativeTabs.tabTools);
		mod_Ebon.staffOfSoulsCharged = new ItemStaffOfSouls(get("staffOfSoulsChargedID", CATEGORY_ITEM, 8858).getInt(8858)).setIconCoord(5, 6).setItemName("staffOfSoulsCharged").setCreativeTab(CreativeTabs.tabTools);
		mod_Ebon.demonicSoul = new ItemSpritesheet(get("demonicSoulID", CATEGORY_ITEM, 8859).getInt(8859)).setIconCoord(6, 6).setItemName("demonicSoul").setCreativeTab(CreativeTabs.tabMaterials);
		mod_Ebon.bloodGem = new ItemSpritesheet(get("bloodGemID", CATEGORY_ITEM, 8860).getInt(8860)).setIconCoord(7, 6).setItemName("bloodGem").setCreativeTab(CreativeTabs.tabMaterials);
		mod_Ebon.ebonScepter = new ItemEbonScpeter(get("ebonScepterID", CATEGORY_ITEM, 8861).getInt(8861)).setIconCoord(8, 6).setItemName("ebonScepter").setCreativeTab(CreativeTabs.tabTools);
		mod_Ebon.soulGrindstone = new ItemSoulGrindstone(get("soulGrindstoneID", CATEGORY_ITEM, 8862).getInt(8862)).setIconCoord(9, 6).setItemName("soulGrindstone").setCreativeTab(CreativeTabs.tabMaterials);
		mod_Ebon.wandOfRetrieval = new ItemWandOfRetrieval(get("wandOfRetrievalID", CATEGORY_ITEM, 8863).getInt(8863)).setIconCoord(10, 6).setItemName("wandOfRetrieval").setCreativeTab(CreativeTabs.tabTools);
		mod_Ebon.bloodPowder = new ItemSpritesheet(get("bloodPowderID", CATEGORY_ITEM, 8864).getInt(8864)).setIconCoord(11, 6).setItemName("bloodPowder").setCreativeTab(CreativeTabs.tabMaterials);
		mod_Ebon.bloodSeeds = new ItemBloodSeeds(get("bloodSeedsID", CATEGORY_ITEM, 8865).getInt(8865)).setIconCoord(12, 6).setItemName("bloodSeeds").setCreativeTab(CreativeTabs.tabMaterials);
		mod_Ebon.bloodLeaf = new ItemPotionComponent(get("bloodLeafID", CATEGORY_ITEM, 8866).getInt(8866), "+4").setIconCoord(13, 6).setItemName("bloodLeaf").setCreativeTab(CreativeTabs.tabBrewing);
		mod_Ebon.necromancerLexicon = new ItemSpritesheet(get("necromancerLexiconID", CATEGORY_ITEM, 8867).getInt(8867)).setIconCoord(14, 6).setItemName("necromancerLexicon").setCreativeTab(CreativeTabs.tabTools);
		mod_Ebon.ebonScepterInfinity = new ItemInfinityScepter(get("ebonScepterInfinityID", CATEGORY_ITEM, 8868).getInt(8868)).setIconCoord(15, 6).setItemName("ebonScepterInfinity").setCreativeTab(CreativeTabs.tabTools);
		mod_Ebon.ebonScepterZero = new ItemZeroScepter(get("ebonScepterZeroID", CATEGORY_ITEM, 8869).getInt(8869)).setIconCoord(0, 7).setItemName("ebonScepterZero").setCreativeTab(CreativeTabs.tabTools);
		mod_Ebon.ebonScepterVoid = new ItemVoidScepter(get("ebonScepterVoidID", CATEGORY_ITEM, 8870).getInt(8870)).setIconCoord(1, 7).setItemName("ebonScepterVoid").setCreativeTab(CreativeTabs.tabTools);
		mod_Ebon.ebonHood = new ItemEbonArmor(get("ebonHoodID", CATEGORY_ITEM, 8871).getInt(8871), 0).setIconCoord(2, 7).setItemName("ebonHood").setCreativeTab(CreativeTabs.tabCombat);
		mod_Ebon.ebonRobeTop = new ItemEbonArmor(get("ebonRobeTopID", CATEGORY_ITEM, 8872).getInt(8872), 1).setIconCoord(3, 7).setItemName("ebonRobeTop").setCreativeTab(CreativeTabs.tabCombat);
		mod_Ebon.ebonRobeBottom = new ItemEbonArmor(get("ebonRobeBottomID", CATEGORY_ITEM, 8873).getInt(8873), 2).setIconCoord(4, 7).setItemName("ebonRobeBottom").setCreativeTab(CreativeTabs.tabCombat);
		mod_Ebon.ebonShoes = new ItemEbonArmor(get("ebonShoesID", CATEGORY_ITEM, 8874).getInt(8874), 3).setIconCoord(5, 7).setItemName("ebonShoes").setCreativeTab(CreativeTabs.tabCombat);
		mod_Ebon.soulStone = new ItemSoulStone(get("soulStoneID", CATEGORY_ITEM, 8875).getInt(8875)).setIconCoord(6, 7).setItemName("soulStone").setCreativeTab(CreativeTabs.tabMaterials);
		mod_Ebon.ebonCloth = new ItemSpritesheet(get("ebonClothID", CATEGORY_ITEM, 8876).getInt(8876)).setIconCoord(7, 7).setItemName("ebonCloth").setCreativeTab(CreativeTabs.tabMaterials);
		mod_Ebon.plusiumCharm = new ItemPlusiumCharm(get("plusiumCharmID", CATEGORY_ITEM, 8877).getInt(8877)).setIconCoord(9, 7).setItemName("plusiumCharm").setCreativeTab(CreativeTabs.tabTools);
		mod_Ebon.miniumCharm = new ItemMiniumCharm(get("miniumCharmID", CATEGORY_ITEM, 8878).getInt(8878)).setIconCoord(10, 7).setItemName("miniumCharm").setCreativeTab(CreativeTabs.tabTools);
		mod_Ebon.gemOfDespair = new ItemSpritesheet(get("gemOfDespairID", CATEGORY_ITEM, 8879).getInt(8879)).setIconCoord(11, 7).setItemName("gemOfDespair").setCreativeTab(CreativeTabs.tabMaterials);
		mod_Ebon.bottleOfDarkness = new ItemBottleOfDarkness(get("bottleOfDarknessID", CATEGORY_ITEM, 8880).getInt(8880)).setIconCoord(11, 10).setItemName("bottleOfDarkness");
		mod_Ebon.wandOfImprisionment = new ItemWandOfImprisionment(get("wandOfImprisionmentID", CATEGORY_ITEM, 8881).getInt(8881)).setIconCoord(12, 7).setItemName("wandOfImprisionment").setCreativeTab(CreativeTabs.tabTools);
		mod_Ebon.altarBlueprint = new ItemAltarBlueprint(get("altarBlueprintID", CATEGORY_ITEM, 8882).getInt(8882)).setIconCoord(12, 3).setItemName("altarBlueprint");
		mod_Ebon.vaseOfSoulsItem = new ItemVaseOfSouls(get("vaseOfSoulsItemID", CATEGORY_ITEM, 8883).getInt(8883)).setIconCoord(13, 7).setItemName("vaseOfSoulsItem").setCreativeTab(CreativeTabs.tabBlock);

		mod_Ebon.venomTouch = new EnchantmentVenomTouch(get("enchantmentVenomTouchID", CATEGORY_OTHERIDS, 133).getInt(133), 2).setName("venomTouch");
		mod_Ebon.magicalExhaustion = constructPotion(get("potionMagicalExhaustionID", CATEGORY_OTHERIDS, 27).getInt(27), true, 0x65007b, "Magical Exhaustion", 0, 1);

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
