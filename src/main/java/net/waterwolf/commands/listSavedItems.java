package net.waterwolf.commands;

import net.waterwolf.FusionLib;
import net.waterwolf.SG30DLL;
import net.waterwolf.itemStorage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class listSavedItems implements CommandExecutor {
    public listSavedItems(SG30DLL plugin) {
        plugin.getCommand("listSavedItems").setExecutor(this);
    }
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(commandSender instanceof Player)) {
            FusionLib.sendToConsole("This comamnd can't be used by a console sender | Must be player", true);
            return false;
        }

        Player player = (Player) commandSender;
        List<itemStorage.ItemStorageObject> items = itemStorage.getItemsList();

        if (items != null ) {
            FusionLib.sendToPlayer("== List of Items ==",player,true);
            System.out.println("== List of Items ==");
            items.forEach((i) -> {
                player.sendMessage(String.format("-- %s --\nin:%s\nout:%s\n",i.id,i.InItem,i.OutItem));
                System.out.printf("-- %s --\nin:%s\nout:%s\n%n",i.id,i.InItem,i.OutItem);
            });
        }
        return false;
    }
}
