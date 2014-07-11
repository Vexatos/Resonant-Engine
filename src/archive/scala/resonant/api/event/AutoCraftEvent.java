package resonant.api.event;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import resonant.lib.utility.inventory.AutoCraftingManager.IAutoCrafter;
import universalelectricity.core.transform.vector.Vector3;

/**
 * Events called when an automated crafter is working on crafting an item
 *
 * @author DarkGuardsman
 */
public class AutoCraftEvent extends Event
{
	World world;
	Vector3 spot;
	IAutoCrafter crafter;
	ItemStack craftingResult;

	public AutoCraftEvent(World world, Vector3 spot, IAutoCrafter craft, ItemStack stack)
	{
		this.world = world;
		this.spot = spot;
		this.crafter = craft;
		this.craftingResult = stack;
	}

	@Cancelable
	/** Called before a crafter checks if it can craft. Use this to cancel crafting */
	public static class PreCraft extends AutoCraftEvent
	{
		public PreCraft(World world, Vector3 spot, IAutoCrafter craft, ItemStack stack)
		{
			super(world, spot, craft, stack);
		}
	}

}