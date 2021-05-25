package eu.byteexception.labymod.settings;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class AddonSettings {

    @Getter @Setter
    private Boolean autoReconnectEnabled;

    @Getter @Setter
    private Integer autoReconnectDelay;

    @Getter @Setter
    private Boolean clickableNicks;

}
