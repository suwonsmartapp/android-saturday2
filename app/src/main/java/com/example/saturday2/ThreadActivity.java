package com.example.saturday2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class ThreadActivity extends AppCompatActivity {

    private static final String TAG = ThreadActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        // Worker 스레드
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for1();
//
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        // UI 스레드
//                        AlertDialog.Builder builder = new AlertDialog.Builder(ThreadActivity.this);
//                        builder.setMessage("다운로드 완료!!");
//                        builder.show();
//                    }
//                });
//
//            }
//        }).start();

        // 순차적으로 실행
        new DownloadTask().execute();
        // 병렬로 실행
        new DownloadTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        for2();

        test("test", "test1", "test2");
    }

    private void test(String... strings) {
        String s = strings[0];
    }

    private class DownloadTask extends AsyncTask<Void, Void, Void> {
        // 실행 전
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        // 오래 걸리는 처리
        @Override
        protected Void doInBackground(Void... params) {
            for1();
            publishProgress();
            return null;
        }

        // 진행률 처리
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        // 결과 처리
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            // UI 스레드
            AlertDialog.Builder builder = new AlertDialog.Builder(ThreadActivity.this);
            builder.setMessage("다운로드 완료!!");
            builder.show();// UI 스레드
        }
    }

    private void for2() {
        for (int i = 0; i < 10; i++) {
            Log.d(TAG, "for2: " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void for1() {
        for (int i = 0; i < 10; i++) {
            Log.d(TAG, "for1: " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
