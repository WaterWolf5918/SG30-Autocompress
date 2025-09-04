package net.waterwolf.commands;

import net.waterwolf.FusionLib;
import net.waterwolf.SG30DLL;
import net.waterwolf.itemStorage;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Base64;

public class storeHeldItem implements CommandExecutor {

    public storeHeldItem(SG30DLL plugin) {
        plugin.getCommand("storeHeldItem").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            FusionLib.sendToConsole("This comamnd can't be used by a console sender | Must be player", true);
            return false;
        }
//        if (args.length < 1) {
//            FusionLib.sendToPlayer("Name is required", (Player) sender,true);
//            return false;
//        }


        Player player = (Player) sender;
        int heldSlot = player.getInventory().getHeldItemSlot();
        if (player.getInventory().getItem(heldSlot) == null || player.getInventory().getItem(heldSlot).getType() == Material.AIR) {
            return false;
        }
        ItemStack newItemStack;
        ItemStack itemstack = player.getInventory().getItem(heldSlot);



        if (itemstack != null) {
            String b64Item = Base64.getEncoder().encodeToString(itemstack.serializeAsBytes());
            player.sendMessage("item serialize");
            System.out.println(b64Item);
            itemStorage.addItem(args[0], b64Item, null);
//            System.out.println(Arrays.toString(itemstack.serializeAsBytes()));
            player.sendMessage(String.format("ItemStack b64: %s saved as: %s",b64Item,args[0]));
        }
//        if (itemmeta != null) {
//            player.sendMessage("serialize");
//            player.sendMessage(itemmeta.serialize().toString());
//            player.sendMessage("String");
//            player.sendMessage(itemmeta.getAsString());
//        }

        return true;

    }
}
