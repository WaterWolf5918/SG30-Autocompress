package net.waterwolf;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.Base64;
import java.util.Objects;

public class GUIHandler implements Listener {
    private Inventory inv;
    private String lastId;
    public GUIHandler(Plugin p) {
        p.getServer().getPluginManager().registerEvents(this, p);
        inv = p.getServer().createInventory(null, 9);;
    }

    public void showCompressGUI(Player p, String id){
        inv = p.getServer().createInventory(null,9,id);
        lastId = id;
        ItemStack black = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemStack blue = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
        ItemStack orange = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
        ItemStack red = new ItemStack(Material.BARRIER);
        ItemStack in = new ItemStack(Material.AIR);
        ItemStack out = new ItemStack(Material.AIR);

        for (itemStorage.ItemStorageObject i : itemStorage.cachedLocalItems) {

            if (!i.id.equals(id)) continue;
            if (i.InItem != null && !i.InItem.isEmpty()) in = ItemStack.deserializeBytes(Base64.getDecoder().decode(i.InItem));
            if (i.OutItem != null && !i.OutItem.isEmpty()) out = ItemStack.deserializeBytes(Base64.getDecoder().decode(i.OutItem));
        }

        ItemMeta blackMeta = black.getItemMeta();
        blackMeta.setDisplayName("");
        black.setItemMeta(blackMeta);

        ItemMeta blueMeta = blue.getItemMeta();
        blueMeta.setDisplayName(FusionLib.getColored("&9Input"));
        blue.setItemMeta(blueMeta);

        ItemMeta orangeMeta = orange.getItemMeta();
        orangeMeta.setDisplayName(FusionLib.getColored("&6Output"));
        orange.setItemMeta(orangeMeta);

        ItemMeta redMeta = red.getItemMeta();
        redMeta.setDisplayName(FusionLib.getColored("&c!! Delete !!"));
        red.setItemMeta(redMeta);

        ItemStack[] items = new ItemStack[]
                {
                        blue,in,blue,
                        black,red,black,
                        orange,out,orange
                };
        inv.setContents(items);


        p.openInventory(inv);
    }

    @EventHandler
    public void onIclick(InventoryClickEvent e){
        if (Objects.equals(e.getClickedInventory(), inv)){
            FusionLib.Debug("Click GUI Menu",true);
            FusionLib.Debug(String.format("Clicked %s",e.getSlot()),true);
//            e.setCancelled(false);
            FusionLib.Debug(String.valueOf(e.getSlot() == 1),true);
            if (e.getSlot() != 1 && e.getSlot() != 7) {
                    FusionLib.Debug("Cancel",true);
                e.setCancelled(true);
//                return;
            }

            if (e.getSlot() == 5) {
//                Handle delete logic
                e.getInventory().close();
                FusionLib.sendToPlayer(String.format("%s Deleted",lastId),(Player)e.getWhoClicked(),true);
                return;
            }
            if (inv.getItem(e.getSlot()) == null) {
                FusionLib.Debug("None",true);
            } else {
                FusionLib.Debug(inv.getItem(e.getSlot()).getType().name(),true);
            }

            ItemStack newIn = inv.getItem(1);
            ItemStack newOut = inv.getItem(7);
            String inB64 = Base64.getEncoder().encodeToString(newIn != null ? newIn.serializeAsBytes() : null);
            String outB64 = Base64.getEncoder().encodeToString(newOut != null ? newOut.serializeAsBytes() : null);
//            itemStorage.addItem(lastId,outB64,inB64);



        }
//        FusionLib.Debug("Click",true);
    }
}
