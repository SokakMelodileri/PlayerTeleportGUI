package com.github.sokakmelodileri.playerteleportgui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class Command implements CommandExecutor {
    PlayerTeleportGUI plugin;
    public Command(PlayerTeleportGUI plugin){
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = ((Player) sender);
            Inventory inventory = Bukkit.createInventory(null, 9, " ");
            Inventory gui = Bukkit.createInventory(new MyGUIHolder(inventory), 27, "§bIşınlanma Menüsü §a" + player.getServer().getOnlinePlayers().size() + " Oyuncu aktif.");

            sender.getServer().getOnlinePlayers().forEach((p) -> {
                ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD, 1);
                SkullMeta skullmeta = (SkullMeta) playerHead.getItemMeta();
                skullmeta.setDisplayName(p.getName());
                skullmeta.setOwningPlayer(Bukkit.getOfflinePlayer(p.getUniqueId()));

                playerHead.setItemMeta(skullmeta);
                gui.addItem(playerHead);
            });

            player.openInventory(gui);

        }else{
            sender.sendMessage("§4Bu komutu sadece oyuncular kullanabilir!");
        }
        return false;
    }
}
