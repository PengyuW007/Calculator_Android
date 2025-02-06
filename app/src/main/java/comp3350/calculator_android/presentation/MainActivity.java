package comp3350.calculator_android.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mariuszgromada.math.mxparser.*;

import comp3350.calculator_android.R;

public class MainActivity extends AppCompatActivity {

    private EditText text;
    private RecyclerView historyRecyclerView;
    private HistoryAdapter historyAdapter;
    private List<String> historyList = new ArrayList<>();
    private boolean isHistoryVisible = false;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("CalculatorHistory", MODE_PRIVATE);

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

        historyRecyclerView = findViewById(R.id.historyRecyclerView);
        historyRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Set initial adapter with empty history
        historyAdapter = new HistoryAdapter(historyList);
        historyRecyclerView.setAdapter(historyAdapter);
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

        // Add result to history
        historyList.add(userExp + " = " + result);
        historyAdapter.notifyDataSetChanged(); // Refresh RecyclerView

        text.setText(result);
        text.setSelection(result.length());
    }

    public void toggleHistory(View view) {
        // Toggle visibility of RecyclerView
        if (historyRecyclerView.getVisibility() == View.GONE) {
            historyRecyclerView.setVisibility(View.VISIBLE);
        } else {
            historyRecyclerView.setVisibility(View.GONE);
        }
    }

    // Save and Load History Using SharedPreferences
    private void saveHistory(String calculation) {
        historyList.add(0, calculation); // Add latest at top
        if (historyList.size() > 10) { // Limit history to last 10 entries
            historyList.remove(historyList.size() - 1);
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set<String> set = new HashSet<>(historyList);
        editor.putStringSet("history", set);
        editor.apply();

        historyAdapter.notifyDataSetChanged();
    }

    private Set<String> loadHistory() {
        return sharedPreferences.getStringSet("history", new HashSet<>());
    }
    public void clearHistory(View view) {
        historyList.clear();
        sharedPreferences.edit().remove("history").apply();
        historyAdapter.notifyDataSetChanged();
    }
}
/*********************************************************/