package eu.byteexception.labymod.internal.listener;

import eu.byteexception.labymod.gui.modules.VanishModule.VanishSetting;
import eu.byteexception.labymod.internal.events.VanishModeUpdateEvent;
import net.labymod.api.event.Subscribe;

public class VanishModeUpdateListener {

    @Subscribe
    public void onVanishModeUpdate(VanishModeUpdateEvent event) {
        VanishSetting.vanishMode = event.getState();
    }
}