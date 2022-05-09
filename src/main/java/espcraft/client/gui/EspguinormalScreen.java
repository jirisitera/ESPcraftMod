
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
import net.minecraft.client.gui.components.EditBox;
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
	EditBox name;
	EditBox channel;

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
		name.render(ms, mouseX, mouseY, partialTicks);
		channel.render(ms, mouseX, mouseY, partialTicks);
	}

	@Override
	protected void renderBg(PoseStack ms, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.setShaderTexture(0, texture);
		this.blit(ms, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		if (name.isFocused())
			return name.keyPressed(key, b, c);
		if (channel.isFocused())
			return channel.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
		name.tick();
		channel.tick();
	}

	@Override
	protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
		this.font.draw(poseStack, "ESPcraft " + (EspcraftModVariables.MapVariables.get(world).version) + "", 185, 133, -12829636);
		this.font.draw(poseStack, "Mode: " + (new Object() {
			public String getValue(BlockPos pos, String tag) {
				BlockEntity BlockEntity = world.getBlockEntity(pos);
				if (BlockEntity != null)
					return BlockEntity.getTileData().getString(tag);
				return "";
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "Mode")) + " / " + (new Object() {
			public String getValue(BlockPos pos, String tag) {
				BlockEntity BlockEntity = world.getBlockEntity(pos);
				if (BlockEntity != null)
					return BlockEntity.getTileData().getString(tag);
				return "";
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "Name")) + " / " + (new Object() {
			public String getValue(BlockPos pos, String tag) {
				BlockEntity BlockEntity = world.getBlockEntity(pos);
				if (BlockEntity != null)
					return BlockEntity.getTileData().getString(tag);
				return "";
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "Channel")) + "", 5, 133, -12829636);
		this.font.draw(poseStack, "ESPblock location: " + x + " " + y + " " + z + "", 65, 23, -12829636);
		this.font.draw(poseStack, "ESPblock Settings", 95, 8, -12829636);
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
		name = new EditBox(this.font, this.leftPos + 30, this.topPos + 58, 60, 20, new TextComponent("UUID")) {
			{
				setSuggestion("UUID");
			}

			@Override
			public void insertText(String text) {
				super.insertText(text);
				if (getValue().isEmpty())
					setSuggestion("UUID");
				else
					setSuggestion(null);
			}

			@Override
			public void moveCursorTo(int pos) {
				super.moveCursorTo(pos);
				if (getValue().isEmpty())
					setSuggestion("UUID");
				else
					setSuggestion(null);
			}
		};
		guistate.put("text:name", name);
		name.setMaxLength(32767);
		this.addWidget(this.name);
		channel = new EditBox(this.font, this.leftPos + 95, this.topPos + 58, 60, 20, new TextComponent("Channel")) {
			{
				setSuggestion("Channel");
			}

			@Override
			public void insertText(String text) {
				super.insertText(text);
				if (getValue().isEmpty())
					setSuggestion("Channel");
				else
					setSuggestion(null);
			}

			@Override
			public void moveCursorTo(int pos) {
				super.moveCursorTo(pos);
				if (getValue().isEmpty())
					setSuggestion("Channel");
				else
					setSuggestion(null);
			}
		};
		guistate.put("text:channel", channel);
		channel.setMaxLength(32767);
		this.addWidget(this.channel);
		this.addRenderableWidget(new Button(this.leftPos + 140, this.topPos + 93, 120, 20, new TextComponent("Turn to Transmitter"), e -> {
			if (true) {
				EspcraftMod.PACKET_HANDLER.sendToServer(new EspguinormalButtonMessage(0, x, y, z));
				EspguinormalButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}));
		this.addRenderableWidget(new Button(this.leftPos + 10, this.topPos + 93, 120, 20, new TextComponent("Turn to Receiver"), e -> {
			if (true) {
				EspcraftMod.PACKET_HANDLER.sendToServer(new EspguinormalButtonMessage(1, x, y, z));
				EspguinormalButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}));
		this.addRenderableWidget(new Button(this.leftPos + 165, this.topPos + 58, 75, 20, new TextComponent("Set Values"), e -> {
			if (true) {
				EspcraftMod.PACKET_HANDLER.sendToServer(new EspguinormalButtonMessage(2, x, y, z));
				EspguinormalButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		}));
	}
}
