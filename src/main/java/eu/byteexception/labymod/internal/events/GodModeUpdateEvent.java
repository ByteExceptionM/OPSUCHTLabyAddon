package eu.byteexception.labymod.internal.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.labymod.api.event.Event;

@AllArgsConstructor
@Getter
public class GodModeUpdateEvent implements Event {

    private final Boolean state;

}
