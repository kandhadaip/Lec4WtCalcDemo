package com.example.lec4wtcalcdemo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
//Made changes to the repo - Feb 2 10:55 am
public class MainActivity extends AppCompatActivity {
    RadioGroup radGroupConvType;
    TextView txtViewResults;
    EditText editTextInputWt;
    Button btnConvertWt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setLogo(R.mipmap.ic_launcher_wt_round);

        actionBar.setTitle(R.string.txtTitle);

        //we will complete the rest of this logic in
        //class next week
        radGroupConvType = findViewById(R.id.radGroupConv);
        editTextInputWt = findViewById(R.id.editTextInputWt);
        btnConvertWt = findViewById(R.id.btnConvertWt);
        txtViewResults = findViewById(R.id.txtViewResults);



        btnConvertWt.setOnClickListener((View v) -> {
            if (radGroupConvType.getCheckedRadioButtonId() == -1){
                Toast.makeText(this, "Please select conversion type", Toast.LENGTH_SHORT).show();
            } else if (editTextInputWt.getText().toString().isEmpty()){
                Toast.makeText(this, "Input baggage weight needs to be entered", Toast.LENGTH_SHORT).show();
            } else {
                double inputWt = 0, outputWt = 0;
                try{
                    inputWt = Double.parseDouble(editTextInputWt.getText().toString());
                    if (inputWt < 0) {
                        Toast.makeText(this, "Input weight cannot be negative", Toast.LENGTH_SHORT).show();
                    } else if (radGroupConvType.getCheckedRadioButtonId() == R.id.radBtnKgsToLbs){
                         if (inputWt > 500){
                             Toast.makeText(this, "Baggage weight in kilos exceeded", Toast.LENGTH_SHORT).show();
                         } else {
                             outputWt = inputWt * 2.2;
                             txtViewResults.setText(String.format("Converted wt: %.2f Lbs", outputWt));
                         }
                    } else if (radGroupConvType.getCheckedRadioButtonId() == R.id.radBtnLbsToKgs){
                        if (inputWt > 1000) {
                            Toast.makeText(this, "Baggage weight in pounds exceeded", Toast.LENGTH_SHORT).show();
                        } else {
                            outputWt = inputWt / 2.2;
                            txtViewResults.setText(String.format("Converted wt: %.2f Kgs", outputWt));
                        }
                    }
                } catch (Exception ex){
                    ex.printStackTrace();
                    Toast.makeText(this, "Must be a valid number", Toast.LENGTH_SHORT).show();
                }
            }


        });
        radGroupConvType.setOnCheckedChangeListener((RadioGroup group, int checkedId) -> {
            if (checkedId == R.id.radBtnKgsToLbs) {
                txtViewResults.setText("Option: Kilos to Pounds, update input weight and click on button");
            } else if (checkedId == R.id.radBtnLbsToKgs){
                txtViewResults.setText("Option: Pounds to Kilos, update input weight and click on button");
            }

        });








    }
}
