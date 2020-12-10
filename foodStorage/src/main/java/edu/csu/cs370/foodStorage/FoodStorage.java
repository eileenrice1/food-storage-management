package edu.csu.cs370.foodStorage;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Date;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class FoodStorage
{
    private String saveFile;
    private Gson gson;
    private ArrayList<Item> database;

    public FoodStorage(String saveFile)
    {
        this.saveFile = saveFile;
        this.gson = new GsonBuilder().setDateFormat("dd MMM yyyy").create();
        File file = new File(saveFile);
        if (file.exists())
        {
            try
            {
                this.database = this.gson.fromJson(new String(java.nio.file.Files.readAllBytes(file.toPath())), new TypeToken<ArrayList<Item>>(){}.getType());
            } catch (IOException e)
            {
                System.out.println("Could not read file " + this.saveFile);
            }
        } else
        {
            this.database = new ArrayList();
        }

        
    }

    public void addItem(Item item)
    {
        if (this.database.contains(item))
        {
            this.database.get(this.database.indexOf(item)).addQuantity(item.getQuantity());
        }
        else
        {
            this.database.add(item);
            Collections.sort(this.database);
        }
        this.save();
    }

    // public void addToItem(String type, String unit, float quantity)
    // {
    //     this.database.get(this.database.indexOf(new Item(type, unit))).addQuantity(quantity);
    // }
    
    public float removeFromItem(Item item)
    {
        int index = this.database.indexOf(item);
        if (index >= 0)
        {
            Item i = this.database.get(index);
            float removed = i.removeQuantity(item.getQuantity());
            if (i.getQuantity() <= 0) {
                this.database.remove(i);
            }
            this.save();
            return removed;
        }
        else return 0.0f;
    }

    private void save()
    {
        try
        {
            PrintWriter file = new PrintWriter(this.saveFile);
            file.print(this.gson.toJson(this.database));
            file.close();
        } catch (IOException e)
        {
            System.out.println("Could not write to file " + this.saveFile);
        }
    }

    public ArrayList<Item> getItems() {return this.database;}
}