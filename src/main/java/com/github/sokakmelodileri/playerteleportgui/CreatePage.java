package com.github.sokakmelodileri.playerteleportgui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Comparator;
import java.util.stream.Collectors;

public class CreatePage {

    public static Inventory createFirstPage(){
        Inventory inventory = Bukkit.createInventory(null, 9, " ");
        Inventory gui = Bukkit.createInventory(new MyGUIHolder(inventory), 54, "§bIşınlanma Menüsü §a" + Bukkit.getOnlinePlayers().size() + " Oyuncu aktif.");
        Bukkit.getServer().getOnlinePlayers().stream().limit(6).sorted(Comparator.comparing(Player::getName)).forEach((p) -> {
            ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD, 1);
            SkullMeta skullmeta = (SkullMeta) playerHead.getItemMeta();
            skullmeta.setDisplayName(p.getName());
            skullmeta.setOwningPlayer(Bukkit.getOfflinePlayer(p.getUniqueId()));

            playerHead.setItemMeta(skullmeta);
            gui.addItem(playerHead);
        });

        ItemStack closePage = new ItemStack(Material.BARRIER, 1);
        ItemMeta closePageMeta = closePage.getItemMeta();
        closePageMeta.setDisplayName("§cKapat");
        closePage.setItemMeta(closePageMeta);
        gui.setItem(49, closePage);

        if(Bukkit.getOnlinePlayers().size() >= 6){
            ItemStack nextPage = new ItemStack(Material.ARROW, 1);
            ItemMeta nextPageMeta = nextPage.getItemMeta();
            nextPageMeta.setDisplayName("§aSonraki Sayfa");
            nextPage.setItemMeta(nextPageMeta);
            gui.setItem(53, nextPage);
        }

        return gui;
    }

    public static Inventory  createNextPage(){
        Inventory guii = Bukkit.createInventory(null, 9, " ");
        Inventory gui2 = Bukkit.createInventory(new MyGUIHolder(guii), 54, "§bIşınlanma Menüsü 2 §a" + Bukkit.getOnlinePlayers().size() + " Oyuncu aktif.");
        Bukkit.getServer().getOnlinePlayers().stream().skip(6).sorted(Comparator.comparing(Player::getName)).forEach((p) -> {
            ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD, 1);
            SkullMeta skullmeta = (SkullMeta) playerHead.getItemMeta();
            skullmeta.setDisplayName(p.getName());
            skullmeta.setOwningPlayer(Bukkit.getOfflinePlayer(p.getUniqueId()));

            playerHead.setItemMeta(skullmeta);
            gui2.addItem(playerHead);
        });

        ItemStack prevPage = new ItemStack(Material.ARROW, 1);
        ItemMeta prevPageMeta = prevPage.getItemMeta();
        prevPageMeta.setDisplayName("§aÖnceki Sayfa");
        prevPage.setItemMeta(prevPageMeta);
        gui2.setItem(45, prevPage);

        ItemStack closePage = new ItemStack(Material.BARRIER, 1);
        ItemMeta closePageMeta = closePage.getItemMeta();
        closePageMeta.setDisplayName("§cKapat");
        closePage.setItemMeta(closePageMeta);
        gui2.setItem(49, closePage);

        return gui2;
    }
}
