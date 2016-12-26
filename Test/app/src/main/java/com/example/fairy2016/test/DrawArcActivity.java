package com.example.fairy2016.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class DrawArcActivity extends AppCompatActivity {

    private TopBar topBar;
    private RatioView ratioView;
    private Spinner angleSelector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.draw_arc);
        topBar = (TopBar) findViewById(R.id.topBar);
        topBar.setOnTopBarClickListener(new TopBar.OnTopBarClickListener() {
            @Override
            public void onLeftClick() {
                Toast.makeText(getApplicationContext(), "left", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightClick() {
                Toast.makeText(getApplicationContext(), "right", Toast.LENGTH_SHORT).show();
            }
        });

        ratioView = (RatioView) findViewById(R.id.ratioView);
        angleSelector = (Spinner) findViewById(R.id.angleSelector);
        final String array[] = {"45","90","135","180","225","270","315","360"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,
                array);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        angleSelector.setAdapter(adapter);
        angleSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                int angle = Integer.valueOf(array[i]);
                ratioView.setmSweepAngle(angle);
                ratioView.setCenterText(angle);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
