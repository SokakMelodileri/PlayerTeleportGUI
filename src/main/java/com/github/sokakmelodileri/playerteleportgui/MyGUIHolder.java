package com.github.sokakmelodileri.playerteleportgui;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class MyGUIHolder implements InventoryHolder {
    private final Inventory inventory;

    public MyGUIHolder(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

}
