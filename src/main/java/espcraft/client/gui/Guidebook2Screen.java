
package espcraft.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.Minecraft;

import java.util.HashMap;

import espcraft.world.inventory.Guidebook2Menu;

import espcraft.network.Guidebook2ButtonMessage;

import espcraft.EspcraftMod;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

public class Guidebook2Screen extends AbstractContainerScreen<Guidebook2Menu> {
	private final static HashMap<String, Object> guistate = Guidebook2Menu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;

	public Guidebook2Screen(Guidebook2Menu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 146;
		this.imageHeight = 220;
	}

	private static final ResourceLocation texture = new ResourceLocation("espcraft:textures/guidebook_2.png");

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

		RenderSystem.setShaderTexture(0, new ResourceLocation("espcraft:textures/espblock.png"));
		this.blit(ms, this.leftPos + 116, this.topPos + 10, 0, 0, 16, 16, 16, 16);

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
		this.font.draw(poseStack, "Meet your ESPblock", 16, 14, -16724788);
		this.font.draw(poseStack, "The ESPblock is the", 16, 30, -12829636);
		this.font.draw(poseStack, "ultimate connection", 16, 42, -12829636);
		this.font.draw(poseStack, "plug between Minecraft", 16, 54, -12829636);
		this.font.draw(poseStack, "and your ESP board.", 16, 66, -12829636);
		this.font.draw(poseStack, "How do you set it up?", 16, 86, -12829636);
		this.font.draw(poseStack, "First, you need to be", 16, 106, -12829636);
		this.font.draw(poseStack, "on your ESPblock and", 16, 118, -12829636);
		this.font.draw(poseStack, "use these commands:", 16, 130, -12829636);
		this.font.draw(poseStack, "/uuid [your ESP uuid]", 16, 142, -3407872);
		this.font.draw(poseStack, "/channel [sensor id]", 16, 154, -3407872);
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
		this.addRenderableWidget(new Button(this.leftPos + 76, this.topPos + 182, 56, 20, new TextComponent("Next"), e -> {
			if (true) {
				EspcraftMod.PACKET_HANDLER.sendToServer(new Guidebook2ButtonMessage(0, x, y, z));
				Guidebook2ButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}));
		this.addRenderableWidget(new Button(this.leftPos + 12, this.topPos + 182, 56, 20, new TextComponent("Previous"), e -> {
			if (true) {
				EspcraftMod.PACKET_HANDLER.sendToServer(new Guidebook2ButtonMessage(1, x, y, z));
				Guidebook2ButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}));
	}
}
