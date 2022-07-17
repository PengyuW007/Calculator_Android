package comp3350.calculator_android.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.widget.EditText;
import android.view.View;

import org.mariuszgromada.math.mxparser.*;

import comp3350.calculator_android.R;

public class MainActivity extends AppCompatActivity {

    private EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**** To make default keyboard invisible ***/
        text = findViewById(R.id.textView2);
        text.setInputType(InputType.TYPE_NULL);
        /******************************************/

        /**** *********Enter text console *********/
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getString(R.string.display).equals(text.getText().toString())) {
                    text.setText("");
                }
            }
        });
    }//end onCreate

    /****** Text Editor/ Number needs to be calculated ****/
    private void updateText(String newStr) {
        String oldStr = text.getText().toString();
        int cur = text.getSelectionStart();
        String leftStr = oldStr.substring(0, cur);
        String rightStr = oldStr.substring(cur);
        //text.setText(String.format("%s%s%s", leftStr, newStr, rightStr));
        if (getString(R.string.display).equals(text.getText().toString())) {
            text.setText(newStr);
        } else {
            text.setText(leftStr + newStr + rightStr);
        }
        text.setSelection(cur + 1);
    }//end updateText

    /******************************************************************************
     *** These(Numbers/Functions) will be used in res/layout/activity_main.xml ****
     ******************************************************************************/
    /********* Numbers *********/
    public void zero(View view) {
        updateText("0");
    }//end zero

    public void one(View view) {
        updateText("1");
    }//end one

    public void two(View view) {
        updateText("2");
    }//end two

    public void three(View view) {
        updateText("3");
    }

    public void four(View view) {
        updateText("4");
    }

    public void five(View view) {
        updateText("5");
    }

    public void six(View view) {
        updateText("6");
    }

    public void seven(View view) {
        updateText("7");
    }

    public void eight(View view) {
        updateText("8");
    }

    public void nine(View view) {
        updateText("9");
    }

    /******* Functions **********/
    public void clear(View view) {
        text.setText("");
    }

    public void left_para(View view) {
        updateText("(");
    }

    public void right_para(View view) {
        updateText(")");
    }

    public void fractional(View view) {
        updateText("!");
    }

    public void delete(View view) {
        int curr = text.getSelectionStart();
        int textLen = text.getText().length();

        if (curr != 0 && textLen != 0) {
            SpannableStringBuilder selection = (SpannableStringBuilder) text.getText();
            selection.replace(curr - 1, curr, "");
            text.setText(selection);
            text.setSelection(curr - 1);
        }

//        int curr = text.getSelectionStart();
//        int len = text.getText().length();
    }

    public void addition(View view) {
        updateText("+");
    }

    public void subtraction(View view) {
        updateText("-");
    }

    public void multiplication(View view) {
        updateText("×");
    }

    public void division(View view) {
        updateText("÷");
    }

    public void log(View view) {
        updateText("㏒");
    }

    public void ln(View view) {
        updateText("㏑");
    }

    public void power(View view) {
        updateText("^");
    }

    public void decimal(View view) {
        updateText(".");
    }

    public void percentage(View view) {
        updateText("%");
    }

    public void equation(View view) {
        String userExp = text.getText().toString();

        userExp = userExp.replaceAll("÷","/");
        userExp = userExp.replaceAll("×","*");

        Expression expression = new Expression(userExp);

        String result = String.valueOf(expression.calculate());

        text.setText(result);
        text.setSelection(result.length());
    }
}
/*********************************************************/