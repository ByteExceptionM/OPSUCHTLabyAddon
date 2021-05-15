package eu.byteexception.labymod.gui.modules;

import eu.byteexception.labymod.internal.listener.GodModeUpdateListener;
import net.labymod.settings.elements.ControlElement.IconData;
import net.labymod.utils.Material;
import net.labymod.utils.ModColor;

public class GodModule extends ILabyModule {

    @Override
    public String getDisplayName() {
        return "GodMode";
    }

    @Override
    public String getDisplayValue() {
        return GodModeSettings.godMode ? ModColor.cl('a') + "An" : ModColor.cl('c') + "Aus";
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

    public static class GodModeSettings {

        public static Boolean godMode = false;

    }

}
