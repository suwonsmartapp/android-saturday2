package com.example.saturday2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.saturday2.models.Contact;

import java.util.ArrayList;

public class CustomListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list_view);

        // 1. 데이터
        ArrayList<Contact> data = new ArrayList<>();
        data.add(new Contact(R.mipmap.ic_launcher, "오준석", "010-4899-3729"));
        data.add(new Contact(R.mipmap.ic_launcher, "김홍철", "010-4899-3729"));
        data.add(new Contact(R.mipmap.ic_launcher, "배상연", "010-4899-3729"));
        data.add(new Contact(R.mipmap.ic_launcher, "한수연", "010-4899-3729"));
        data.add(new Contact(R.mipmap.ic_launcher, "조두현", "010-4899-3729"));
        data.add(new Contact(R.mipmap.ic_launcher, "양샛별", "010-4899-3729"));
        data.add(new Contact(R.mipmap.ic_launcher, "배은경", "010-4899-3729"));
        data.add(new Contact(R.mipmap.ic_launcher, "이현주", "010-4899-3729"));
        data.add(new Contact(R.mipmap.ic_launcher, "최상천", "010-4899-3729"));
        data.add(new Contact(R.mipmap.ic_launcher, "정민영", "010-4899-3729"));
        data.add(new Contact(R.mipmap.ic_launcher, "김현수", "010-4899-3729"));
        data.add(new Contact(R.mipmap.ic_launcher, "김재용", "010-4899-3729"));

        // 2. 어댑터
        MyAdapter adapter = new MyAdapter(data);

        // 3. ListView에 어댑터 장착
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }

    private static class MyAdapter extends BaseAdapter {
        private ArrayList<Contact> mData;

        public MyAdapter(ArrayList<Contact> data) {
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
            Contact contact = mData.get(position);

            // 데이터 셋팅
            viewHolder.imageView.setImageResource(contact.getImageResId());
            viewHolder.nameTextView.setText(contact.getName());
            viewHolder.phoneNumberTextView.setText(contact.getPhoneNumber());

            return convertView;
        }

    }

    private static class ViewHolder {
        ImageView imageView;
        TextView nameTextView;
        TextView phoneNumberTextView;
    }
}
