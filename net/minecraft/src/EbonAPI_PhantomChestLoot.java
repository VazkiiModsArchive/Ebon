package net.minecraft.src;

import net.minecraft.src.forge.DungeonLoot; //Import for API purposes only.
import java.util.Random;


/**
 * A class that holds information about loot in the Phantom Chest.
 * 
 * @author Vazkii
 * @see EbonAPI
 * @see TileEntityPhantomChest
 * @see DungeonLoot
 * @see PhantomChestLoot
 */
public class EbonAPI_PhantomChestLoot {
	
	/**
	 * The rank of the Phantom Chest that this item will get placed in in the occasion that it gets rolled.
	 */
	private int rank;
	
	/**
	 * Holds the index in the HashMap of this instance.
	 */
	private int index;
	
	/**
	 * The ItemStack that this represents, you should never call this field directly, use copy() instead.
	 */
	private ItemStack itemStack; 
	
	/**
	 * The chance this will be actually placed if rolled, in percentage.
	 */
	private int chance = 100;
	
	/**
	 * The minimum quantity of the ItemStack that will get placed if the ItemStack gets placed.
	 */
	private int qtdMin = -1;

	/**
	 * The maximum quantity of the ItemStack that will get placed if the ItemStack gets placed, used as
	 * the constant quantity if qtdMin is set to a negative value or zero;
	 */
	private int qtdMax = 1;
	
	/**
	 * The Array holding the enchantments that will be placed in the ItemStack upon generating.
	 */
	private Enchantment[] enchantList = null;
	
	/**
	 * The Array holding the levels of the enchantments that will be placed in the ItemStack upon generating.
	 */
	private int enchantmentLevels[];
	
	/**
	 * NOTE: The constructor doesn't add all the information required, also take a look at
	 * the setMaxQtd(...), setMinQtd(...), setChance(...) and addEnchantments(...) methods.
	 * 
	 * @param itemstack The ItemStack that will get placed if this gets rolled.
	 * @param rank The Chest Rank that this is acossiated to.
	 */
	public EbonAPI_PhantomChestLoot(ItemStack itemStack, int rank){
		this.itemStack = itemStack;
		this.rank = rank;
		index = EbonAPI.phantomLootList.size();
	}
	
	/**
	 * Changes the maximum quantity of items that can be spawned in this in the occasion this gets rolled, this is also used
	 * to set the constant quantity of items spawned if setMinQtd(...) isn't called or is called with an invalid value.
	 * 
	 * @param qtd The quantity to set.
	 */
	public void setMaxQtd(int qtd){
		qtdMax = qtd;
	}
	
	/**
	 * Changes the minimum quantity of items that can be spawned in this in the occasion that this gets rolled, make sure to also
	 * call setMaxQtd(...) otherwise, the numbers may be stale or contradict with each other.
	 * 
	 * @param qtd The quantity to set.
	 */
	public void setMinQtd(int qtd){
		qtdMin = qtd;
	}
	
	/**
	 * Sets the chance (in percentage) that the items will actually get placed in the chest in the occasion that this gets rolled.
	 * 
	 * @param chance The chance to set.
	 */
	public void setChance(int chance){
		if(chance <= 100 && chance >= 1)
		this.chance = chance;
		else throw new IndexOutOfBoundsException(chance + " at index " + index + ".");
	}
	
	/**
	 * Adds enchantments for the item that will get placed, note that any enchantment can be placed in any item, so
	 * you can have virtually anything.
	 * 
	 * @param enchantmentArray The Array of Enchantments to get placed in the item.
	 * @param levels The Array indicating the Levels of the Enchantments, these must be in the same order as the Enchantments are,
	 * any levels over 50 won't get properly displayed on a tooltip and any levels over 127 won't work at all.
	 */
	public void addEnchantments(Enchantment[] enchantmentArray, int[] levels){
		enchantList = enchantmentArray;
		enchantmentLevels = levels;
	}
	
	/**
	 * Gets the rank for this instance.
	 * 
	 * @return The rank.
	 */
	public int getRank(){
		return rank;
	}
	
	/**
	 * Creates an ItemStack that can get used in other methods.
	 * 
	 * @return The ItemStack created by this instance.
	 */
	public ItemStack createItem(){
		Random r = new Random();
		int itemCount;
		ItemStack stack = itemStack.copy();
		
		if(!(r.nextInt(100) < chance)) return null;
		
		if(qtdMin > 0){
			do{
				itemCount = r.nextInt(qtdMax); 
			}while(itemCount <= qtdMin);
			
			stack.stackSize = itemCount;
		}
		
		if(enchantList != null){
			for(int i=0; i<enchantList.length; i++)
				stack.addEnchantment(enchantList[i], enchantmentLevels[i]);
			
		}
		
		return stack;
	}

}
