package me.fortibrine.visualdriver.fabric.hud;

import com.mojang.blaze3d.vertex.PoseStack;
import lombok.Getter;
import me.fortibrine.visualdriver.fabric.event.HudRenderEvents;
import net.minecraft.client.gui.Gui;
import org.apache.logging.log4j.util.TriConsumer;

import java.util.ArrayList;
import java.util.List;

@Getter
public class HudManager implements HudRenderEvents.RenderHud {

    private final List<TriConsumer<Gui, PoseStack, Float>> actions = new ArrayList<>();
    private final List<String> disableRender = new ArrayList<>();

    public HudManager() {
        HudRenderEvents.RENDER_HUD.register(this);

        HudRenderEvents.RENDER_VEHICLE_HEALTH.register(stack -> !disableRender.contains("render_vehicle_health"));
        HudRenderEvents.RENDER_HOTBAR.register((f, stack) -> !disableRender.contains("render_hotbar"));
        HudRenderEvents.RENDER_EXPERIENCE_BAR.register((f, stack) -> !disableRender.contains("render_experience_bar"));
        HudRenderEvents.RENDER_HEALTH.register(stack -> !disableRender.contains("render_health"));
        HudRenderEvents.RENDER_EFFECTS.register(stack -> !disableRender.contains("render_effects"));
        HudRenderEvents.RENDER_CROSSHAIR.register(stack -> !disableRender.contains("render_crosshair"));
        HudRenderEvents.RENDER_SELECTED_ITEM_NAME.register(stack -> !disableRender.contains("render_selected_item_name"));
        HudRenderEvents.RENDER_JUMP_METER.register((stack, i) -> !disableRender.contains("render_jump_meter"));
    }

    @Override
    public void render(Gui context, PoseStack stack, float delta) {
        new ArrayList<>(actions).forEach(action -> action.accept(context, stack, delta));
    }

}
