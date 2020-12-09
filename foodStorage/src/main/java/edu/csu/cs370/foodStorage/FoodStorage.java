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
        if (this.database.contains(item))
        {
            this.database.get(this.database.indexOf(item)).addQuantity(item.getQuantity());
        }
        else this.database.add(item);
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
            return removed;
        }
        else return 0.0f;
    }

    public ArrayList<Item> getItems() {return this.database;}
}