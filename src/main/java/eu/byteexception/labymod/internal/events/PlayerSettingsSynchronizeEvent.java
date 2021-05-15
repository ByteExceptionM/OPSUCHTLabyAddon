package eu.byteexception.labymod.internal.events;

import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.labymod.api.event.Event;

@AllArgsConstructor
@Getter
public class PlayerSettingsSynchronizeEvent implements Event {

    private final JsonObject playerSettings;

}
