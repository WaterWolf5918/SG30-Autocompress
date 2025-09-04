package net.waterwolf.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;


import net.milkbowl.vault.permission.Permission;
import net.waterwolf.SG30DLL;

public class CompressTab implements TabCompleter{

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        Permission perms = SG30DLL.getPermissions();
        Boolean admin = perms.playerHas(player,"SGDLL.compress.admin");
        //what layer of args you are on /compress [1] [2] [3] [etc]
        switch(args.length){
            case 1:
                List<String> argsList = new ArrayList<>();
                argsList.add("off");
                argsList.add("on");
                argsList.add("message");
                if (admin){argsList.add("admin");}
                return argsList;
            case 2:
                List<String> argsList2 = new ArrayList<>();
                switch (args[0]){
                    case "message":
                        argsList2.add("on");
                        argsList2.add("off");
                        break;
                    case "admin":
                        if (!admin) break;
                        argsList2.add("gui");
                        argsList2.add("remove");
                        break;
                }

                return argsList2;
             case 3:
                List<String> argsList3 = new ArrayList<>();
                 switch (args[1]){
                     case "gui":
                         if (!admin) break;
                         argsList3.add("Item Id");
                         break;
                     case "remove":
                         if (!admin) break;
                         argsList3.add("Item Id");
                         break;

                 }
//                if (admin){
//                    argsList3.add("on");
//                    argsList3.add("off");
//
//                }
                return argsList3;
            default:
                break;
        }


        return null;
    }
    
}
