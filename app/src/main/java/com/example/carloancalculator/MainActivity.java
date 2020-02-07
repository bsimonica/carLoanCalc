package com.example.carloancalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText priceCar;
    private EditText downAmt;
    private EditText apr;
    private EditText payMonthly;
    private TextView months;
    private SeekBar numMonths;
    private RadioButton loan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        priceCar = findViewById(R.id.priceCar);
        downAmt = findViewById(R.id.downAmt);
        apr = findViewById(R.id.apr);
        payMonthly = findViewById(R.id.payMonthly);
        months = findViewById(R.id.months);
        numMonths = findViewById(R.id.numMonths);
        loan = findViewById(R.id.loan);

        numMonths.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(loan.isChecked()) {
                    months.setText(progress + "");
                }else{
                    months.setText(36 + "");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void calculate(View v){
        String price = priceCar.getText().toString();
        String down = downAmt.getText().toString();
        String rate = apr.getText().toString();
        String mon = months.getText().toString();
        if (loan.isChecked()){
            if (price.length()>0){
                if (down.length()>0){
                    if (rate.length()>0){
                        double priceValue = Double.parseDouble(price);
                        double downValue = Double.parseDouble(down);
                        double rateValue = Double.parseDouble(rate) / 100.0;
                        double totalMon = Double.parseDouble(mon);
                        double monRate = rateValue / 12.0;
                        double loanAmt = priceValue - downValue;
                        double totalMonPay = (monRate * loanAmt) / (1.0 - Math.pow((1.0 + monRate),-totalMon));
                        payMonthly.setText(String.format("%.2f", totalMonPay));
                    }else{
                        payMonthly.setText(" ");
                        Toast.makeText(this, "No APR entered.", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    payMonthly.setText(" ");
                    Toast.makeText(this, "No Down Payment entered.", Toast.LENGTH_SHORT).show();
                }
            }else{
                payMonthly.setText(" ");
                Toast.makeText(this, "No Car Price entered.", Toast.LENGTH_SHORT).show();
            }
        }else{
            if (price.length()>0){
                if (down.length()>0){
                    if (rate.length()>0){
                        months.setText(36 + "");
                        double priceValue = Double.parseDouble(price) / 3.0;
                        double downValue = Double.parseDouble(down);
                        double rateValue = Double.parseDouble(rate) / 100.0;
                        double totalMon = 36.0;
                        double monRate = rateValue / 12.0;
                        double loanAmt = priceValue - downValue;
                        double totalMonPay = (monRate * loanAmt) / (1.0 - Math.pow((1.0 + monRate),-totalMon));
                        payMonthly.setText(String.format("%.2f", totalMonPay));
                    }else{
                        payMonthly.setText(" ");
                        Toast.makeText(this, "No APR entered.", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    payMonthly.setText(" ");
                    Toast.makeText(this, "No Down Payment entered.", Toast.LENGTH_SHORT).show();
                }
            }else{
                payMonthly.setText(" ");
                Toast.makeText(this, "No Car Price entered.", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
