package espcraft.procedures;

import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.CapabilityItemHandler;

import net.minecraft.item.ItemStack;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import java.util.Map;

import espcraft.EspcraftMod;

public class KitBuildingProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				EspcraftMod.LOGGER.warn("Failed to load dependency entity for procedure KitBuilding!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		{
			final ItemStack _setstack = new ItemStack(Blocks.GRASS_BLOCK);
			final int _sltid = (int) (0);
			_setstack.setCount((int) 1);
			entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
				if (capability instanceof IItemHandlerModifiable) {
					((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
				}
			});
		}
		{
			final ItemStack _setstack = new ItemStack(Blocks.DIRT);
			final int _sltid = (int) (1);
			_setstack.setCount((int) 1);
			entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
				if (capability instanceof IItemHandlerModifiable) {
					((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
				}
			});
		}
		{
			final ItemStack _setstack = new ItemStack(Blocks.STONE);
			final int _sltid = (int) (2);
			_setstack.setCount((int) 1);
			entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
				if (capability instanceof IItemHandlerModifiable) {
					((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
				}
			});
		}
		{
			final ItemStack _setstack = new ItemStack(Blocks.COBBLESTONE);
			final int _sltid = (int) (3);
			_setstack.setCount((int) 1);
			entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
				if (capability instanceof IItemHandlerModifiable) {
					((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
				}
			});
		}
		{
			final ItemStack _setstack = new ItemStack(Blocks.SAND);
			final int _sltid = (int) (4);
			_setstack.setCount((int) 1);
			entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
				if (capability instanceof IItemHandlerModifiable) {
					((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
				}
			});
		}
		{
			final ItemStack _setstack = new ItemStack(Blocks.GLASS);
			final int _sltid = (int) (5);
			_setstack.setCount((int) 1);
			entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
				if (capability instanceof IItemHandlerModifiable) {
					((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
				}
			});
		}
		{
			final ItemStack _setstack = new ItemStack(Blocks.OAK_LOG);
			final int _sltid = (int) (6);
			_setstack.setCount((int) 1);
			entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
				if (capability instanceof IItemHandlerModifiable) {
					((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
				}
			});
		}
		{
			final ItemStack _setstack = new ItemStack(Blocks.OAK_PLANKS);
			final int _sltid = (int) (7);
			_setstack.setCount((int) 1);
			entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
				if (capability instanceof IItemHandlerModifiable) {
					((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
				}
			});
		}
		{
			final ItemStack _setstack = new ItemStack(Blocks.BEDROCK);
			final int _sltid = (int) (8);
			_setstack.setCount((int) 1);
			entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
				if (capability instanceof IItemHandlerModifiable) {
					((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
				}
			});
		}
	}
}
