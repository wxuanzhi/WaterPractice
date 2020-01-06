package com.wxuanzhi.waterpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.DecimalFormat;

public class ResultActivity extends AppCompatActivity {

    private static final String TAG = ResultActivity.class.getSimpleName();
    private static final float DEFAULT_MONEY = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        double money = intent.getDoubleExtra("MONEY",DEFAULT_MONEY);
        Log.d(TAG,money+"");
        TextView moneyText = findViewById(R.id.money);

        int n = (int)(money + 0.5f);

        DecimalFormat df = new DecimalFormat("#.#");
        String s=df.format(money);
        moneyText.setText(s+"");
    }
}
