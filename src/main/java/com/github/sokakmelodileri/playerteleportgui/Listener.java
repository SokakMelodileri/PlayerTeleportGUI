package com.github.sokakmelodileri.playerteleportgui;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Listener implements org.bukkit.event.Listener {
    PlayerTeleportGUI pl;
    Command cmd;
    Listener (PlayerTeleportGUI plugin){
        this.pl = plugin;
    }

    @EventHandler
    public void onGUIClick(InventoryClickEvent event){
        if(event.getInventory().getHolder() instanceof MyGUIHolder){
            event.setCancelled(true);
            int slotIndex = event.getRawSlot();

            ItemStack clickedItem = event.getCurrentItem();
            ClickType clickType = event.getClick();
            HumanEntity clicker = event.getWhoClicked();

            if(clickedItem == null) return;
            if(slotIndex > 54) return;

            if(clickedItem.getType().equals(Material.PLAYER_HEAD)){
                if(clickType.equals(ClickType.LEFT)){
                    if(event.getWhoClicked().getServer().getPlayer(clickedItem.getItemMeta().getDisplayName()) == null){
                        pl.sendMessage(clicker, "playerOffline");
                        return;
                    }
                    if(event.getWhoClicked().getName().equals(clickedItem.getItemMeta().getDisplayName())){
                        pl.sendMessage(clicker, "teleportToSelf");
                        return;
                    }
                    event.getWhoClicked().teleport(event.getWhoClicked().getServer().getPlayer(clickedItem.getItemMeta().getDisplayName()));
                    event.getWhoClicked().sendMessage(pl.tag + pl.getConfig().getString("messages.teleported").replace("%player%", clickedItem.getItemMeta().getDisplayName()).replace("&", "§"));
                }
            }
            if(clickedItem.getType() == Material.ARROW && clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase("§aSonraki Sayfa")){
                if(clickType.equals(ClickType.LEFT)){
                    Inventory gui2 = CreatePage.createNextPage();
                    clicker.openInventory(gui2);
                }
            }

            if(clickedItem.getType() == Material.ARROW && clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase("§aÖnceki Sayfa")){
                if(clickType.equals(ClickType.LEFT)){
                    Inventory gui = CreatePage.createFirstPage();
                    clicker.openInventory(gui);
                }
            }

            if(clickedItem.getType() == Material.BARRIER && clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase("§cKapat")){
                if(clickType.equals(ClickType.LEFT)){
                    clicker.closeInventory();
                }
            }
        }
    }

}
