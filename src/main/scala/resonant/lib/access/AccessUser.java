package resonant.lib.access;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import resonant.lib.utility.nbt.ISaveObj;

/** Used to define a users access to a terminal based object.
 * 
 * @author DarkGuardsman */
public class AccessUser extends User implements ISaveObj
{
    protected boolean isTempary = false;
    protected NBTTagCompound extraData;
    protected AccessGroup group;
    private List<String> nodes = new ArrayList<String>();

    public AccessUser(String username)
    {
        super(username);
    }

    public AccessUser(EntityPlayer player)
    {
        super(player.username);
    }

    public AccessGroup getGroup()
    {
        return this.group;
    }

    public AccessUser setGroup(AccessGroup group)
    {
        this.group = group;
        return this;
    }

    public boolean hasNode(String node)
    {
        String tempNode = node.replaceAll(".*", "");
        for (String headNode : nodes)
        {
            if (tempNode.contains(headNode))
            {
                return true;
            }
        }
        return this.nodes.contains(node) || this.group != null && this.group.hasNode(node);
    }

    @Override
    public void save(NBTTagCompound nbt)
    {
        nbt.setString("username", this.username);
        nbt.setCompoundTag("extraData", this.userData());
        NBTTagList usersTag = new NBTTagList();
        for (String str : this.getNodes())
        {
            NBTTagCompound accessData = new NBTTagCompound();
            accessData.setString("name", str);
            usersTag.appendTag(accessData);
        }
        nbt.setTag("nodes", usersTag);
    }

    @Override
    public void load(NBTTagCompound nbt)
    {
        this.username = nbt.getString("username");
        this.extraData = nbt.getCompoundTag("extraData");
        NBTTagList userList = nbt.getTagList("nodes");
        this.getNodes().clear();
        for (int i = 0; i < userList.tagCount(); ++i)
        {
            this.getNodes().add(((NBTTagCompound) userList.tagAt(i)).getString("name"));
        }
    }

    public static AccessUser loadFromNBT(NBTTagCompound nbt)
    {
        AccessUser user = new AccessUser("");
        user.load(nbt);
        return user;
    }

    public AccessUser setTempary(boolean si)
    {
        this.isTempary = si;
        return this;
    }

    /** Used to add other data to the user */
    public NBTTagCompound userData()
    {
        if (this.extraData == null)
        {
            this.extraData = new NBTTagCompound();
        }
        return this.extraData;
    }

    public List<String> getNodes()
    {
        return nodes;
    }

    public void setNodes(List<String> nodes)
    {
        this.nodes = nodes;
    }

    @Override
    public String toString()
    {
        return "User: " + this.username + " Group: " + (this.getGroup() != null ? this.getGroup().getName() : " null");
    }

}
