package com.github.sokakmelodileri.playerteleportgui;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Command implements CommandExecutor {
    PlayerTeleportGUI plugin;
    Listener listener;
    public Command(PlayerTeleportGUI plugin){
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = ((Player) sender);
            Inventory inv = CreatePage.createFirstPage();
            player.openInventory(inv);

        }else{
            sender.sendMessage("§4Bu komutu sadece oyuncular kullanabilir!");
        }
        return false;

    }
}
