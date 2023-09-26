package com.github.sokakmelodileri.playerteleportgui;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class PlayerTeleportGUI extends JavaPlugin {
    FileConfiguration config = this.getConfig();
    String tag = (config.getString("pluginTag") + "§r ").replace("&", "§");

    @Override
    public void onEnable() {
        configYenile();
        // Plugin startup logic
        getServer().getConsoleSender().sendMessage("§aPlayerTeleportGUI is enabled!");
        getServer().getPluginManager().registerEvents(new Listener(this), this);
        Objects.requireNonNull(getCommand("ptg")).setExecutor(new Command(this));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getConsoleSender().sendMessage("§4PlayerTeleportGUI is disabled!");
    }

    public void configYenile(){
        reloadConfig();
        saveDefaultConfig();
        config = getConfig();
        config.options().copyDefaults(true);
        saveConfig();
    }

    public void sendMessage (CommandSender receiver, String path, String... args){
        String rawMessage = getConfig().getString("messages."+path);
        String formattedMessage = ChatColor.translateAlternateColorCodes('&', String.format(rawMessage, (Object) args));
        receiver.sendMessage(tag + formattedMessage);
    }
}
