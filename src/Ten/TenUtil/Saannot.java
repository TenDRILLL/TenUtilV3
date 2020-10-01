package Ten.TenUtil;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;

public class Saannot implements CommandExecutor
{
    private Main plugin;

    public Saannot(final Main main) {
        this.plugin = main;
    }

    public boolean onCommand(final CommandSender commandSender, final Command command, final String s, final String[] strings) {
        if (commandSender instanceof Player) {
            final Player player = (Player)commandSender;
            final TextComponent message = new TextComponent("Paina t\u00e4st\u00e4 avataksesi s\u00e4\u00e4nn\u00f6t");
            message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://docs.google.com/document/d/1MH_QIxVtwV2wcsKPCp2Gor4_pNYEf5h-qsa3fvfVDVs"));
            message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Avaa s\u00e4\u00e4nn\u00f6t selaimessa.").create()));
            player.spigot().sendMessage((BaseComponent)message);
            return true;
        }
        this.plugin.getLogger().info("Linkki: https://docs.google.com/document/d/1MH_QIxVtwV2wcsKPCp2Gor4_pNYEf5h-qsa3fvfVDVs");
        return true;
    }
}