package com.example.saturday2;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;

public class CursorAdapterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursor_adapter);

        GridView gridView = (GridView) findViewById(R.id.grid_view);

        // data
        Cursor cursor = getContentResolver()
                .query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        null,
                        null,
                        null,
                        MediaStore.Images.ImageColumns.DATE_TAKEN + " DESC");

        // adapter
        MyCursorAdapter adapter = new MyCursorAdapter(this, cursor);

        gridView.setAdapter(adapter);
    }

    private static class MyCursorAdapter extends CursorAdapter {

        public MyCursorAdapter(Context context, Cursor c) {
            super(context, c, false);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            View convertView = LayoutInflater.from(context)
                    .inflate(R.layout.item_image, parent, false);
            return convertView;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {

            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            String path = cursor.getString(columnIndex);

            ImageView imageView = (ImageView) view.findViewById(R.id.photo_image);
            Glide.with(context).load(path).into(imageView);
        }
    }
}
