package net.minecraft.src;
import net.minecraft.src.forge.ITextureProvider;

public class ItemEbonSword extends ItemSword implements ITextureProvider{

	public ItemEbonSword(int i, EnumToolMaterial enumtoolmaterial) {
		super(i, enumtoolmaterial);
		maxStackSize = 1;
		setMaxDamage(enumtoolmaterial.getMaxUses());
		weaponDamage = 4 + enumtoolmaterial.getDamageVsEntity() * 2;
	}
	
    public int getItemEnchantability()
    {
        return 22;
    }
	
	public String getTextureFile() {
		return "/vazkii/ebonmod/sprites.png";
	}
    
	public int getDamageVsEntity(Entity entity)
    {
		if(EbonAPI.doesPlayerHaveMagicExhaustion()) return weaponDamage;
		
		if(entity instanceof EntityEbonGhost) return 4000;
		
		else if((entity instanceof EntityLiving) && (((EntityLiving)entity).getCreatureAttribute() == EnumCreatureAttribute.UNDEAD && !entity.isDead)){
		     entity.worldObj.playSoundAtEntity(entity, "vazkii.ebonmod.smite", 1.0F, 1.0F);
        	return ((EntityLiving)entity).getMaxHealth();
		}
        else return weaponDamage; 
    }
	
	private int weaponDamage;
}
