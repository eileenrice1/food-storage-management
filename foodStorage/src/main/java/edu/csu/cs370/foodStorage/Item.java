package edu.csu.cs370.foodStorage;
import java.util.Date;
import com.google.gson.*;

public class Item implements Comparable<Item>
{
    private String type;
    private String unit;
    private float quantity;
    private Date expirationDate = null;
    private int barcode;

    public Item(String type, String unit)
    {
        this.type = type;
        this.unit = unit;
    }
    
    public Item(String type, String unit, float quantity, Date expirationDate)
    {
        this(type, unit);
        this.quantity = quantity;
        this.expirationDate = expirationDate;
    }
    
    public Item(String type, String unit, float quantity, Date expirationDate, int barcode)
    {
        this(type, unit, quantity, expirationDate);
        this.barcode = barcode;
    }

    public String getType() {return this.type;}
    public String getUnit() {return this.unit;}
    public float getQuantity() {return this.quantity;}
    public Date getExpirationDate() {return this.expirationDate;}
    public int getBarcode() {return barcode;}

    public void addQuantity(float quantity) {this.quantity += quantity;}

    public float removeQuantity(float quantity) {
        this.quantity -= quantity;
        if (this.quantity < 0.0f)
        {
            float removed = quantity + this.quantity;
            this.quantity = 0;
            return removed;
        }
        else return quantity;
    }

    public boolean equals(Object o)
    {
        if (o instanceof Item)
        {
            Item i = (Item)o;
            return this.type.equals(i.type)
                && this.unit.equals(i.unit)
                && this.barcode == i.barcode
                && ((this.expirationDate == null || i.expirationDate == null) ? true : this.expirationDate.equals(i.expirationDate));
        }
        else return false;
    }

    public String toString()
    {
        Gson gson = new GsonBuilder().setDateFormat("dd MMM yyyy").create();

        return gson.toJson(this);
    }

    public int compareTo(Item i) {
        return this.expirationDate.compareTo(i.expirationDate);
    }
}