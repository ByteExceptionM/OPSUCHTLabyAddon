package eu.byteexception.labymod.internal.listener;

import eu.byteexception.labymod.internal.events.GodModeUpdateEvent;
import eu.byteexception.labymod.settings.PlayerSettings;
import net.labymod.api.event.Subscribe;

public class GodModeUpdateListener {

    @Subscribe
    public void onGodModeUpdate(GodModeUpdateEvent event) {
        PlayerSettings.setGodMode(event.getState());
    }
}