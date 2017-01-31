package com.example.saturday2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class ListViewExamActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private ArrayList<String> mData;

    private HashMap<Integer, Class> mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_exam);

        mMap = new HashMap<>();
        mMap.put(0, LoginActivity.class);
        mMap.put(1, MainActivity.class);
        mMap.put(2, SettingsActivity.class);

        ListView listView = (ListView) findViewById(R.id.list_view);

        // 1. data (배열, 리스트)
        mData = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mData.add("data " + i);
        }

        // 2. adapter (데이터를 표시하는 방법을 결정해 주는 역할)
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                mData);

        // 3. 리스트뷰에 어댑터를 장착
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);


        // GridView
        GridView gridView = (GridView) findViewById(R.id.grid_view);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(this);
        gridView.setOnItemLongClickListener(this);

        // 스피너
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListViewExamActivity.this, "스피너 선택", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // 이건 이상하게 동작함
        view.setBackgroundColor(Color.RED);

        Toast.makeText(this, mData.get(position), Toast.LENGTH_SHORT).show();

        // HashMap을 활용한 예
        Intent intent = new Intent(this, mMap.get(position));
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "롱 클릭", Toast.LENGTH_SHORT).show();

        // 이벤트 소비했는가
        return true;
    }
}
