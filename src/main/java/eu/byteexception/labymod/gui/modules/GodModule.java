package eu.byteexception.labymod.gui.modules;

import eu.byteexception.labymod.internal.listener.GodModeUpdateListener;
import eu.byteexception.labymod.settings.PlayerSettings;
import net.labymod.core.LabyModCore;
import net.labymod.settings.elements.ControlElement.IconData;
import net.labymod.utils.Material;
import net.labymod.utils.ModColor;

public class GodModule extends LabyModule {

    @Override
    public String getDisplayName() {
        return "GodMode";
    }

    @Override
    public boolean isShown() {
        return super.isShown() && !LabyModCore.getMinecraft().getPlayer().isCreative() && !LabyModCore.getMinecraft().getPlayer().isSpectator();
    }

    @Override
    public String getDisplayValue() {
        return PlayerSettings.getGodMode() ? ModColor.cl('a') + "An" : ModColor.cl('c') + "Aus";
    }

    @Override
    public IconData getIconData() {
        return new IconData(Material.DIAMOND_CHESTPLATE);
    }

    @Override
    public String getControlName() {
        return "GodMode-Status";
    }

    @Override
    public String getSettingName() {
        return "god_mode_state";
    }

    @Override
    public int getSortingId() {
        return 3;
    }

    @Override
    public String getListenerName() {
        return GodModeUpdateListener.class.getCanonicalName();
    }

}