package com.prohua.timerrecycler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.prohua.universal.UniversalAdapter;
import com.prohua.universal.UniversalViewHolder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TimerAdapter timerAdapter;

    private List<Long> stringList;

    private List<InnerTextView> innerTextViewList;

    private class InnerTextView {
        public TextView tv;
        public int position;
    }

    private Timer timer;

    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        linearLayoutManager = new LinearLayoutManager(getBaseContext());

        recyclerView.setLayoutManager(linearLayoutManager);

        innerTextViewList = new ArrayList<>();

        stringList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            stringList.add(System.currentTimeMillis());
        }

        timer = new Timer();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                for (int i = 0; i < stringList.size(); i++) {
                    stringList.set(i, System.currentTimeMillis());
                }

                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < innerTextViewList.size(); i++) {

                            innerTextViewList.get(i).tv.setText(
                                    getTimerString(stringList.get(i)));

                        }
                    }
                });
            }
        };
        timer.schedule(timerTask, 1000, 1000);

        timerAdapter = new TimerAdapter(getBaseContext(), stringList, R.layout.recyclerview_item_layout, 0, 0);
        timerAdapter.setOnBindItemView(new UniversalAdapter.OnBindItemView() {
            @Override
            public void onBindItemViewHolder(final UniversalViewHolder universalViewHolder, final int i) {

                stringList.set(i, System.currentTimeMillis());

                universalViewHolder.setText(R.id.timeText, getTimerString(stringList.get(i)));

                for (int j = 0; j < innerTextViewList.size(); j++) {
                    if (innerTextViewList.get(j).position == i) {
                        innerTextViewList.get(i).tv = (TextView) universalViewHolder.vbi(R.id.timeText);
                        return;
                    }
                }
                InnerTextView innerTextView = new InnerTextView();
                innerTextView.position = i;
                innerTextView.tv = (TextView) universalViewHolder.vbi(R.id.timeText);
                innerTextViewList.add(innerTextView);
            }
        });
        recyclerView.setAdapter(timerAdapter);
    }

    private String getTimerString(long dateLong) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒",
                Locale.CHINA);
        Date date = new Date(dateLong);
        return sdr.format(date);
    }

}
