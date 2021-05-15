package eu.byteexception.labymod.internal.listener;

import eu.byteexception.labymod.gui.modules.FlyModule.FlySetting;
import eu.byteexception.labymod.internal.events.FlyModeUpdateEvent;
import net.labymod.api.event.Subscribe;

public class FlyModeUpdateListener {

    @Subscribe
    public void onFlyModeUpdate(FlyModeUpdateEvent event) {
        FlySetting.flyMode = event.getState();
    }
}