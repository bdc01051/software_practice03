package com.example.jiwon_hae.myapplication.schedule;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.jiwon_hae.myapplication.R;
import com.example.jiwon_hae.myapplication.schedule.at_main.main_listview_adapter;

public class schedule extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        ListView listview = (ListView)findViewById(R.id.schedule_listView);
        main_listview_adapter adapter = new main_listview_adapter(this);

        listview.setAdapter(adapter);
    }
}
