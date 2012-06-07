package net.minecraft.src;

/**
 * An event for the Staff of Souls.
 *
 * @author Vazkii
 * @see EbonAPI
 * @see ItemStaffOfSouls
 * @see EbonAPI_SimpleStaffOfSoulsEvent
 */
public abstract class EbonAPI_StaffOfSoulsEvent
{
    /**
     * Does an event at the defined Entity.
     *
     * @param world The Active World.
     * @param entity The Entity in which the event is being activated on.
     */
    public abstract void doEventAt(World world, EntityLiving entity);

    /**
     * If the event requires a Necromancer's Lexicon.
     *
     * @return If the event does or not.
     */
    public abstract boolean requiresTome();

    /**
     * Checks if the event can be done under the specified conditions, good for checking entity health or light value.
     *
     * @param world The active World.
     * @param entity The Entity in which the event is being checked.
     * @return If the event can be done or not.
     */
    public abstract boolean canDoEvent(World world, EntityLiving entity);
}
