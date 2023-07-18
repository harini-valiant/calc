package com.example.calc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private static final String HISTORY_KEY = "calculation_history";
    private ArrayList<String> calculationHistory = new ArrayList<>();
    public TextView historyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("CalcHistory", Context.MODE_PRIVATE);



        loadCalculationHistory();
        historyTextView = findViewById(R.id.historyTextView);
        Button historyButton = findViewById(R.id.histroy);
        Button addButton = findViewById(R.id.add);
        Button subtractButton = findViewById(R.id.subtract);
        Button multiplyButton = findViewById(R.id.multiply);
        Button divideButton = findViewById(R.id.divide);
        EditText number1EditText = findViewById(R.id.editTextNumber);
        EditText number2EditText = findViewById(R.id.editTextNumber2);
        TextView resultTextView = findViewById(R.id.textView);
        Button clearButton = findViewById(R.id.Clear);
        Button measurementButton = findViewById(R.id.measurement);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float n1 = Float.parseFloat(number1EditText.getText().toString());
                float n2 = Float.parseFloat(number2EditText.getText().toString());
                float result = n1 + n2;
                resultTextView.setText(String.valueOf(result));
                String calculation = n1 + "+" + n2 + "=" + result;
                calculationHistory.add(calculation);

                saveCalculationHistory();
            }
        });

        subtractButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float n1 = Float.parseFloat(number1EditText.getText().toString());
                float n2 = Float.parseFloat(number2EditText.getText().toString());
                float result = n1 - n2;
                resultTextView.setText(String.valueOf(result));
                String calculation = n1 + "-" + n2 + "=" + result;
                calculationHistory.add(calculation);

                saveCalculationHistory();
            }
        });

        multiplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float n1 = Float.parseFloat(number1EditText.getText().toString());
                float n2 = Float.parseFloat(number2EditText.getText().toString());
                float result = n1 * n2;
                resultTextView.setText(String.valueOf(result));
                String calculation = n1 + "*" + n2 + "=" + result;
                calculationHistory.add(calculation);

                saveCalculationHistory();
            }
        });

        divideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float n1 = Float.parseFloat(number1EditText.getText().toString());
                float n2 = Float.parseFloat(number2EditText.getText().toString());
                float result;
                if (n2 == 0) {
                    result = 0;
                } else {
                    result = n1 / n2;
                }
                resultTextView.setText(String.valueOf(result));
                String calculation = n1 + "/" + n2 + "=" + result;
                calculationHistory.add(calculation);

                saveCalculationHistory();
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number1EditText.setText("0");
                number2EditText.setText("0");
                resultTextView.setText("0");
            }
        });


        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayCalculationHistory();
            }
        });
    }

    private void loadCalculationHistory() {
        Set<String> historySet = sharedPreferences.getStringSet(HISTORY_KEY, new HashSet<>());
        calculationHistory = new ArrayList<>(historySet);
    }

    private void saveCalculationHistory() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set<String> historySet = new HashSet<>(calculationHistory);
        editor.putStringSet(HISTORY_KEY, historySet);
        editor.apply();
    }

    private void displayCalculationHistory() {
        StringBuilder historyBuilder = new StringBuilder();
        for (String calculation : calculationHistory) {
            historyBuilder.append(calculation).append("\n");
        }
        historyTextView.setText(historyBuilder.toString());
    }
}
