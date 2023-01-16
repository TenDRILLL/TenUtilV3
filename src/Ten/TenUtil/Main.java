package Ten.TenUtil;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Main extends JavaPlugin
{
    FileConfiguration config;
    FileConfiguration messages;
    Eventer events;

    public FileConfiguration getTConfig() {
        return this.config;
    }
    public FileConfiguration getMessages() { return this.messages; }

    public void onEnable() {
        this.getLogger().info("TenUtil enabled.");
        this.saveDefaultConfig();
        this.loadConfig();
        this.loadMessages();
        this.events = new Eventer(this);
        this.getServer().getPluginManager().registerEvents(this.events, this);
        this.getCommand("hyväksy").setExecutor(new Hyvaksy(this, this.events));
        this.getCommand("säännöt").setExecutor(new Saannot(this));
    }

    public void onDisable() {
        this.getLogger().info("TenUtil disabled.");
    }

    public void loadConfig() {
        try {
            this.config = this.getConfig();
            if (this.config != null) {
                this.getLogger().info("Config loaded.");
            }
        }
        catch (NullPointerException err) {
            Bukkit.getLogger().warning("No plugin configuration file found, this shouldn't be possible.");
        }
    }

    private void loadMessages() {
        try {
            File messagesConfig = new File("/","messages.yml");
            this.messages = YamlConfiguration.loadConfiguration(messagesConfig);
            if(this.messages != null) {
                this.getLogger().info("Messages loaded.");
            }
        } catch (NullPointerException err) {
            Bukkit.getLogger().warning("No messages configuration file found, this shouldn't be possible.");
        }
    }
}