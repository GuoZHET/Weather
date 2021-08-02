package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.weather.city.City1;
import com.example.weather.city.City2;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Activity_Search extends AppCompatActivity {

    MyDataBaseHelper myDataBaseHelper;

    City1 city1;
    City2 city2;

    ListView listView;
    ArrayList<String> cityList;
    ArrayList<String> cityList2;
    ArrayAdapter<String> adapter;
    Boolean flag;
    ProgressBar progressBar2;
    int number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        myDataBaseHelper = new MyDataBaseHelper(this, "City.db", null, 1);
        myDataBaseHelper.getWritableDatabase();
        SQLiteDatabase sqLiteDatabase = myDataBaseHelper.getWritableDatabase();
        listView = findViewById(R.id.listView);
        progressBar2 = findViewById(R.id.progressBar2);
        cityList = new ArrayList<>();
        cityList2 = new ArrayList<>();
        flag = false;


        Cursor cursor = sqLiteDatabase.query("City1",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do{
                @SuppressLint("Range")
                String city_name = cursor.getString(cursor.getColumnIndex("name"));
                cityList.add(city_name);
            }
            while (cursor.moveToNext());
        }
        cursor.close();

        adapter = new ArrayAdapter<String>(Activity_Search.this, android.R.layout.simple_list_item_1,cityList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                progressBar2.setVisibility(View.VISIBLE);
                int position = i + 1;
                if (flag == false) {
                    search(position);
                }else {
                    Intent intent = new Intent();
                    intent.putExtra("city",cityList2.get(i));
                    setResult(RESULT_OK,intent);
                    finish();
                }
            }
        });


    }



    private void search(int i){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                        HttpUtil.sendOkHttpRequest("http://guolin.tech/api/china/" + i , new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar2.setVisibility(View.INVISIBLE);
                                    Toast.makeText(MyApplication.getContext(),"查询失败",Toast.LENGTH_LONG).show();
                                }
                            });

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            Gson gson = new Gson();
                            List<City2> city2s = gson.fromJson(response.body().string(),new TypeToken<List<City2>>(){}.getType());
                            for (City2 city2 : city2s){
                                ContentValues values = new ContentValues();
                                cityList2.add(city2.getName());
                                flag = true;
                            }
                            adapter = new ArrayAdapter<String>(Activity_Search.this, android.R.layout.simple_list_item_1,cityList2);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    listView.setAdapter(adapter);
                                    progressBar2.setVisibility(View.INVISIBLE);
                                    flag = true;
                                }
                            });
                        }
                    });
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK ) {
            //do something.
            return true;
        } else {
            return super.dispatchKeyEvent(event);
        }
    }

}