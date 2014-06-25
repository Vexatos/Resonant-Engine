package resonant.api.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.event.Cancelable;
import universalelectricity.api.vector.Vector3;

/** @author Darkguardsman */
@Cancelable
public class LaserFiredPlayerEvent extends LaserFireEvent
{
    public ItemStack laserItem;
    public MovingObjectPosition hit;

    public LaserFiredPlayerEvent(EntityPlayer player, MovingObjectPosition hit, ItemStack stack)
    {
        super(player.worldObj, new Vector3(player), new Vector3(hit), player);
        this.laserItem = stack;
        this.hit = hit;
    }
}