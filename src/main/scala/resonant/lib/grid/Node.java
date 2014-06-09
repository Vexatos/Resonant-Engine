package resonant.lib.grid;

import java.util.AbstractMap;
import java.util.WeakHashMap;

import resonant.api.grid.INode;
import resonant.api.grid.INodeProvider;
import resonant.lib.utility.nbt.ISaveObj;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;

@SuppressWarnings("rawtypes")
public abstract class Node<P extends INodeProvider, G extends Grid, N> implements INode, ISaveObj
{
    public final P parent;

    protected final AbstractMap<N, ForgeDirection> connections = new WeakHashMap<N, ForgeDirection>();

    public G grid = null;

    public Node(P parent)
    {
        this.parent = parent;
    }

    public final G getGrid()
    {
        if (grid == null)
            grid = newGrid();

        return grid;
    }

    protected abstract G newGrid();

    public final void setGrid(G grid)
    {
        this.grid = grid;
    }

    @Override
    public void update(float deltaTime)
    {

    }

    /** TODO: Try inject tile validate and invalidate events so this does not have to be called. This
     * constructs the node. It should be called whenever the connections of the node are updated OR
     * when the node is first initiated and can access its connections. */
    @Override
    public void reconstruct()
    {
        recache();
        getGrid().add(this);
        getGrid().reconstruct();
    }

    @Override
    public void deconstruct()
    {
        synchronized (connections)
        {
            /** Remove self from all connections. */
            for (N connection : connections.keySet())
            {
                if (getGrid().isValidNode(connection))
                {
                    ((Node) connection).getConnections().remove(this);
                }
            }

            getGrid().remove(this);
            getGrid().deconstruct();
        }
    }

    /** Called for a node to recache all its connections. */
    @Override
    public final void recache()
    {
        try
        {
            doRecache();
        }
        catch (Exception e)
        {
            System.out.println("Node: " + this + " failed to rebuild its connection list and/or cache of values");
            e.printStackTrace();
        }
    }

    protected void doRecache()
    {

    }

    /** All the connections we are connected to. Note that this may not be thread safe for
     * modification unless synchronized.
     * 
     * @return Returns all the connections in this node. */
    public AbstractMap<N, ForgeDirection> getConnections()
    {
        return connections;
    }

    /** Can this node connect with the source?
     * 
     * @param from - Direction coming from.
     * @param source - Object trying to connect with this node. This should either extend Node or be
     * an object that can interface with the node. */
    public boolean canConnect(ForgeDirection from, Object source)
    {
        return false;
    }

    /** Must be called to load the node's data. */
    public void load(NBTTagCompound nbt)
    {

    }

    /** Must be called to save the node's data. */
    public void save(NBTTagCompound nbt)
    {

    }

    @Override
    public String toString()
    {
        return getClass().getSimpleName() + "[" + hashCode() + ", Connections: " + connections.size() + ", Grid:" + getGrid() + "]";
    }
}
