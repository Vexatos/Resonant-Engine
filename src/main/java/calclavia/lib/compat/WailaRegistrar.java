package calclavia.lib.compat;

import calclavia.lib.content.module.prefab.TileElectrical;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;

/**
 * @since 21/03/14
 * @author tgame14
 */
public class WailaRegistrar
{
    public static void WailaRegistry(IWailaRegistrar registrar)
    {
        registrar.registerBodyProvider((IWailaDataProvider) new TileElectrical(), TileElectrical.class);
    }
}
