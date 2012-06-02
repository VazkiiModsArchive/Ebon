package net.minecraft.src;


public class Ebon3DItems extends ItemEbonMod{

	EnumRarity er;
	
	public Ebon3DItems(int i, EnumRarity e){
		super(i);
		er = e;
	}
	
	public Ebon3DItems(int i, EnumRarity e, String s){
		super(i, s);
		er = e;
		
	}
	
    public EnumRarity getRarity(ItemStack itemstack)
    {
        return er;
    }

    public boolean isFull3D()
    {
        return true;
    }
}
