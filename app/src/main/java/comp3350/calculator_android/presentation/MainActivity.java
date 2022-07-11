package comp3350.calculator_android.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.view.View;

import comp3350.calculator_android.R;

public class MainActivity extends AppCompatActivity {

    private EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.textView2);
        text.setShowSoftInputOnFocus(false);

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getString(R.string.display).equals(text.getText().toString())) {
                    text.setText("");
                }
            }
        });
    }//end onCreate

    private void updateText(String newStr) {
        String oldStr = text.getText().toString();
        int cur = text.getSelectionStart();
        String leftStr = oldStr.substring(0, cur);
        String rightStr = oldStr.substring(cur);
        text.setText(String.format("%s%s%s", leftStr, newStr, rightStr));
    }


    /*********
     * Numbers
     *********/
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

    /*************
     * Functions
     ************/
    public void clear(View view) {
updateText("");
    }

    public void left_para(View view) {
updateText("(");
    }

    public void right_para(View view) {
    }

    public void fractional(View view) {

    }

    public void delete(View view) {

    }

    public void addition(View view) {

    }

    public void subtraction(View view) {

    }

    public void multiplication(View view) {

    }

    public void division(View view) {

    }

    public void log(View view) {

    }

    public void ln(View view) {

    }

    public void power(View view) {

    }

    public void decimal(View view) {
    }

    public void percentage(View view) {

    }

    public void equation(View view) {

    }
}