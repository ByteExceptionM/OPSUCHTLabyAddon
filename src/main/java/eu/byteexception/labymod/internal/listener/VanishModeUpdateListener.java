package eu.byteexception.labymod.internal.listener;

import eu.byteexception.labymod.internal.events.VanishModeUpdateEvent;
import eu.byteexception.labymod.settings.PlayerSettings;
import net.labymod.api.event.Subscribe;

public class VanishModeUpdateListener {

    @Subscribe
    public void onVanishModeUpdate(VanishModeUpdateEvent event) {
        PlayerSettings.setVanishMode(event.getState());
    }
}