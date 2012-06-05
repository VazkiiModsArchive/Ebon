package net.minecraft.src;

import java.util.ArrayList;

import codechicken.nei.API;
import codechicken.nei.IConfigureNEI;
import codechicken.nei.MultiItemRange;

public class NEIEbonModConfig implements IConfigureNEI {

	public void loadConfig() {
		API.setMaxDamageException(mod_Ebon.ebonstaffc.shiftedIndex, 0);
		API.setMaxDamageException(mod_Ebon.soulstaffc.shiftedIndex, 0);
		API.setMaxDamageException(mod_Ebon.soulorb.shiftedIndex, 0);
		API.setMaxDamageException(mod_Ebon.ebonArmor_Hood.shiftedIndex, 4);
		API.setMaxDamageException(mod_Ebon.ebonArmor_RobeTop.shiftedIndex, 4);
		API.hideItem(mod_Ebon.mobSpawnerItem.shiftedIndex);
		
		MultiItemRange r = new MultiItemRange();
		r.add(mod_Ebon.mortarPestle);
		r.add(mod_Ebon.corpsedust);
		r.add(mod_Ebon.deaddust);
		r.add(mod_Ebon.ebongem);
		r.add(mod_Ebon.soulgem);
		r.add(mod_Ebon.soul);
		r.add(mod_Ebon.sould);
		r.add(mod_Ebon.staffshard);
		r.add(mod_Ebon.soulorbc);
		r.add(mod_Ebon.bloodPowder);
		r.add(mod_Ebon.bloodSeeds);
		r.add(mod_Ebon.bloodLeaf);
		r.add(mod_Ebon.soulStone);
		r.add(mod_Ebon.ebonCloth);
		
		API.addSetRange("Vazkii Mods.Ebon Mod.Components", r);
		MultiItemRange r1 = new MultiItemRange();
		
		r1.add(mod_Ebon.necroTome);
		r1.add(mod_Ebon.ebonstaffc);
		r1.add(mod_Ebon.soulstaffc);
		r1.add(mod_Ebon.soulorb);
		r1.add(mod_Ebon.ebonscepter);
		r1.add(mod_Ebon.ebonscepterVoid);
		r1.add(mod_Ebon.ebonscepterZero);
		r1.add(mod_Ebon.ebonscepterInfinity);
		r1.add(mod_Ebon.wandOfRetrieval);

		API.addSetRange("Vazkii Mods.Ebon Mod.Tools.Necro Tools", r1);
		MultiItemRange r2 = new MultiItemRange();
		
		r2.add(mod_Ebon.ebonsword);
		r2.add(mod_Ebon.ebonpick);
		r2.add(mod_Ebon.ebonspade);
		r2.add(mod_Ebon.ebonax);
		r2.add(mod_Ebon.ebonbow);
		r2.add(mod_Ebon.darkfert);
		r2.add(mod_Ebon.ebonapple);
		r2.add(mod_Ebon.soulsub);
		
		API.addSetRange("Vazkii Mods.Ebon Mod.Tools.Other Tools", r2);
		MultiItemRange r3 = new MultiItemRange();
		
		r3.add(mod_Ebon.ebonArmor_RobeBottom);
		r3.add(mod_Ebon.ebonArmor_Shoes);
		
		API.addSetRange("Vazkii Mods.Ebon Mod.Armor.Normal Armor", r3);
		MultiItemRange r4 = new MultiItemRange();
		
		r4.add(new ItemStack(mod_Ebon.ebonArmor_Hood, 1, 1));
		r4.add(new ItemStack(mod_Ebon.ebonArmor_Hood, 1, 2));
		r4.add(new ItemStack(mod_Ebon.ebonArmor_Hood, 1, 3));
		r4.add(new ItemStack(mod_Ebon.ebonArmor_Hood, 1, 4));
		r4.add(new ItemStack(mod_Ebon.ebonArmor_RobeTop, 1, 1));
		r4.add(new ItemStack(mod_Ebon.ebonArmor_RobeTop, 1, 2));
		r4.add(new ItemStack(mod_Ebon.ebonArmor_RobeTop, 1, 3));
		r4.add(new ItemStack(mod_Ebon.ebonArmor_RobeTop, 1, 4));
		
		API.addSetRange("Vazkii Mods.Ebon Mod.Armor.Special Armor", r4);
		MultiItemRange r5 = new MultiItemRange();
		
		r5.add(mod_Ebon.ebonblock);
		r5.add(mod_Ebon.soulgemblock);
		r5.add(mod_Ebon.ebonstone);
		r5.add(mod_Ebon.ebonglow);
		r5.add(mod_Ebon.darkobsidian);
		r5.add(mod_Ebon.ebontorch);
		r5.add(mod_Ebon.phantomChest);
		
		API.addSetRange("Vazkii Mods.Ebon Mod.Blocks", r5);
		MultiItemRange r6 = new MultiItemRange();
		
		r6.add(mod_Ebon.phantomKey);
		r6.add(mod_Ebon.dustpile);
		r6.add(mod_Ebon.eboncoal);
		r6.add(mod_Ebon.glowdustEbon);
		r6.add(mod_Ebon.ebonstaffu);
		r6.add(mod_Ebon.soulstaffu);
		r6.add(mod_Ebon.altarBlueprint);
		
		API.addSetRange("Vazkii Mods.Ebon Mod.Misc", r6);
	}	

}
