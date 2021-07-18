package eu.byteexception.labymod.gui.modules;

import net.labymod.settings.elements.ControlElement;
import net.labymod.settings.elements.ControlElement.IconData;
import net.labymod.utils.Material;
import net.labymod.utils.ModColor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;

import java.util.Objects;

public class GlowModule extends LabyModule {

    @Override
    public Class<?> getListener() {
        return null;
    }

    @Override
    public String getDisplayName() {
        return "Glow";
    }

    @Override
    public String getDisplayValue() {
        ClientPlayerEntity clientPlayerEntity = Minecraft.getInstance().player;

        return Objects.nonNull(clientPlayerEntity) && clientPlayerEntity.isGlowing() ? ModColor.GREEN + "An" : ModColor.RED + "Aus";
    }

    @Override
    public IconData getIconData() {
        return new ControlElement.IconData(Material.BEACON);
    }

    @Override
    public String getControlName() {
        return "Glow";
    }

    @Override
    public String getSettingName() {
        return "glow_state";
    }

    @Override
    public int getSortingId() {
        return 1;
    }

}