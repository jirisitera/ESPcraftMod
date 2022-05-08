
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package espcraft.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.block.Block;

import espcraft.block.EspblockonBlock;
import espcraft.block.EspblockoffBlock;
import espcraft.block.BlocktransmitteronBlock;
import espcraft.block.BlocktransmitteroffBlock;
import espcraft.block.BlockreceiveronBlock;
import espcraft.block.BlockreceiveroffBlock;

import espcraft.EspcraftMod;

public class EspcraftModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, EspcraftMod.MODID);
	public static final RegistryObject<Block> ESPBLOCKOFF = REGISTRY.register("espblockoff", () -> new EspblockoffBlock());
	public static final RegistryObject<Block> BLOCKTRANSMITTERON = REGISTRY.register("blocktransmitteron", () -> new BlocktransmitteronBlock());
	public static final RegistryObject<Block> BLOCKTRANSMITTEROFF = REGISTRY.register("blocktransmitteroff", () -> new BlocktransmitteroffBlock());
	public static final RegistryObject<Block> BLOCKRECEIVERON = REGISTRY.register("blockreceiveron", () -> new BlockreceiveronBlock());
	public static final RegistryObject<Block> BLOCKRECEIVEROFF = REGISTRY.register("blockreceiveroff", () -> new BlockreceiveroffBlock());
	public static final RegistryObject<Block> ESPBLOCKON = REGISTRY.register("espblockon", () -> new EspblockonBlock());

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
	public static class ClientSideHandler {
		@SubscribeEvent
		public static void clientSetup(FMLClientSetupEvent event) {
			EspblockoffBlock.registerRenderLayer();
			BlocktransmitteronBlock.registerRenderLayer();
			BlocktransmitteroffBlock.registerRenderLayer();
			BlockreceiveronBlock.registerRenderLayer();
			BlockreceiveroffBlock.registerRenderLayer();
			EspblockonBlock.registerRenderLayer();
		}
	}
}
