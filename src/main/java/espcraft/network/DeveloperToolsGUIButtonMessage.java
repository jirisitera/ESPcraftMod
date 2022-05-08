
package espcraft.network;

import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import java.util.function.Supplier;
import java.util.HashMap;

import espcraft.world.inventory.DeveloperToolsGUIMenu;

import espcraft.procedures.KitRedstoneProcedure;
import espcraft.procedures.KitEquipmentProcedure;
import espcraft.procedures.KitDecorationProcedure;
import espcraft.procedures.KitBuildingProcedure;
import espcraft.procedures.GamemodeSurvivalProcedure;
import espcraft.procedures.GamemodeSpectatorProcedure;
import espcraft.procedures.GamemodeCreativeProcedure;
import espcraft.procedures.GamemodeAdventureProcedure;

import espcraft.EspcraftMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DeveloperToolsGUIButtonMessage {
	private final int buttonID, x, y, z;

	public DeveloperToolsGUIButtonMessage(FriendlyByteBuf buffer) {
		this.buttonID = buffer.readInt();
		this.x = buffer.readInt();
		this.y = buffer.readInt();
		this.z = buffer.readInt();
	}

	public DeveloperToolsGUIButtonMessage(int buttonID, int x, int y, int z) {
		this.buttonID = buttonID;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public static void buffer(DeveloperToolsGUIButtonMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}

	public static void handler(DeveloperToolsGUIButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			Player entity = context.getSender();
			int buttonID = message.buttonID;
			int x = message.x;
			int y = message.y;
			int z = message.z;
			handleButtonAction(entity, buttonID, x, y, z);
		});
		context.setPacketHandled(true);
	}

	public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z) {
		Level world = entity.level;
		HashMap guistate = DeveloperToolsGUIMenu.guistate;
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {

			KitBuildingProcedure.execute(entity);
		}
		if (buttonID == 1) {

			KitDecorationProcedure.execute(entity);
		}
		if (buttonID == 2) {

			KitRedstoneProcedure.execute(entity);
		}
		if (buttonID == 3) {

			KitEquipmentProcedure.execute(entity);
		}
		if (buttonID == 4) {

			GamemodeSurvivalProcedure.execute(entity);
		}
		if (buttonID == 5) {

			GamemodeCreativeProcedure.execute(entity);
		}
		if (buttonID == 6) {

			GamemodeAdventureProcedure.execute(entity);
		}
		if (buttonID == 7) {

			GamemodeSpectatorProcedure.execute(entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		EspcraftMod.addNetworkMessage(DeveloperToolsGUIButtonMessage.class, DeveloperToolsGUIButtonMessage::buffer,
				DeveloperToolsGUIButtonMessage::new, DeveloperToolsGUIButtonMessage::handler);
	}
}
