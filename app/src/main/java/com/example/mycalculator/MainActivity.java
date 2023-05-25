package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView result_tv, sol_tv;
    MaterialButton C, bracket1, bracket2, divide;
    MaterialButton one, two, three, multiply;
    MaterialButton four, five, six, subtract;
    MaterialButton seven, eight, nine, add;
    MaterialButton point, zero, ac, equal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result_tv = findViewById(R.id.result_tv);
        sol_tv = findViewById(R.id.sol_tv);

        assign(C, R.id.buttons_c);
        assign(bracket1, R.id.buttons_bracket1);
        assign(bracket2, R.id.buttons_bracket2);
        assign(divide, R.id.buttons_divide);
        assign(one, R.id.buttons_one);
        assign(two, R.id.buttons_two);
        assign(three, R.id.buttons_three);
        assign(multiply, R.id.buttons_multiply);
        assign(four, R.id.buttons_four);
        assign(five, R.id.buttons_five);
        assign(six, R.id.buttons_six);
        assign(subtract, R.id.buttons_subtract);
        assign(seven, R.id.buttons_seven);
        assign(eight, R.id.buttons_eight);
        assign(nine, R.id.buttons_nine);
        assign(add, R.id.buttons_add);
        assign(point, R.id.buttons_point);
        assign(zero, R.id.buttons_zero);
        assign(ac, R.id.buttons_ac);
        assign(equal, R.id.buttons_equal);

    }

    void assign(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataCal = sol_tv.getText().toString();

        if(buttonText.equals("ac")){
            sol_tv.setText("");
            result_tv.setText("0");
            return;
        }

        if(buttonText.equals("=")){
            sol_tv.setText(result_tv.getText());
            return;
        }

        if(buttonText.equals("C")){
            dataCal = dataCal.substring(0,dataCal.length() - 1);
            return;
        }
        else {
            dataCal = dataCal+buttonText;
        }

        sol_tv.setText(dataCal);

        String finalResult = getResult(dataCal);
        if(!finalResult.equals("Error")){
            result_tv.setText(finalResult);
        }

    }

    String getResult(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            if (finalResult.endsWith(".0")) {
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;
        } catch (Exception e) {
            return "Error";
        }
    }
}