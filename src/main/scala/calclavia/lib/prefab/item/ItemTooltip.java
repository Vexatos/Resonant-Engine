package calclavia.lib.prefab.item;

import calclavia.lib.render.EnumColor;
import calclavia.lib.utility.LanguageUtility;
import calclavia.lib.utility.TooltipUtility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.lwjgl.input.Keyboard;

import java.util.List;

/**
 * extend this instead of Item, gives the basis to Special Tooltip behaviour
 *
 * @author tgame14
 * @since 06/04/14
 */
public abstract class ItemTooltip extends Item
{

	public ItemTooltip(int id)
	{
		super(id);
	}

	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean par4)
	{
		String tooltip = LanguageUtility.getLocal(getUnlocalizedName(itemStack) + ".tooltip");

		if (tooltip != null && tooltip.length() > 0)
		{
			if (!Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
			{
				list.add(LanguageUtility.getLocal("tooltip.noShift").replace("%0", EnumColor.AQUA.toString()).replace("%1", EnumColor.GREY.toString()));
			}
			else
			{
				list.addAll(LanguageUtility.splitStringPerWord(tooltip, 5));
			}
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_J))
		{
			TooltipUtility.addTooltip(itemStack, list);
		}
		else
		{
			list.add(LanguageUtility.getLocal("info.recipes.tooltip").replace("%0", EnumColor.AQUA.toString()).replace("%1", EnumColor.GREY.toString()));
		}
	}
}