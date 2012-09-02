package vazkii.ebon.api;

public enum ArmorType {

	HELM, CHEST, LEGS, BOOTS;

	public static ArmorType getFromIndex(int index) {
		switch (index) {
		case 0:
			return HELM;
		case 1:
			return CHEST;
		case 2:
			return LEGS;
		default:
			return BOOTS;
		}
	}

}
