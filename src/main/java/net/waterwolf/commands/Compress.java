package net.waterwolf.commands;


import net.waterwolf.GUIHandler;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import net.milkbowl.vault.permission.Permission;
import net.waterwolf.FusionLib;
import net.waterwolf.SG30DLL;

import java.util.HashMap;

public class Compress implements CommandExecutor {

    public Compress(SG30DLL plugin) {
        plugin.getCommand("Compress").setExecutor(this);
        plugin.getCommand("Compress").setTabCompleter(new CompressTab());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            FusionLib.sendToConsole("This comamnd can't be used by a console sender | Must be player", true);
            return false;
        }
        Player player = (Player) sender;
        Permission perms = SG30DLL.getPermissions();
        if (args.length < 1){
            FusionLib.sendInfo(player);
            return true;
        }else{
            String subCommand = args[0];
            switch(subCommand){
                case "1":
                case "true":
                case "on" :
                    FusionLib.SetAutocompressToggle(player, 1);
                    break;
                case "0":
                case "false":
                case "off":
                    FusionLib.SetAutocompressToggle(player, 0);
                    break;
                case "info":
                    FusionLib.sendInfo(player);
                    break;
                case "message":
                    String subArgs = args[1].toLowerCase();
                    switch (subArgs) {
                        case "1":
                        case "true":
                        case "on":
                            FusionLib.Debug(subArgs, true);
                            FusionLib.SetAutocompressMsgToggle(player, 1);
                            break;
                        case "0":
                        case "false":
                        case "off":
                            FusionLib.Debug(subArgs, true);
                            FusionLib.SetAutocompressMsgToggle(player, 0);
                            break;
                        default:
                            FusionLib.sendToPlayer("&cHey! &7Please use either '&fon&7' or '&foff&7'&r", player, true);
                            break;
                    }
                    break;
                case "admin":
                    String subSubCommand = args[1].toLowerCase();
                    switch (subSubCommand){
                        case "gui":
                            if (args[2].isEmpty()) break;
                            if (perms.playerHas(player,"SGDLL.compress.admin")){
//                                HashMap<Integer, ItemStack> items = new HashMap<Integer, ItemStack>();
//                                Inventory inv = Bukkit.createInventory(player, 9,args[2]);
//
//                                ItemStack black = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
//                                ItemStack blue = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
//                                ItemStack orange = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
//                                ItemStack[] items = new ItemStack[]
//                                        {
//                                                blue,null,blue,
//                                                black,black,black,
//                                                orange,null,orange
//                                        };
//                                inv.setContents(items);
//
//
//                                player.openInventory(inv);
//                                inv.addItem(new ItemStack(Material.DROPPER));
                                SG30DLL.getCompressGUI().showCompressGUI(player,args[2]);
                            }else{
                                FusionLib.sendToPlayer("You don't have perms for this command (did toni forget to add them?)", player, false);
                            }
                            break;
                        case "remove":
                            if (perms.playerHas(player,"SGDLL.compress.admin")){


                            }else{
                                FusionLib.sendToPlayer("You don't have perms for this command (did toni forget to add them?)", player, false);
                            }
                            break;
                    }
                    break;
                
                default:
                    FusionLib.sendInfo(player);
                    break;
            }
        }
        return true;
    };
}




