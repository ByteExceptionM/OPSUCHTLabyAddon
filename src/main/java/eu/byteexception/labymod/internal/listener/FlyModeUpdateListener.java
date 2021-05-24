package eu.byteexception.labymod.internal.listener;

import eu.byteexception.labymod.internal.events.FlyModeUpdateEvent;
import eu.byteexception.labymod.settings.PlayerSettings;
import net.labymod.api.event.Subscribe;

public class FlyModeUpdateListener {

    @Subscribe
    public void onFlyModeUpdate(FlyModeUpdateEvent event) {
        PlayerSettings.setFlyMode(event.getState());
    }
}