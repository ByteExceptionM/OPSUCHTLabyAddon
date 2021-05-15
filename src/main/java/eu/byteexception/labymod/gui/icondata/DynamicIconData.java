package eu.byteexception.labymod.gui.icondata;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.labymod.main.LabyMod;
import net.labymod.settings.elements.ControlElement;
import net.minecraft.util.ResourceLocation;

@AllArgsConstructor
@Getter(AccessLevel.PROTECTED)
public class DynamicIconData extends ControlElement.IconData {

    protected final String identifier;
    protected final String url;

    @Override
    public boolean hasTextureIcon() {
        return true;
    }

    @Override
    public boolean hasMaterialIcon() {
        return false;
    }

    @Override
    public ResourceLocation getTextureIcon() {
        return LabyMod.getInstance().getDynamicTextureManager().getTexture(this.identifier, this.url);
    }

}
