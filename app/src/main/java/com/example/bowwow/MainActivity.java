package com.example.bowwow;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.CheckBox;
import android.widget.Toast;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    EditText weightEditTextNumber;
    TextView amountTextView;
    CheckBox trimNailsCheckBox, fleaBathCheckBox, massageCheckBox;
    Double extrasAmount, weightAmount;
    NumberFormat fmt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weightEditTextNumber = findViewById(R.id.weight_editTextNumber);
        amountTextView = findViewById(R.id.amount_textView);

        trimNailsCheckBox = findViewById(R.id.trimNails_checkBox);
        fleaBathCheckBox = findViewById(R.id.fleaBath_checkBox);
        massageCheckBox = findViewById(R.id.massage_checkBox);

        fmt = NumberFormat.getCurrencyInstance();
        extrasAmount = 0.0;
        weightAmount = 0.0;
        updateCurrentPrice();
    }

    private void resetValues() {
        weightEditTextNumber.setText("");
        trimNailsCheckBox.setChecked(false);
        fleaBathCheckBox.setChecked(false);
        massageCheckBox.setChecked(false);
        extrasAmount = 0.0;
        weightAmount = 0.0;
        updateCurrentPrice();
    }

    private void updateCurrentPrice() {
        // Format total and display
        try {
            amountTextView.setText(fmt.format(extrasAmount + weightAmount));
        }
        catch (Exception e) {
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void setWeightCost() {
        // This function only updates the weight
        try {
            Integer weight = Integer.parseInt(weightEditTextNumber.getText().toString());

            if (weight < 30) {
                weightAmount = 35.0;
            }
            else if (weight < 50) {
                weightAmount = 45.0;
            }
            else {
                weightAmount = 55.0;
            }
        }
        catch (NumberFormatException nfe) {
            Toast.makeText(MainActivity.this, "Enter a weight in lbs.", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e) {
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void handleChecked(View v) {
        // Calculate the extra cost for the checkbox selected
        if (v.getId() == R.id.trimNails_checkBox) {
            if (trimNailsCheckBox.isChecked())
                extrasAmount += 5;
            else
                extrasAmount -= 5;
        }
        else if (v.getId() == R.id.fleaBath_checkBox) {
            if (fleaBathCheckBox.isChecked())
                extrasAmount += 10;
            else
                extrasAmount -= 10;
        }
        else {
            if (massageCheckBox.isChecked())
                extrasAmount += 20;
            else
                extrasAmount -= 20;
        }

        // Update the weight then display total
        // According to the second part of the video, weight gets calculated
        // when a checkbox is clicked as well as the extras amount
        setWeightCost();
        updateCurrentPrice();
    }

    public void handleCalculate(View v) {
        setWeightCost();
        updateCurrentPrice();
    }

    public void handleOrder(View v) {
        Toast.makeText(MainActivity.this, "Order placed.  Thank you for your business!", Toast.LENGTH_SHORT).show();
        resetValues();
    }

    public void handleReset(View v) {
        resetValues();
    }
}