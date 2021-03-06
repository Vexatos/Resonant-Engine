package resonant.core.content;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import resonant.core.ResonantEngine;
import resonant.lib.References;

public class ItemCircuit extends ItemBase
{
    public static final String[] TYPES = { "circuitBasic", "circuitAdvanced", "circuitElite" };

    public ItemCircuit(int id, int texture)
    {
        super("circuit", id);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int damage)
    {
        return damage;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return "item." + References.PREFIX + TYPES[itemStack.getItemDamage()];
    }

    @Override
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List list)
    {
        for (int i = 0; i < TYPES.length; i++)
        {
            list.add(new ItemStack(this, 1, i));
        }
    }
}
