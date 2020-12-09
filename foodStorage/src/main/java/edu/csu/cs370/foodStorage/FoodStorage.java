package edu.csu.cs370.foodStorage;
import java.util.ArrayList;
import java.util.Date;

public class FoodStorage
{
    private ArrayList<Item> database;

    public FoodStorage()
    {
        database = new ArrayList();
    }

    public void addItem(Item item)
    {
        this.database.add(item);
    }

    public void addToItem(String type, String unit, float quantity)
    {
        this.database.get(this.database.indexOf(new Item(type, unit))).addQuantity(quantity);
    }
    
    public void removeFromItem(String type, String unit, float quantity)
    {
        Item i = this.database.get(this.database.indexOf(new Item(type, unit)));
        i.removeQuantity(quantity);
        if (i.getQuantity() <= 0) {
            this.database.remove(i);
        }
    }

    public ArrayList<Item> getItems() {return this.database;}
}