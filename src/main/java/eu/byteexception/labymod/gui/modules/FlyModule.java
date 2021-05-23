package eu.byteexception.labymod.gui.modules;

import eu.byteexception.labymod.internal.listener.FlyModeUpdateListener;
import eu.byteexception.labymod.settings.PlayerSettings;
import net.labymod.core.LabyModCore;
import net.labymod.settings.elements.ControlElement;
import net.labymod.settings.elements.ControlElement.IconData;
import net.labymod.utils.Material;
import net.labymod.utils.ModColor;

public class FlyModule extends LabyModule {

    @Override
    public String getListenerName() {
        return FlyModeUpdateListener.class.getCanonicalName();
    }

    @Override
    public String getDisplayName() {
        return "Fly";
    }

    @Override
    public boolean isShown() {
        return super.isShown() && !LabyModCore.getMinecraft().getPlayer().isCreative() && !LabyModCore.getMinecraft().getPlayer().isSpectator();
    }

    @Override
    public String getDisplayValue() {
        return PlayerSettings.getFlyMode() ? ModColor.GREEN + "An" : ModColor.RED + "Aus";
    }

    @Override
    public IconData getIconData() {
        return new ControlElement.IconData(Material.ELYTRA);
    }

    @Override
    public String getControlName() {
        return "Fly";
    }

    @Override
    public String getSettingName() {
        return "fly_state";
    }

    @Override
    public int getSortingId() {
        return 0;
    }

}