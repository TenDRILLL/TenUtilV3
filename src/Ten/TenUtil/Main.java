package Ten.TenUtil;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{
    FileConfiguration config;
    Eventer events;

    public FileConfiguration getTConfig() {
        return this.config;
    }

    public void onEnable() {
        this.getLogger().info("TenUtil enabled.");
        this.saveDefaultConfig();
        this.loadConfig();
        this.events = new Eventer(this);
        this.getServer().getPluginManager().registerEvents(this.events, this);
        this.getCommand("hyvaksy").setExecutor(new Hyvaksy(this, this.events));
        this.getCommand("saannot").setExecutor(new Saannot(this));
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
            Bukkit.getLogger().warning("No config, shit's fucked.");
        }
    }
}