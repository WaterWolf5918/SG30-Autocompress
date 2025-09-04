package net.waterwolf;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
// import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;


public class FusionLib {
    public static String oldprefix = ChatColor.translateAlternateColorCodes('&',"&x&f&b&9&7&0&0&l[&x&f&d&5&b&0&0&lS&x&f&d&2&7&0&0&lG&x&f&d&2&7&0&0&l3&x&f&d&5&b&0&0&l0&x&f&b&9&7&0&0&l]&r ");
    public static String prefix = ChatColor.translateAlternateColorCodes('&',"&x&0&8&4&c&f&b&l[&x&2&b&4&1&f&7S&x&4&e&3&6&f&2G&x&7&1&2&b&e&e.&x&9&4&2&1&e&9D&x&b&7&1&6&e&5L&x&d&a&0&b&e&0L&x&f&d&0&0&d&c&l]&r");
    public static Boolean debug = true;
    public static Enchantment CompressedEnchant = Enchantment.LUCK;
    public static int CompressedLevel = 1;


    public static void sendToConsole(String string, boolean usePrefix) {
        if(usePrefix) {
            Bukkit.getConsoleSender().sendMessage(prefix + " " + getColored(string));
        } else {
            Bukkit.getConsoleSender().sendMessage(getColored(string));
        }
    }

    public static void Debug(String text,Boolean doBroadcast){
        String newText = "&5[Debug]&f " + text;
        if (!debug){ return;}
        if(doBroadcast == true){
            sendToConsole(newText, true);
            broadcast(newText);
        } else {
            sendToConsole(newText, true);
        }
    };

    public static void sendToPlayer(String text,Player player,boolean usePrefix){
        if(usePrefix == true){
            player.sendMessage(prefix + " " + getColored(text));
        } else {
            player.sendMessage(getColored(text));
        }
    }

    public static void sendInfo(Player player){
        sendToPlayer("\n&x&0&8&4&c&f&8&l&n[&x&2&b&4&1&f&4&l&nS&x&4&e&3&6&f&0&l&nG&x&7&1&2&b&e&c&l&n.&x&9&4&2&1&e&8&l&nD&x&b&7&1&6&e&4&l&nL&x&d&a&0&b&e&0&l&nL&x&f&d&0&0&d&c&l&n] &x&7&1&9&5&f&fV&x&7&e&9&2&f&fe&x&8&b&8&e&f&fr&x&9&8&8&b&f&fs&x&a&5&8&8&f&fi&x&b&2&8&5&f&fo&x&b&e&8&1&f&en 2.0.5-RC1",player,false); //&x&f&2&7&4&f&e.&x&f&f&7&1&f&0
        sendToPlayer("&x&7&1&9&5&f&fC&x&7&8&9&3&f&fr&x&7&f&9&2&f&fe&x&8&5&9&0&f&fa&x&8&c&8&e&f&ft&x&9&3&8&c&f&fe&x&9&a&8&b&f&fd &x&a&0&8&9&f&fB&x&a&7&8&7&f&fy &x&a&e&8&6&f&fW&x&b&5&8&4&f&fa&x&b&b&8&2&f&et&x&c&2&8&0&f&ee&x&c&9&7&f&f&er&x&d&0&7&d&f&eW&x&d&6&7&b&f&eo&x&d&d&7&a&f&el&x&e&4&7&8&f&ef&x&e&b&7&6&f&e5&x&f&1&7&4&f&e9&x&f&8&7&3&f&e1&x&f&f&7&1&f&e8", player, false);
        sendToPlayer("&x&7&1&9&5&f&fB&x&7&5&9&4&f&fu&x&7&a&9&3&f&fg &x&7&e&9&2&f&fT&x&8&2&9&1&f&fe&x&8&7&9&0&f&fs&x&8&b&8&e&f&ft&x&8&f&8&d&f&fe&x&9&3&8&c&f&fd &x&9&8&8&b&f&fB&x&9&c&8&a&f&fy &x&a&0&8&9&f&fW&x&a&5&8&8&f&fa&x&a&9&8&7&f&ft&x&a&d&8&6&f&fe&x&b&2&8&5&f&fr&x&b&6&8&4&f&fW&x&b&a&8&2&f&eo&x&b&e&8&1&f&el&x&c&3&8&0&f&ef&x&c&7&7&f&f&e5&x&c&b&7&e&f&e9&x&d&0&7&d&f&e1&x&d&4&7&c&f&e8 &x&d&8&7&b&f&ea&x&d&d&7&a&f&en&x&e&1&7&9&f&ed &x&e&5&7&8&f&eT&x&e&9&7&6&f&eo&x&e&e&7&5&f&en&x&f&2&7&4&f&ei&x&f&6&7&3&f&e3&x&f&b&7&2&f&e0&x&f&f&7&1&f&e0", player, false);
        
    }

    public static String getColored(Object text) {
        return ChatColor.translateAlternateColorCodes('&',(text.toString()));
    }

    public static Boolean GetAutocompressToggle(Player player){
        PersistentDataContainer store = player.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(SG30DLL.getPlugin(), "autocomp");
        Boolean toggle;

        if (store.getOrDefault(key, PersistentDataType.INTEGER, 1) >= 1){ toggle = true; }else{ toggle = false; }
        return toggle;
    }

    public static Boolean GetAutocompressMsgToggle(Player player) {
        PersistentDataContainer store = player.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(SG30DLL.getPlugin(), "msgcomp");
        Boolean toggle = true;
        if (store.getOrDefault(key, PersistentDataType.INTEGER, 1) >= 1) {
            toggle = true;
        } else {
            toggle = false;
        }
        return toggle;
    }

    public static void SetAutocompressToggle(Player player, Integer toggle){
        PersistentDataContainer store = player.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(SG30DLL.getPlugin(), "autocomp");
        store.set(key, PersistentDataType.INTEGER, toggle);
        if (toggle >= 1){
            FusionLib.sendToPlayer("&7Turned Autocompress &f(&aON&f)&r", player, true);
        }else{
            FusionLib.sendToPlayer("&7Turned Autocompress &f(&cOFF&f)&r", player, true);
        }
    }


    public static void SetAutocompressMsgToggle(Player player, Integer toggle2){
        PersistentDataContainer store2 = player.getPersistentDataContainer();
        NamespacedKey mkey = new NamespacedKey(SG30DLL.getPlugin(), "msgcomp");
        store2.set(mkey, PersistentDataType.INTEGER, toggle2);
        FusionLib.Debug(String.valueOf(toggle2), true);
        if (toggle2 >= 1){
            FusionLib.sendToPlayer("&7Turned Autocompress Message &f(&aON&f)&r", player, true);
        }else{
            FusionLib.sendToPlayer("&7Turned Autocompress Message &f(&cOFF&f)&r", player, true);
        }
    }


    public static void broadcast(String text) {
        for(Player p : Bukkit.getOnlinePlayers()) {
            p.sendMessage(getColored(text));
        }
    }

    // public static String getTime(Player player) {
    //     DecimalFormat f = new DecimalFormat("#####.##");
    //     float time = player.getStatistic(Statistic.TOTAL_WORLD_TIME) / (float) 1200;

    //     if (time > 60) {
    //         return f.format(time / 60) + "Hours";
    //     } else if(time == 60) {
    //         return f.format(time / 60) + "Hour";
    //     }
        
    //     return f.format(time) + "Mins";
    // }

}


