package com.wxuanzhi.waterpractice;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText edmonth;
    boolean isNext = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edmonth = findViewById(R.id.edmonth);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Switch sw = findViewById(R.id.sw);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isNext = isChecked;
                TextView text = findViewById(R.id.type);
                text.setText(isNext ? "Every other month" : "Monthly");
            }
        });
    }

    public void reset() {
        edmonth.setText("");
    }

    public void calculate(View view) {
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                reset();
            }
        };

        if (!TextUtils.isEmpty(edmonth.getText().toString())) {
            float num = Float.parseFloat(edmonth.getText().toString());
            double money = 0;
            if (isNext == false) {
                if (num >= 1 && num <= 10) {
                    money = num * 7.35;
                } else if (num >= 11 && num <= 30) {
                    money = num * 9.45 - 21;
                } else if (num >= 31 && num <= 50) {
                    money = num * 11.55 - 84;
                } else if (num > 50) {
                    money = num * 12.075 - 110.25;
                }

            } else {
                if (num >= 1 && num <= 20) {
                    money = num * 7.35;
                } else if (num >= 21 && num <= 60) {
                    money = num * 9.45 - 42;
                } else if (num >= 61 && num <= 100) {
                    money = num * 11.55 - 168;
                } else if (num > 100) {
                    money = num * 12.075 - 220.5;
                }
            }
            Intent intent = new Intent(this, ResultActivity.class);
            startActivity(intent);
            intent.putExtra("Fee", money);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
