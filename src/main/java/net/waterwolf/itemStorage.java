package net.waterwolf;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.imageio.ImageTranscoder;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class itemStorage {
    public static List<ItemStorageObject> cachedLocalItems = new ArrayList<>();

    public static class ItemStorageList {
        public List<ItemStorageObject> items = new ArrayList<>();
    }

    public static class ItemStorageObject {
        public String id;
        public String InItem;  // seems unused right now
        public String OutItem;
    }


    static Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public static void addItem(String id, String outItem, String inItem) {
        try {
            File file = new File("itemStorage.json");
            ItemStorageList json;

            if (!file.exists() || file.length() == 0) {
                FusionLib.Debug("itemStorage.json doesn't exist or is empty.",true);
                // File doesn't exist or is empty
                json = new ItemStorageList();
                json.items = new ArrayList<>();
            } else {
                // Load existing data
                FusionLib.Debug("itemStorage.json exists.",true);
                try (FileReader fr = new FileReader(file)) {
                    json = gson.fromJson(fr, ItemStorageList.class);

                    // Check if file is empty or invalid
                    if (json == null || json.items == null) {
                        json = new ItemStorageList();
                        json.items = new ArrayList<>();
                    }
                }
            }
            //@TODO: add check to see if item exists in file allready
            int jsonIndex = -1;
            ItemStorageObject obj = new ItemStorageObject();

            for (ItemStorageObject o : json.items) {
                if (o.id.equals(id)) {
                    obj = o;
                    jsonIndex = json.items.indexOf(o);
                }
            }

//            for (int i = 0; i < json.items.size(); i++){
//                if (json.items.get(i).id.equals(id)) obj = json.items.get(i); jsonIndex = i;
//                break;
//            }

            obj.id = id;
            if (outItem != null && !outItem.isEmpty()) obj.OutItem = outItem;
            if (inItem != null && !inItem.isEmpty()) obj.InItem = inItem;
            FusionLib.Debug(String.format("Got JSON Index: %s",Integer.toString(jsonIndex)),true);
            if (jsonIndex != -1) {
                // Modify existing
                FusionLib.Debug("Modifying Existing Entry",true);
                json.items.set(jsonIndex,obj);
            } else {
                // Create new
                FusionLib.Debug("Creating New",true);
                json.items.add(obj);
            }

            // Create new item object
//            final ItemStorageObject[] obj = {new ItemStorageObject()};
            // Make this an array as a hack to be able to modify it.
//            json.items.forEach(i -> {
//                if (i.id.equals(id)) {
//                    obj[0] = i;
//                }
//            });
//
//
//            obj[0].id = id;
//            obj[0].OutItem = item;
//            json.items.add(obj[0]);

            // Save updated data
            FusionLib.Debug("Attempting to write to itemStorage.json",true);
            try (FileWriter fw = new FileWriter(file)) {
                gson.toJson(json, fw);
            }
            cachedLocalItems = json.items;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void removeItem(String id) {
        try {
            File file = new File("itemStorage.json");
            if (!file.exists() || file.length() == 0) {
                FusionLib.Debug("itemStorage.json doesn't exist or is empty, nothing to remove.",true);
                return;
            }

            ItemStorageList json;
            try (FileReader fr = new FileReader(file)) {
                json = gson.fromJson(fr, ItemStorageList.class);
                if (json == null || json.items == null) {
                    FusionLib.Debug("itemStorage.json is empty.",true);
                    return; // no items in storage
                }
            }

            // Remove item matching the id
            json.items.removeIf(item -> item.id.equals(id));

            FusionLib.Debug("Attempting to write to itemStorage.json",true);
            try (FileWriter fw = new FileWriter(file)) {
                gson.toJson(json, fw);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<ItemStorageObject> getItemsList() {
        File file = new File("itemStorage.json");
        if (!file.exists() || file.length() == 0) {
            FusionLib.Debug("itemStorage.json doesn't exist or is empty, nothing to remove.",true);
            return null;
        }

        ItemStorageList json;
        try (FileReader fr = new FileReader(file)) {
            json = gson.fromJson(fr, ItemStorageList.class);
            if (json == null || json.items == null) {
                FusionLib.Debug("itemStorage.json is empty.",true);
                return null; // no items in storage
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        cachedLocalItems = json.items;
        return json.items;
    }

//    public ItemStorageObject getByID() {
//        return null;
//    }
}

