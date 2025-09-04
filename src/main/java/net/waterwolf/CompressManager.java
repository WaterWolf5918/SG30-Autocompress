package net.waterwolf;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Base64;
import java.util.Objects;
// import tk.waterwolf.items.Compressed.CompressedLevelOne;





public class CompressManager {
    public static void checkCompress(Player player){
//        if(!FusionLib.GetAutocompressToggle(player)){return;}
        for (int i = 0; i < player.getInventory().getSize(); i++) {
            if (player.getInventory().getItem(i) != null && Objects.requireNonNull(player.getInventory().getItem(i)).getType() != Material.AIR){
                // item is not null and not air
                ItemStack invItem = player.getInventory().getItem(i);
                assert invItem != null;

                for (itemStorage.ItemStorageObject o : itemStorage.cachedLocalItems) {
                    if (o.InItem == null || o.InItem.isEmpty() || o.OutItem == null || o.OutItem.isEmpty()) continue;
                    ItemStack scanItem = ItemStack.deserializeBytes(Base64.getDecoder().decode(o.InItem));
                    ItemStack outItem = ItemStack.deserializeBytes(Base64.getDecoder().decode(o.OutItem));
                    if (!scanItem.isSimilar(invItem)) continue;
                    int divisionCount = (int) ((double) (invItem.getAmount() / scanItem.getAmount()));
                    FusionLib.Debug(String.format("Divides: %s / %s = %s",invItem.getAmount(), scanItem.getAmount(),divisionCount),true);
                    FusionLib.Debug(String.format("Given: %s", divisionCount),true);
                    outItem.setAmount(divisionCount);
                    if (FusionLib.GetAutocompressToggle(player)) {
//                        player.getInventory().setItem(i,null);
                        ItemStack scanAmount = scanItem.clone();
                        scanAmount.setAmount(scanItem.getAmount() * divisionCount);
                        player.getInventory().removeItemAnySlot(scanAmount);
                        player.getInventory().addItem(outItem);
                        FusionLib.Debug(String.format("Replaced %s with %s", invItem.getType().name(), outItem.getType().name()),true);
                    }
//                    scanItem.getItemMeta().serialize()
                }
//                if (player.getInventory().getItem(i).hasItemMeta() && player.getInventory().getItem(i).getItemMeta().hasLore()) {
//                    //item has lore
//                    int CompressedItemAmount = 0;
//                    if (player.getInventory().getItem(i).getAmount() == 32) {
//                        CompressedItemAmount = 1;
//                    }
//                    else if (player.getInventory().getItem(i).getAmount() == 64){
//                        CompressedItemAmount = 2;
//                    }
//                    if (CompressedItemAmount != 0){
//                        int loreSize = player.getInventory().getItem(i).getItemMeta().getLore().size();
//
//
//                        switch(player.getInventory().getItem(i).getItemMeta().getLore().get(loreSize - 1).replace('§', '$')){
//                            case "$1":{
//                                FusionLib.Debug("\nLevel 1 Compress |\nSlot " + i + " |\nGiving Comprssed Item " + CompressedItemAmount + " times |\n Item name" + player.getInventory().getItem(i).getItemMeta().getDisplayName(), true);
//                                CompressLevelOne.Compress(player, i,CompressedItemAmount);
//                                break;
//                            }
//                            case "$2": {
//                                FusionLib.Debug("\nLevel 2 Compress |\nSlot " + i + " |\nGiving Comprssed Item " + CompressedItemAmount + " times |\n Item name"+ player.getInventory().getItem(i).getItemMeta().getDisplayName(), true);
//                                CompressLevelTwo.Compress(player, i,CompressedItemAmount);
//                                break;
//                            }
//                            case "$3": {
//                                FusionLib.Debug("\nLevel 3 Compress |\nSlot " + i + " |\nGiving Comprssed Item " + CompressedItemAmount + " times |\n Item name"+ player.getInventory().getItem(i).getItemMeta().getDisplayName(), true);
//                                break;
//                            }
//
//                        }
//                    }
//                }
            } // remove later
        }
    }



}
