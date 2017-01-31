package com.example.saturday2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.saturday2.models.Weather;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherActivity extends AppCompatActivity {

    private OkHttpClient client = new OkHttpClient();
    private ListView mListView;

    private String loadUrl(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        mListView = (ListView) findViewById(R.id.list_view);

        // OkHttp
        new Thread(new Runnable() {
            @Override
            public void run() {
                String jsonData = null;
                try {
                    // OkHttp를 이용 해서 JSON 데이터를 얻고
                    jsonData = loadUrl("https://gist.githubusercontent.com/junsuk5/6b293ac781b038366419ac6e4027abb7/raw/b30deab47a9d2fd04247d9d912df3a9a4f7be8a9/weather.json");
                    Log.d("날씨", "run: " + jsonData);

                    // JSON => 자바 (ArrayList)
                    // 구글의 Gson !!!
                    Gson gson = new Gson();
                    ArrayList<Weather> data = gson.fromJson(jsonData, new TypeToken<ArrayList<Weather>>(){}.getType());

                    mAdapter = new MyAdapter(data);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mListView.setAdapter(mAdapter);
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }

    private static class MyAdapter extends BaseAdapter {
        private ArrayList<Weather> mData;

        public MyAdapter(ArrayList<Weather> data) {
            mData = data;
        }

        // 아이템 갯수
        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = new ViewHolder();
            if (convertView == null) {
                // 최초 레이아웃 로드
                convertView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_contacts, parent, false);

                ImageView imageView = (ImageView) convertView.findViewById(R.id.image_view);
                TextView nameTextView = (TextView) convertView.findViewById(R.id.name);
                TextView phoneNumberTextView = (TextView) convertView.findViewById(R.id.phone_number);

                // ViewHolder에 연결
                viewHolder.imageView = imageView;
                viewHolder.nameTextView = nameTextView;
                viewHolder.phoneNumberTextView = phoneNumberTextView;

                // ViewHolder와 현재 아이템을 연결
                convertView.setTag(viewHolder);
            } else {
                // 연결된 ViewHolder를 가져오기
                viewHolder = (ViewHolder) convertView.getTag();
            }

            // 현재 위치의 데이터
            Weather contact = mData.get(position);

            // 데이터 셋팅
            viewHolder.nameTextView.setText(contact.getCountry());
            viewHolder.phoneNumberTextView.setText(contact.getTemperature());

            return convertView;
        }

    }

    private static class ViewHolder {
        ImageView imageView;
        TextView nameTextView;
        TextView phoneNumberTextView;
    }
}
