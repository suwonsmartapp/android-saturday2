package com.example.saturday2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText mNameEditText;
    private CheckBox mSaveIdCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mNameEditText = (EditText) findViewById(R.id.id_edit);
        mSaveIdCheckBox = (CheckBox) findViewById(R.id.save_id_check);

        // 프리퍼런스에서 읽어오기
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String id = preferences.getString("id", "");
        boolean check = preferences.getBoolean("check", false);

        mNameEditText.setText(id);
        mSaveIdCheckBox.setChecked(check);
    }

    // 뒤로가기 키 눌렀을 때 호출
    @Override
    public void onBackPressed() {
        // ID, 체크상태를 프리퍼런스에 저장
        String id = mNameEditText.getText().toString();
        boolean check = mSaveIdCheckBox.isChecked();

        if (mSaveIdCheckBox.isChecked() == false) {
            id = "";
        }

        saveData(id, check);

        // 실제로 뒤로가는 부분
        super.onBackPressed();
    }

    public void saveData(String id, boolean check) {
        // 현재 앱의 프리퍼런스에 저장
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString("id", id);
        edit.putBoolean("check", check);
        edit.apply();
    }

}
