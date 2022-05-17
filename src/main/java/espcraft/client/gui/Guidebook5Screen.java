
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

import espcraft.world.inventory.Guidebook5Menu;

import espcraft.network.Guidebook5ButtonMessage;
import espcraft.network.EspcraftModVariables;

import espcraft.EspcraftMod;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

public class Guidebook5Screen extends AbstractContainerScreen<Guidebook5Menu> {
	private final static HashMap<String, Object> guistate = Guidebook5Menu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;

	public Guidebook5Screen(Guidebook5Menu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 146;
		this.imageHeight = 220;
	}

	private static final ResourceLocation texture = new ResourceLocation("espcraft:textures/guidebook_5.png");

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
		this.font.draw(poseStack, "A bit of advanced info", 16, 14, -16724788);
		this.font.draw(poseStack, "We sincerely thank you", 16, 42, -12829636);
		this.font.draw(poseStack, "for using our mod and", 16, 54, -12829636);
		this.font.draw(poseStack, "ESPcraft " + (EspcraftModVariables.MapVariables.get(world).version) + "", 16, 154, -12829636);
		this.font.draw(poseStack, "Thank you for using", 16, 142, -12829636);
		this.font.draw(poseStack, "Some Debug info:", 16, 86, -12829636);
		this.font.draw(poseStack, "[" + (EspcraftModVariables.MapVariables.get(world).UUID) + "]", 16, 98, -65536);
		this.font.draw(poseStack, "and a thanks to you!", 16, 26, -16724788);
		this.font.draw(poseStack, "[" + (EspcraftModVariables.MapVariables.get(world).Channel) + "]", 16, 110, -65536);
		this.font.draw(poseStack, "[" + (EspcraftModVariables.MapVariables.get(world).Power) + "]", 16, 122, -65536);
		this.font.draw(poseStack, "we hope you like it.", 16, 66, -12829636);
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
		this.addRenderableWidget(new Button(this.leftPos + 24, this.topPos + 182, 97, 20, new TextComponent("Turn to page 4"), e -> {
			if (true) {
				EspcraftMod.PACKET_HANDLER.sendToServer(new Guidebook5ButtonMessage(0, x, y, z));
				Guidebook5ButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}));
	}
}
