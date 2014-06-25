package resonant.api.event;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.event.Cancelable;
import universalelectricity.api.vector.Vector3;

/** @author Darkguardsman */
@Cancelable
public class LaserFireEvent extends LaserEvent
{
    public Object shooter;

    public LaserFireEvent(World world, Vector3 spot, Vector3 target, Object shooter)
    {
        super(world, spot, target);
        this.shooter = shooter;
    }

    public LaserFireEvent(TileEntity tileEntity, MovingObjectPosition hit)
    {
        super(tileEntity.worldObj, new Vector3(tileEntity), new Vector3(hit));
        this.shooter = tileEntity;
    }
}