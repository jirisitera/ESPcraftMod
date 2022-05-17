
package espcraft.client.gui;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.Minecraft;

import java.util.HashMap;

import espcraft.world.inventory.EspguinormalMenu;

import espcraft.network.EspguinormalButtonMessage;
import espcraft.network.EspcraftModVariables;

import espcraft.EspcraftMod;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

public class EspguinormalScreen extends AbstractContainerScreen<EspguinormalMenu> {
	private final static HashMap<String, Object> guistate = EspguinormalMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;

	public EspguinormalScreen(EspguinormalMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 275;
		this.imageHeight = 150;
	}

	private static final ResourceLocation texture = new ResourceLocation("espcraft:textures/espguinormal.png");

	@Override
	public void render(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderTooltip(ms, mouseX, mouseY);
	}

	@Override
	protected void renderBg(PoseStack ms, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.setShaderTexture(0, texture);
		this.blit(ms, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);

		RenderSystem.setShaderTexture(0, new ResourceLocation("espcraft:textures/nvias_logo_new_small.png"));
		this.blit(ms, this.leftPos + 15, this.topPos + 8, 0, 0, 50, 50, 50, 50);

		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
	}

	@Override
	protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
		this.font.draw(poseStack, "ESPcraft " + (EspcraftModVariables.version) + "", 185, 133, -12829636);
		this.font.draw(poseStack, "Mode: " + (new Object() {
			public String getValue(BlockPos pos, String tag) {
				BlockEntity BlockEntity = world.getBlockEntity(pos);
				if (BlockEntity != null)
					return BlockEntity.getTileData().getString(tag);
				return "";
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "Mode")) + "", 10, 133, -12829636);
		this.font.draw(poseStack, "ESPblock location: " + x + " " + y + " " + z + "", 75, 33, -12829636);
		this.font.draw(poseStack, "ESPblock Settings", 95, 13, -16711732);
		this.font.draw(poseStack, "UUID: " + (new Object() {
			public String getValue(BlockPos pos, String tag) {
				BlockEntity BlockEntity = world.getBlockEntity(pos);
				if (BlockEntity != null)
					return BlockEntity.getTileData().getString(tag);
				return "";
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "Name")) + "", 10, 103, -12829636);
		this.font.draw(poseStack, "Channel: " + (new Object() {
			public String getValue(BlockPos pos, String tag) {
				BlockEntity BlockEntity = world.getBlockEntity(pos);
				if (BlockEntity != null)
					return BlockEntity.getTileData().getString(tag);
				return "";
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "Channel")) + "", 10, 118, -12829636);
	}

	@Override
	public void onClose() {
		super.onClose();
		Minecraft.getInstance().keyboardHandler.setSendRepeatsToGui(false);
	}

	@Override
	public void init() {
		super.init();
		this.minecraft.keyboardHandler.setSendRepeatsToGui(true);
		this.addRenderableWidget(new Button(this.leftPos + 140, this.topPos + 68, 120, 20, new TextComponent("Turn to Transmitter"), e -> {
			if (true) {
				EspcraftMod.PACKET_HANDLER.sendToServer(new EspguinormalButtonMessage(0, x, y, z));
				EspguinormalButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}));
		this.addRenderableWidget(new Button(this.leftPos + 10, this.topPos + 68, 120, 20, new TextComponent("Turn to Receiver"), e -> {
			if (true) {
				EspcraftMod.PACKET_HANDLER.sendToServer(new EspguinormalButtonMessage(1, x, y, z));
				EspguinormalButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}));
	}
}
