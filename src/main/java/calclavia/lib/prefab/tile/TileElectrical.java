package calclavia.lib.prefab.tile;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import universalelectricity.api.CompatibilityModule;
import universalelectricity.api.UniversalClass;
import universalelectricity.api.energy.EnergyStorageHandler;
import universalelectricity.api.energy.IEnergyContainer;
import universalelectricity.api.energy.IEnergyInterface;
import universalelectricity.api.vector.Vector3;

@UniversalClass
public class TileElectrical extends TileIO implements IEnergyInterface, IEnergyContainer
{
	public EnergyStorageHandler energy;

	/** Recharges electric item. */
	public void recharge(ItemStack itemStack)
	{
		if (this.energy != null)
			this.energy.extractEnergy(CompatibilityModule.chargeItem(itemStack, this.energy.getEnergy(), true), true);
	}

	/** Discharges electric item. */
	public void discharge(ItemStack itemStack)
	{
		if (this.energy != null)
			this.energy.receiveEnergy(CompatibilityModule.dischargeItem(itemStack, this.energy.getEmptySpace(), true), true);
	}

	@Override
	public boolean canConnect(ForgeDirection direction)
	{
		if (direction == null || direction.equals(ForgeDirection.UNKNOWN))
		{
			return false;
		}

		return this.getInputDirections().contains(direction) || this.getOutputDirections().contains(direction);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		if (this.energy != null)
			this.energy.readFromNBT(nbt);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		if (this.energy != null)
			this.energy.writeToNBT(nbt);
	}

	@Override
	public long getEnergy(ForgeDirection from)
	{
		if (this.energy != null)
			return this.energy.getEnergy();
		else
			return 0;
	}

	@Override
	public long getEnergyCapacity(ForgeDirection from)
	{
		if (this.energy != null)
			return this.energy.getEnergyCapacity();
		else
			return 0;
	}

	@Override
	public long onReceiveEnergy(ForgeDirection from, long receive, boolean doReceive)
	{
		if (this.energy != null && (from == ForgeDirection.UNKNOWN || this.getInputDirections().contains(from)))
		{
			return this.energy.receiveEnergy(receive, doReceive);
		}

		return 0;
	}

	@Override
	public long onExtractEnergy(ForgeDirection from, long extract, boolean doExtract)
	{
		if (this.energy != null && (from == ForgeDirection.UNKNOWN || this.getOutputDirections().contains(from)))
		{
			return this.energy.extractEnergy(extract, doExtract);
		}

		return 0;
	}

	@Override
	public void setEnergy(ForgeDirection from, long energy)
	{
		this.energy.setEnergy(energy);
	}

	protected long produce(long outputEnergy)
	{
		long usedEnergy = 0;

		for (ForgeDirection direction : this.getOutputDirections())
		{
			if (outputEnergy > 0)
			{
				TileEntity tileEntity = new Vector3(this).translate(direction).getTileEntity(this.worldObj);

				if (tileEntity != null)
				{
					usedEnergy += CompatibilityModule.receiveEnergy(tileEntity, direction.getOpposite(), outputEnergy, true);
				}
			}
		}

		return usedEnergy;
	}

	protected long produce()
	{
		long totalUsed = 0;

		for (ForgeDirection direction : this.getOutputDirections())
		{
			if (this.energy.getEnergy() > 0)
			{
				TileEntity tileEntity = new Vector3(this).translate(direction).getTileEntity(this.worldObj);

				if (tileEntity != null)
				{
					long used = CompatibilityModule.receiveEnergy(tileEntity, direction.getOpposite(), this.energy.extractEnergy(this.energy.getEnergy(), false), true);
					totalUsed += this.energy.extractEnergy(used, true);
				}
			}
		}

		return totalUsed;
	}
}