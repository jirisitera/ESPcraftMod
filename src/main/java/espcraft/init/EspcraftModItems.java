
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package espcraft.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.BlockItem;

import espcraft.EspcraftMod;

public class EspcraftModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, EspcraftMod.MODID);
	public static final RegistryObject<Item> ESPBLOCKOFF = block(EspcraftModBlocks.ESPBLOCKOFF, CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<Item> BLOCKTRANSMITTERON = block(EspcraftModBlocks.BLOCKTRANSMITTERON, null);
	public static final RegistryObject<Item> BLOCKTRANSMITTEROFF = block(EspcraftModBlocks.BLOCKTRANSMITTEROFF, null);
	public static final RegistryObject<Item> BLOCKRECEIVERON = block(EspcraftModBlocks.BLOCKRECEIVERON, null);
	public static final RegistryObject<Item> BLOCKRECEIVEROFF = block(EspcraftModBlocks.BLOCKRECEIVEROFF, null);
	public static final RegistryObject<Item> ESPBLOCKON = block(EspcraftModBlocks.ESPBLOCKON, null);

	private static RegistryObject<Item> block(RegistryObject<Block> block, CreativeModeTab tab) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
	}
}
