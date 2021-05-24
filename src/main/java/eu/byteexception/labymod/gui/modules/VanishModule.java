package eu.byteexception.labymod.gui.modules;

import eu.byteexception.labymod.internal.listener.VanishModeUpdateListener;
import eu.byteexception.labymod.settings.PlayerSettings;
import net.labymod.settings.elements.ControlElement.IconData;
import net.labymod.utils.Material;
import net.labymod.utils.ModColor;

public class VanishModule extends LabyModule {

    @Override
    public String getListenerName() {
        return VanishModeUpdateListener.class.getCanonicalName();
    }

    @Override
    public String getDisplayName() {
        return "Vanish";
    }

    @Override
    public String getDisplayValue() {
        return PlayerSettings.getVanishMode() ? ModColor.GREEN + "An" : ModColor.RED + "Aus";
    }

    @Override
    public IconData getIconData() {
        return new IconData(Material.SPLASH_POTION);
    }

    @Override
    public String getControlName() {
        return "Vanish";
    }

    @Override
    public String getSettingName() {
        return "vanish_state";
    }

    @Override
    public int getSortingId() {
        return 2;
    }

}