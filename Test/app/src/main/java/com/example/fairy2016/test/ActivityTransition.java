package com.example.fairy2016.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.view.Window;

public class ActivityTransition extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //进入与退出过渡动画
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        //getWindow().setEnterTransition(new Explode());
        //getWindow().setEnterTransition(new Slide());
        getWindow().setEnterTransition(new Fade());
        setContentView(R.layout.activity_transition);
    }
}
