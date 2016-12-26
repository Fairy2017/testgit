package com.example.fairy2016.test;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ListView listview;
    private Class[] turnClass = {DrawArcActivity.class, RectGraphActivity.class, ClockActivity.class,
           RecyclerActivity.class, ActivityTransition.class};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = (ListView) findViewById(R.id.list);
        ArrayAdapter<String> adpter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.function));
        listview.setAdapter(adpter);
        listview.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent();
        intent.setClass(this, turnClass[i]);
        if(i==5) {
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
            return;
        }
        startActivity(intent);
    }
}
