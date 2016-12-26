package com.example.fairy2016.test;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fairy2016 on 2016/12/22.
 */

public class RecyclerActivity extends Activity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    private RecyclerView recyclerView;
    private Spinner layoutType;
    private Button add;
    private Button del;

    private RecylerAdapter adapter;
    private List<String> array;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyler);
        recyclerView = (RecyclerView) findViewById(R.id.recylerView);
        layoutType = (Spinner) findViewById(R.id.layoutType);
        add = (Button) findViewById(R.id.add);
        del = (Button) findViewById(R.id.del);
        add.setOnClickListener(this);
        del.setOnClickListener(this);

        String  typeStr[] = {"线性布局","水平布局","表格布局"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, typeStr);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        layoutType.setAdapter(arrayAdapter);
        layoutType.setOnItemSelectedListener(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        array = new ArrayList<String>();
        adapter = new RecylerAdapter(array);
        recyclerView.setAdapter(adapter);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Recycler Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        RecyclerView.LayoutManager layoutManager = null;
        switch(i) {
            case 0:
                layoutManager = new LinearLayoutManager(this);
                break;
            case 1:
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                linearLayoutManager.setOrientation(LinearLayout.HORIZONTAL);
                layoutManager = linearLayoutManager;
                break;
            case 2:
                layoutManager = new GridLayoutManager(this, 3);
                break;
        }
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    @Override
    public void onClick(View view) {
        int size;
        switch(view.getId()) {
            case R.id.add:
                size = array.size()+1;
                array.add("test"+size);
                adapter.notifyDataSetChanged();
                break;
            case R.id.del:
                size = array.size();
                if(size > 0) {
                    array.remove(size-1);
                    adapter.notifyDataSetChanged();
                }
                break;
        }
    }
}
