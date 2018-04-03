package com.example.maclab.icecream;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class OrderHistory extends AppCompatActivity {
    ArrayList<Order> orderItems;
    ArrayList<String> stringOrders;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        setTitle("Order History");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent i = getIntent();
        orderItems = new ArrayList<>();
        stringOrders = new ArrayList<String>();
         orderItems = (ArrayList<Order>) i.getSerializableExtra("ordersKey");
         for (Order item: orderItems) {
             Log.d("Debug", item.toString());
             //if (!stringOrders.contains(item))
                 stringOrders.add(item.toString());
         }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,stringOrders);
        lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                this.finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
