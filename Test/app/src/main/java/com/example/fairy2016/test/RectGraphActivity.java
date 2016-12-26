package com.example.fairy2016.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RectGraphActivity extends AppCompatActivity {

    private RectGraph rectGraph;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rect_graph);
        rectGraph = (RectGraph) findViewById(R.id.rectGraph);
    }
}
