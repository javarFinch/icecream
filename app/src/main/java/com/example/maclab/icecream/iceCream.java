package com.example.maclab.icecream;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class iceCream extends AppCompatActivity {
    CheckBox mmsCheckBox;
    CheckBox oreosCheckBox;
    CheckBox peanutsCheckBox;
    CheckBox gummyBearsCheckBox;
    CheckBox almondsCheckBox;
    CheckBox hotFudgeCheckBox;
    CheckBox brownieCheckBox;
    CheckBox strawberriesCheckBox;
    CheckBox marshMallowsCheckBox;
    TextView priceView;
    SeekBar seekBar;
    TextView seekBarTextView;
    Spinner flavorSpinner;
    Spinner sizeSpinner;
    TextView seekBarPrompt;
    double price = 0.0;
    ArrayList<Order> orders;
    Order orderItem = new Order();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ice_cream);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mmsCheckBox = (CheckBox) findViewById(R.id.MMsCheckBox);
        oreosCheckBox = (CheckBox) findViewById(R.id.OreosCheckBox);
        peanutsCheckBox = (CheckBox) findViewById(R.id.PeanutsCheckBox);
        gummyBearsCheckBox = (CheckBox) findViewById(R.id.GummyBearsCheckBox);
        almondsCheckBox = (CheckBox) findViewById(R.id.AlmondsCheckBox);
        hotFudgeCheckBox = (CheckBox) findViewById(R.id.HotFudgeCheckBox);
        brownieCheckBox = (CheckBox) findViewById(R.id.BrownieCheckBox);
        strawberriesCheckBox = (CheckBox) findViewById(R.id.StrawberriesCheckbox);
        marshMallowsCheckBox = (CheckBox) findViewById(R.id.MarshmallowsCheckBox);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBarTextView = (TextView) findViewById(R.id.seekBarTextView);
        priceView = (TextView) findViewById(R.id.priceView);
        seekBar.setVisibility(View.GONE);
        flavorSpinner = (Spinner) findViewById(R.id.flavorSpinner);
        sizeSpinner = (Spinner) findViewById(R.id.sizeSpinner);
        orders = new ArrayList<>();
        seekBarPrompt = (TextView) findViewById(R.id.seekBarPrompt);
        flavorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0)
                    orderItem.flavor = "Vanilla";
                else if (position == 1)
                    orderItem.flavor = "Chocolate";
                else
                    orderItem.flavor = "Strawberry";

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    orderItem.size = "Small";
                } else if (position == 1)
                    orderItem.size = "Medium";
                else
                    orderItem.size = "Large";
                calculatePrice();
                priceView.setText(priceToString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                orderItem.howMuchFudge = progress;
                seekBarTextView.setText(Integer.toString(progress) + " oz");
                if (progress == 0) {
                    hotFudgeCheckBox.setChecked(false);
                    seekBar.setVisibility(View.GONE);
                    seekBarTextView.setVisibility(View.GONE);
                }
                calculatePrice();
                priceView.setText(priceToString());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ice_cream, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.orderHistory) {
            Intent o = new Intent(this, OrderHistory.class);
            o.putExtra("ordersKey", orders);
            startActivity(o);
            return true;
        }
        if (id == R.id.about) {
            String aboutMessage = "Frank loves ice cream and really wants to make money off of it, the only problem is Frank keeps eating his own merchandise. So to solve this problem Frank got some professional help to put his addiction onto his customers.";
            Intent a = new Intent(this, About.class);
            a.putExtra("aboutTextKey", aboutMessage);
            startActivity(a);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void orderButtonClicked(View view) {
        Toast.makeText(this, "Your sundae is on the way enjoy.", Toast.LENGTH_SHORT).show();
        Log.d("Debug", orderItem.toString());
        orders.add(orderItem);
        orderItem = new Order();

    }

    public void resetButtonClicked(View view) {
        flavorSpinner.setSelection(0);
        sizeSpinner.setSelection(0);
        mmsCheckBox.setChecked(false);
        oreosCheckBox.setChecked(false);
        peanutsCheckBox.setChecked(false);
        gummyBearsCheckBox.setChecked(false);
        almondsCheckBox.setChecked(false);
        hotFudgeCheckBox.setChecked(true);
        seekBar.setVisibility(View.VISIBLE);
        seekBar.setProgress(1);
        seekBarTextView.setText("1 oz");
        brownieCheckBox.setChecked(false);
        strawberriesCheckBox.setChecked(false);
        marshMallowsCheckBox.setChecked(false);
        calculatePrice();
        priceView.setText(priceToString());
    }

    public void theWorksButtonClicked(View view) {
        sizeSpinner.setSelection(2);
        mmsCheckBox.setChecked(true);
        oreosCheckBox.setChecked(true);
        peanutsCheckBox.setChecked(true);
        gummyBearsCheckBox.setChecked(true);
        almondsCheckBox.setChecked(true);
        hotFudgeCheckBox.setChecked(true);
        seekBar.setVisibility(View.VISIBLE);
        seekBar.setProgress(3, true);
        seekBarTextView.setVisibility(View.VISIBLE);
        seekBarTextView.setText(seekBar.getProgress() + " oz");
        brownieCheckBox.setChecked(true);
        strawberriesCheckBox.setChecked(true);
        marshMallowsCheckBox.setChecked(true);
        calculatePrice();
        priceView.setText(priceToString());


    }

    public void checkBoxChanged(View view) {
        calculatePrice();
        priceView.setText(priceToString());
        if (view.getId() == R.id.HotFudgeCheckBox) {
            if (hotFudgeCheckBox.isChecked()) {
                seekBar.setVisibility(View.VISIBLE);
                seekBar.setProgress(1);
                seekBarPrompt.setVisibility(View.VISIBLE);
                seekBarTextView.setVisibility(View.VISIBLE);
                seekBarTextView.setText(seekBar.getProgress() + " oz");
            } else {
                seekBar.setVisibility(View.GONE);
                seekBarTextView.setVisibility(View.GONE);
            }
        }
    }

    private String priceToString() {
        NumberFormat price = NumberFormat.getCurrencyInstance(Locale.US);
        String total = price.format(this.price);
        return total;

    }

    private void calculatePrice() {
        orderItem.numberOfToppings = 0;
        price = 0.0;
        if (sizeSpinner.getSelectedItemPosition() == 0) {
            price += 2.99;
        } else if (sizeSpinner.getSelectedItemPosition() == 1) {
            price += 3.99;
        } else {
            price += 4.99;
        }
        if (hotFudgeCheckBox.isChecked()) {
            orderItem.addTopppingToOrder("Hot Fudge");
            if (seekBar.getProgress() == 0)
                price += 0.0;
            else if (seekBar.getProgress() == 1)
                price += .15;
            else if (seekBar.getProgress() == 2)
                price += .25;
            else
                price += .30;
        }
        if (peanutsCheckBox.isChecked()) {
            price += .15;
            orderItem.addTopppingToOrder("Peanuts");
        }
        if (almondsCheckBox.isChecked()) {
            price += .15;
            orderItem.addTopppingToOrder("Almonds");
        }
        if (marshMallowsCheckBox.isChecked()) {
            price += .15;
            orderItem.addTopppingToOrder("Marshmallow");
        }
        if (strawberriesCheckBox.isChecked()) {
            price += .20;
            orderItem.addTopppingToOrder("Strawberries");
        }
        if (gummyBearsCheckBox.isChecked()) {
            price += .20;
            orderItem.addTopppingToOrder("Gummy Bears");
        }
        if (oreosCheckBox.isChecked()) {
            price += .20;
            orderItem.addTopppingToOrder("Oreos");
        }
        if (brownieCheckBox.isChecked()) {
            price += .20;
            orderItem.addTopppingToOrder("Brownies");
        }
        if (mmsCheckBox.isChecked()) {
            price += .25;
            orderItem.addTopppingToOrder("M&Ms");
        }
        orderItem.orderTotal = price;
        Log.d("Debug", orderItem.toString());

    }

}
