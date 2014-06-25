package resonant.api.event;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import universalelectricity.api.vector.Vector3;

/** Basic event for getting items dropped from a destroyed block
 * 
 * @author Darkguardsman */
public class LaserDropItemEvent extends LaserEvent
{
    /** Items dropped by the block */
    public List<ItemStack> items;

    public LaserDropItemEvent(World world, Vector3 spot, Vector3 hit, List<ItemStack> items)
    {
        super(world, spot, hit);
        this.items = items;
    }
}