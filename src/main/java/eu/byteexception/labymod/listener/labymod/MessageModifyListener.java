package eu.byteexception.labymod.listener.labymod;

import eu.byteexception.labymod.OPSuchtLabyAddon;
import lombok.AllArgsConstructor;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.chat.MessageModifyEvent;
import net.labymod.utils.ModColor;
import net.minecraft.util.text.*;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.ClickEvent.Action;
import net.minecraft.util.text.event.HoverEvent;

import java.util.List;

@AllArgsConstructor
public class MessageModifyListener {

    private final OPSuchtLabyAddon addon;

    @Subscribe
    public void onMessageModify(MessageModifyEvent event) {
        ITextComponent component = event.getComponent();

        if (!component.getString().contains("|") || !component.getString().contains("~")) return;

        List<ITextComponent> componentSiblings = component.getSiblings();

        if (componentSiblings.isEmpty()) return;

        ITextComponent playerNameComponent = componentSiblings.stream().filter(textComponent -> textComponent.getString().startsWith("~")).findFirst().orElse(null);

        if (playerNameComponent == null) return;

        String playerName = playerNameComponent.getString();

        if (playerNameComponent.getString().equals("~")) {
            StringBuilder playerNameBuilder = new StringBuilder();

            for (int i = componentSiblings.indexOf(playerNameComponent); i < componentSiblings.size(); i++) {
                String nextComponentString = componentSiblings.get(i).getString();

                if (nextComponentString.startsWith(" ")) break;

                playerNameBuilder.append(nextComponentString);
            }

            playerName = playerNameBuilder.substring(0);
        }

        Style style = playerNameComponent.getStyle()
                .setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/realname " + playerName))
                .setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new StringTextComponent(ModColor.GREEN + "Klicke, für den richtigen Namen")));

        IFormattableTextComponent textComponent = TextComponentUtils.func_240648_a_(playerNameComponent.copyRaw(), playerNameComponent.getStyle().mergeStyle(style));

        component.getSiblings().set(componentSiblings.indexOf(playerNameComponent), textComponent);

        event.setComponent(component);
    }
}