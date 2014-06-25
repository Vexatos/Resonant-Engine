package resonant.api.event;

import net.minecraft.world.World;
import net.minecraftforge.event.Cancelable;
import universalelectricity.api.vector.Vector3;

/** @author Darkguardsman */
@Cancelable
public class LaserMineBlockEvent extends LaserEvent
{
    public Object shooter;

    public LaserMineBlockEvent(World world, Vector3 spot, Vector3 hit, Object shooter)
    {
        super(world, spot, hit);
        this.shooter = shooter;
    }
}