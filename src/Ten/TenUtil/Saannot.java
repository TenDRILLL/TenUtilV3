package Ten.TenUtil;

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
            final TextComponent message = new TextComponent("Paina tästä avataksesi säännöt");
            message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://docs.google.com/document/d/1MH_QIxVtwV2wcsKPCp2Gor4_pNYEf5h-qsa3fvfVDVs"));
            player.spigot().sendMessage(message);
            return true;
        }
        this.plugin.getLogger().info("Linkki: https://docs.google.com/document/d/1MH_QIxVtwV2wcsKPCp2Gor4_pNYEf5h-qsa3fvfVDVs");
        return true;
    }
}