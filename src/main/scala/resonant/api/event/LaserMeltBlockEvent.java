package resonant.api.event;

import net.minecraft.world.World;
import universalelectricity.api.vector.Vector3;

/** @author Darkguardsman */
public class LaserMeltBlockEvent extends LaserEvent
{
    public Object shooter;

    public LaserMeltBlockEvent(World world, Vector3 spot, Vector3 hit, Object shooter)
    {
        super(world, spot, hit);
        this.shooter = shooter;
    }
}