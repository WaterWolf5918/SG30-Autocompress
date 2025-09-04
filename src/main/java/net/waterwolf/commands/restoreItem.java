package net.waterwolf.commands;

import net.waterwolf.FusionLib;
import net.waterwolf.SG30DLL;
import net.waterwolf.itemStorage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class restoreItem implements CommandExecutor {

    public restoreItem(SG30DLL plugin) {
        plugin.getCommand("restoreItem").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(commandSender instanceof Player)) {
            FusionLib.sendToConsole("This comamnd can't be used by a console sender | Must be player", true);
            return false;
        }


        Player player = (Player) commandSender;
        String b64Encoded = "H4sIAAAAAAAA/+NiYGBm4HZJLEkMSy0qzszPY2Dg7eRgYMpMYeDPzcxLTS5KTCuxKskvSs5gZGB1zi/NK2FkAAAiK8u6NQAAAA==";
        byte[] itemBytes = {31, -117, 8, 0, 0, 0, 0, 0, 0, -1, -29, 98, 96, 96, 102, -32, 118, 73, 44, 73, 12, 75, 45, 42, -50, -52, -49, 99, 96, -32, -19, -28, 96, 96, -54, 76, 97, -32, -49, -51, -52, 75, 77, 46, 74, 76, 43, -79, 42, -55, 47, 74, -50, 96, 100, 96, 117, -50, 47, -51, 43, 97, 100, 0, 0, 34, 43, -53, -70, 53, 0, 0, 0};
//        ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64.getDecoder().decode(b64Encoded));
//        try {
//            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
//            ItemStack item = new ItemStack(Material.AIR);
//            item = (ItemStack) dataInput.readObject();
//            if (item != null) {
//                player.getInventory().setItemInMainHand(item);
//            }
//        } catch (IOException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
        List<itemStorage.ItemStorageObject> items = itemStorage.getItemsList();
        if (items != null) {

            items.forEach((i) -> {
                if (i.id.equals(strings[0])) {
                    ItemStack item = ItemStack.deserializeBytes(Base64.getDecoder().decode(i.OutItem));
                    player.getInventory().setItemInMainHand(item);
                }
            });



        }





        return true;
    }
}
