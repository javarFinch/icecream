package com.example.maclab.icecream;

import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.Spinner;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;

/**
 * Created by maclab on 3/22/18.
 */

public class Order implements Serializable {
    String[] toppings;
    String size;
    String flavor;
    int howMuchFudge;
    int numberOfToppings = 0;
    double orderTotal = 0.0;


    public Order () {
        this.flavor = "Vanilla";
        this.size = "Small";
        this.toppings = new String[9];
        numberOfToppings = 0;
        orderTotal = 2.99;
    }

    public void addTopppingToOrder(String topping) {
        if ( numberOfToppings < toppings.length)
            toppings[numberOfToppings] = topping;
            numberOfToppings++;
        }
        public String toppingsToString() {
        if (numberOfToppings == 0)
            return "none";
        String temp = toppings[0];
        for (int i = 1; i < numberOfToppings; i++)
            temp += "," + toppings[i];
        return temp;
        }

        public String getOrderTotal() {
            NumberFormat price = NumberFormat.getCurrencyInstance(Locale.US);
            String total = price.format(orderTotal);
            return total;
        }


    @Override
    public String toString() {
        return "Order:" + " size:" + size + "\n" +
        "flavor:" + flavor + "\n" +
                "toppings:" + toppingsToString() + "\n" +
                "Fudge:" + howMuchFudge + " oz" + "\n" +
                "Total=" + getOrderTotal();
    }
}
