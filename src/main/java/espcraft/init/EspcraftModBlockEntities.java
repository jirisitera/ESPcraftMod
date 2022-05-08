
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package espcraft.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.Block;

import espcraft.block.entity.EspblockonBlockEntity;
import espcraft.block.entity.EspblockoffBlockEntity;
import espcraft.block.entity.BlocktransmitteronBlockEntity;
import espcraft.block.entity.BlocktransmitteroffBlockEntity;
import espcraft.block.entity.BlockreceiveronBlockEntity;
import espcraft.block.entity.BlockreceiveroffBlockEntity;

import espcraft.EspcraftMod;

public class EspcraftModBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, EspcraftMod.MODID);
	public static final RegistryObject<BlockEntityType<?>> ESPBLOCKOFF = register("espblockoff", EspcraftModBlocks.ESPBLOCKOFF,
			EspblockoffBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> BLOCKTRANSMITTERON = register("blocktransmitteron", EspcraftModBlocks.BLOCKTRANSMITTERON,
			BlocktransmitteronBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> BLOCKTRANSMITTEROFF = register("blocktransmitteroff",
			EspcraftModBlocks.BLOCKTRANSMITTEROFF, BlocktransmitteroffBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> BLOCKRECEIVERON = register("blockreceiveron", EspcraftModBlocks.BLOCKRECEIVERON,
			BlockreceiveronBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> BLOCKRECEIVEROFF = register("blockreceiveroff", EspcraftModBlocks.BLOCKRECEIVEROFF,
			BlockreceiveroffBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> ESPBLOCKON = register("espblockon", EspcraftModBlocks.ESPBLOCKON,
			EspblockonBlockEntity::new);

	private static RegistryObject<BlockEntityType<?>> register(String registryname, RegistryObject<Block> block,
			BlockEntityType.BlockEntitySupplier<?> supplier) {
		return REGISTRY.register(registryname, () -> BlockEntityType.Builder.of(supplier, block.get()).build(null));
	}
}
