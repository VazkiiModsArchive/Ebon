package net.minecraft.src;

import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The main Class for the Ebon Mod API.
 * 
 * @author Vazkii
 */
public class EbonAPI {
	
	/**
	 * The HashMap containing all the events for the Ebon Staff.
	 * @see EbonAPI_EbonStaffEvent
	 */
	public static HashMap<Integer, EbonAPI_EbonStaffEvent> esEvents = new HashMap();
	
	/**
	 * The HashMap containing all the events for the Staff of Souls.
	 * @see EbonAPI_StaffOfSoulsEvent
	 */
	public static HashMap<String, EbonAPI_StaffOfSoulsEvent> sosEvents = new HashMap();
	
	/**
	 * The List of registered Ebon Scepters, used to check if the player has multiple scepters.
	 */
	public static List<ItemStack> scepterList = new ArrayList();
	
	/**
	 * The List of possible loot for the Phantom Chests
	 */
	public static List<EbonAPI_PhantomChestLoot> phantomLootList = new ArrayList();
	
	/**
	 * Used to get the version of the API to prevent the game from crashing randomly.<br><br>
	 * 
	 * How to use:
	 * <pre>{@code 		
	 * 	private static final int apiVersionRequired = VERSION;
	 *	if(EbonAPI.getAPIVersion() < apiVersionRequired)
     *	MinecraftForge.killMinecraft(MOD_NAME, "Ebon API too old, require at least version " + apiVersionRequired + " , curent version is " + EbonAPI.getAPIVersion() + ".");}</pre>
	 * 
	 * @return The version.
	 */
	public static int getAPIVersion(){
		return 1;
	}
	
	/**
	 * Used to check if the player has a Necromancer's Lexicon in it's inventory.
	 * 
	 * @return If it has it or not.
	 * @see ItemNecroTome
	 */
    public static boolean hasNecroTome(){
    	EntityPlayer ep = ModLoader.getMinecraftInstance().thePlayer;
    	return ep.inventory.getStackInSlot(8) != null && ep.inventory.getStackInSlot(8).itemID ==  mod_Ebon.necroTome.shiftedIndex;
    }
    
	/**
	 * Used to get the Necromancer's Lexicon in the player's inventory.
	 * 
	 * @return The ItemStack of the Lexicon in the inventory.
	 * @see ItemNecroTome
	 */
    public static ItemStack getNecroTome(){
    	if(!hasNecroTome()) return null;
    	return ModLoader.getMinecraftInstance().thePlayer.inventory.getStackInSlot(8);
    }
    
    /**
     * Used to check if the player has the Magical Exhaustion Effect.
     * 
     * @return If the player has the effect or not.
     */
    public static boolean doesPlayerHaveMagicExhaustion(){
    	return ModLoader.getMinecraftInstance().thePlayer.activePotionsMap.containsKey(new PotionEffect(mod_Ebon.magicExhaustion.id, 1, 0).getPotionID());
    }
    
    /**
     * Used to add the Magical Exhaustion effect to the player for a specific amount of time.
     * 
     * @param time The amount of time to add the Magical Exhaustion effect to the player for, in seconds.
     */
    public static void addMagicalExhaustionOnPlayerFor(int time){
    	EntityPlayer player = ModLoader.getMinecraftInstance().thePlayer;
    	if(doesPlayerHaveMagicExhaustion()) return;
    	
    	player.addPotionEffect(new PotionEffect(mod_Ebon.magicExhaustion.id, time*20, 0));
    }
    
    /**
     * Used to add an event to the Ebon Staff.
     * 
     * @param blockID The Block ID acossiated to the event.
     * @param staffEvent The event to be acossiated to the Block ID.
     * @see EbonAPI_EbonStaffEvent
     */
    public static void addEbonStaffEvent(int blockID, EbonAPI_EbonStaffEvent staffEvent){
    	esEvents.put(blockID, staffEvent);
    }
    
    /**
     * Used to add a simple event to the Ebon Staff.
     * 
     * @param blockID The Block ID used as the target.
     * @param idTo The Block ID in which the target will get transformed into.
     * @see EbonAPI_SimpleEbonStaffEvent
     */
    public static void addSimpleEbonStaffEvent(int blockID, int idTo){
    	esEvents.put(blockID, new EbonAPI_SimpleEbonStaffEvent(idTo));
    }
    
    /**
     * Used to add an event to the Staff of Souls.
     * 
     * @param string The entity's string acossiated to the event.
     * @param staffEvent The event to be acossiated to the entity.
     * @see EbonAPI_StaffOfSoulsEvent
     */
    public static void addStaffOfSoulsEvent(String string, EbonAPI_StaffOfSoulsEvent staffEvent){
    	sosEvents.put(string, staffEvent);
    }
    
    /**
     * Used to add a simple event to the Staff of Souls.
     * 
     * @param entityFrom The entity's string used as the target.
     * @param entityTo The entity String in which the target will get transformed into.
     * @see EbonAPI_SimpleStaffOfSoulsEvent
     */
    public static void addSimpleStaffOfSoulsEvent(String entityFrom, String entityTo){
    	sosEvents.put(entityFrom, new EbonAPI_SimpleStaffOfSoulsEvent(entityTo));
    }
    
    /**
     * Used to add an Ebon Scepter, this is used to calculate the scepters the player
     * has in order to deal damage in the case of multiple.
     * 
     * @param scepter The scepter to add to the list.
     * @see ItemEbonScepter
     */
    public static void addScepter(ItemStack scepter){
    	if(!scepterList.contains(scepter))
    	scepterList.add(scepter);
    }
    
    /**
     * Used to add loot to the phantom chests.
     * 
     * @param loot The loot to be added.
     * @see EbonAPI_PhantomChestLoot
     */
    public static void addPhantomLoot(EbonAPI_PhantomChestLoot loot){
    	if(!phantomLootList.contains(loot))
    		phantomLootList.add(loot);
    }
    

}
