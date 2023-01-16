package Ten.TenUtil;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;

public class Saannot implements CommandExecutor
{
    private Main plugin;
    FileConfiguration config;
    FileConfiguration messages;

    public Saannot(final Main main) {
        this.plugin = main;
        this.config = this.plugin.getTConfig();
        this.messages = this.plugin.getMessages();
    }

    public boolean onCommand(final CommandSender commandSender, final Command command, final String s, final String[] strings) {
        if (commandSender instanceof Player) {
            final Player player = (Player)commandSender;
            final TextComponent message = new TextComponent(this.messages.getString("CLICK_TO_OPEN"));
            message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, this.config.getString("RULES_LINK")));
            player.spigot().sendMessage(message);
            return true;
        }
        return true;
    }
}