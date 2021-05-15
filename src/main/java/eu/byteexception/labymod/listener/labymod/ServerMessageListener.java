package eu.byteexception.labymod.listener.labymod;

import com.google.gson.JsonObject;
import eu.byteexception.labymod.OPSuchtLabyAddon;
import eu.byteexception.labymod.internal.events.FlyModeUpdateEvent;
import eu.byteexception.labymod.internal.events.PlayerSettingsSynchronizeEvent;
import eu.byteexception.labymod.internal.events.VanishModeUpdateEvent;
import lombok.AllArgsConstructor;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.network.ServerMessageEvent;

@AllArgsConstructor
public class ServerMessageListener {

    private final OPSuchtLabyAddon addon;

    @Subscribe
    public void onServerMessage(ServerMessageEvent event) {
        this.addon.getLogger().info(event.getMessageKey());

        JsonObject jsonObject = event.getServerMessage().getAsJsonObject();

        if (event.getMessageKey().equals("opsucht_fly_mode")) {
            this.addon.getApi().getEventService().fireEvent(new FlyModeUpdateEvent(jsonObject.get("value").getAsBoolean()));
            return;
        }

        if (event.getMessageKey().equals("opsucht_vanish_mode")) {
            this.addon.getApi().getEventService().fireEvent(new VanishModeUpdateEvent(jsonObject.get("value").getAsBoolean()));
            return;
        }

        if (event.getMessageKey().equals("opsucht_settings")) {
            this.addon.getApi().getEventService().fireEvent(new PlayerSettingsSynchronizeEvent(jsonObject));
            return;
        }
    }

}
