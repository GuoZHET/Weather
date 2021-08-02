package com.example.weather;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView city, tep_now, weather_now, weather_future;
    String cityName;
    SharedPreferences sharedPreferences;
    Button button2;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        weather_future = findViewById(R.id.weather_future);
        button2 = findViewById(R.id.button2);
        progressBar = findViewById(R.id.progressBar);

        cityName = "广州";

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            HttpUtil.sendOkHttpRequest("http://apis.juhe.cn/simpleWeather/query?city=" + cityName + "&key=2737a88ac08c2d8f7eb47ce7ce6284b4", new Callback() {
                                @Override
                                public void onFailure(Call call, IOException e) {

                                }

                                @Override
                                public void onResponse(Call call, Response response) throws IOException {
                                    StringBuilder stringBuilder = new StringBuilder();

                                    WeatherBean weatherBean = GsonUtil.getBean(response.body().string());
                                    for (WeatherBean.ResultBean.FutureBean futureBean : weatherBean.getResult().getFuture()) {
                                        stringBuilder.append(futureBean.getDate());
                                        stringBuilder.append(" ");
                                        stringBuilder.append(futureBean.getTemperature());
                                        stringBuilder.append(" ");
                                        stringBuilder.append(futureBean.getWeather());
                                        stringBuilder.append("\n");
                                    }


                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {

                                            city.setText(weatherBean.getResult().getCity());
                                            tep_now.setText(weatherBean.getResult().getRealtime().getTemperature());
                                            weather_now.setText(weatherBean.getResult().getRealtime().getInfo());
                                            weather_future.setText(stringBuilder);
                                            progressBar.setVisibility(View.INVISIBLE);
                                            Toast.makeText(MyApplication.getContext(), "获取数据成功", Toast.LENGTH_LONG).show();

                                        }
                                    });
                                }
                            });
                        } catch (Exception e) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    Toast.makeText(MyApplication.getContext(), "获取数据失败", Toast.LENGTH_LONG).show();
                                }
                            });

                        }
                    }
                }).start();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Activity_Search.class);
                startActivityForResult(intent, 1);
            }
        });


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpUtil.sendOkHttpRequest("http://apis.juhe.cn/simpleWeather/query?city=" + cityName + "&key=2737a88ac08c2d8f7eb47ce7ce6284b4", new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            StringBuilder stringBuilder = new StringBuilder();

                            WeatherBean weatherBean = GsonUtil.getBean(response.body().string());
                            for (WeatherBean.ResultBean.FutureBean futureBean : weatherBean.getResult().getFuture()) {
                                stringBuilder.append(futureBean.getDate());
                                stringBuilder.append(" ");
                                stringBuilder.append(futureBean.getTemperature());
                                stringBuilder.append(" ");
                                stringBuilder.append(futureBean.getWeather());
                                stringBuilder.append("\n");
                            }


                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    city.setText(weatherBean.getResult().getCity());
                                    tep_now.setText(weatherBean.getResult().getRealtime().getTemperature());
                                    weather_now.setText(weatherBean.getResult().getRealtime().getInfo());
                                    weather_future.setText(stringBuilder);
                                    progressBar.setVisibility(View.INVISIBLE);
                                    Toast.makeText(MyApplication.getContext(), "获取数据成功", Toast.LENGTH_LONG).show();

                                }
                            });
                        }
                    });
                } catch (Exception e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(MyApplication.getContext(), "获取数据失败", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        }).start();
    }


    private void init() {
        button = findViewById(R.id.button);
        city = findViewById(R.id.city);
        tep_now = findViewById(R.id.tmp_now);
        weather_now = findViewById(R.id.weather_now);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        progressBar.setVisibility(View.VISIBLE);
        switch (requestCode) {
            case 1:
                if (requestCode == 1) {
                    if (!data.getStringExtra("city").equals("1")) {
                        cityName = data.getStringExtra("city");

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    HttpUtil.sendOkHttpRequest("http://apis.juhe.cn/simpleWeather/query?city=" + cityName + "&key=2737a88ac08c2d8f7eb47ce7ce6284b4", new Callback() {
                                        @Override
                                        public void onFailure(Call call, IOException e) {

                                        }

                                        @Override
                                        public void onResponse(Call call, Response response) throws IOException {
                                            StringBuilder stringBuilder = new StringBuilder();

                                            WeatherBean weatherBean = GsonUtil.getBean(response.body().string());
                                            for (WeatherBean.ResultBean.FutureBean futureBean : weatherBean.getResult().getFuture()) {
                                                stringBuilder.append(futureBean.getDate());
                                                stringBuilder.append(" ");
                                                stringBuilder.append(futureBean.getTemperature());
                                                stringBuilder.append(" ");
                                                stringBuilder.append(futureBean.getWeather());
                                                stringBuilder.append("\n");
                                            }
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {

                                                    city.setText(weatherBean.getResult().getCity());
                                                    tep_now.setText(weatherBean.getResult().getRealtime().getTemperature());
                                                    weather_now.setText(weatherBean.getResult().getRealtime().getInfo());
                                                    weather_future.setText(stringBuilder);

                                                    progressBar.setVisibility(View.INVISIBLE);
                                                    Toast.makeText(MyApplication.getContext(), "获取数据成功", Toast.LENGTH_LONG).show();
                                                }

                                            });
                                        }
                                    });
                                } catch (Exception e) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            progressBar.setVisibility(View.INVISIBLE);
                                            Toast.makeText(MyApplication.getContext(), "获取数据失败", Toast.LENGTH_LONG).show();
                                        }
                                    });
                                }
                            }
                        }).start();
                    }
                }

                break;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }
}