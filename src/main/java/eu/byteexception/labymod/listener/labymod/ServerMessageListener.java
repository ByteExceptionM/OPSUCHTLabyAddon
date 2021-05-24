package eu.byteexception.labymod.listener.labymod;

import com.google.gson.JsonObject;
import eu.byteexception.labymod.OPSuchtLabyAddon;
import eu.byteexception.labymod.internal.events.FlyModeUpdateEvent;
import eu.byteexception.labymod.internal.events.GodModeUpdateEvent;
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
        JsonObject jsonObject = event.getServerMessage().getAsJsonObject();

        switch (event.getMessageKey()) {

            case "opsucht_settings": {
                this.addon.fireEvent(new PlayerSettingsSynchronizeEvent(jsonObject));
                break;
            }

            case "opsucht_fly_mode": {
                this.addon.fireEvent(new FlyModeUpdateEvent(jsonObject.get("value").getAsBoolean()));
                break;
            }

            case "opsucht_vanish_mode": {
                this.addon.fireEvent(new VanishModeUpdateEvent(jsonObject.get("value").getAsBoolean()));
                break;
            }

            case "opsucht_god_mode": {
                this.addon.fireEvent(new GodModeUpdateEvent(jsonObject.get("value").getAsBoolean()));
                break;
            }

            default: break;

        }
    }

}
