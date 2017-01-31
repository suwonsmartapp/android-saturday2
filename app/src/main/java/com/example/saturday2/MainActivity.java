package com.example.saturday2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    public static final int PRICE_COFFEE = 3000;
    public static final int PRICE_CREAM = 500;
    public static final int PRICE_CHOCOLATE = 700;

    private EditText mNameEditText;
    private CheckBox mCreamCheckBok;
    private CheckBox mChocolateCheckBox;
    private TextView mQuantityTextView;
    private TextView mSummaryTextView;

    private int mQuantity = 0;
    private int mCoffeePrice = 0;

    // 휘핑크림과 초코 체크 여부
    private boolean mHasCream;
    private boolean mHasChocolate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNameEditText = (EditText) findViewById(R.id.name_edit);
        mCreamCheckBok = (CheckBox) findViewById(R.id.cream_check);
        mChocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_check);
        mQuantityTextView = (TextView) findViewById(R.id.quantity_text);
        mSummaryTextView = (TextView) findViewById(R.id.summary_text);

        mCreamCheckBok.setOnCheckedChangeListener(this);
        mChocolateCheckBox.setOnCheckedChangeListener(this);

        findViewById(R.id.minus_button).setOnClickListener(this);
        findViewById(R.id.plus_button).setOnClickListener(this);
        findViewById(R.id.order_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                composeEmail(new String[]{"a811219@gmail.com"},
                        "주문",
                        mSummaryTextView.getText().toString());
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.minus_button:
                mQuantity--;
                if (mQuantity < 0) {
                    mQuantity = 0;
                }
                break;
            case R.id.plus_button:
                mQuantity++;
                if (mQuantity > 10) {
                    mQuantity = 10;
                }
                break;
        }
        mCoffeePrice = mQuantity * PRICE_COFFEE;
        displayPrice();
    }

    public void composeEmail(String[] addresses, String subject, String content) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, content);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void displayPrice() {
        mQuantityTextView.setText("" + mQuantity);

        int totalPrice = mCoffeePrice;
        if (mHasCream) {
            totalPrice += PRICE_CREAM;
        }
        if (mHasChocolate) {
            totalPrice += PRICE_CHOCOLATE;
        }

        StringBuilder builder = new StringBuilder();
        builder.append("합계 : " + totalPrice + "원");
        builder.append("\nThank you");

        mSummaryTextView.setText(builder.toString());
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        switch (compoundButton.getId()) {
            case R.id.cream_check:
                mHasCream = isChecked;
                break;
            case R.id.chocolate_check:
                mHasChocolate = isChecked;
                break;
        }
        displayPrice();
    }

    public static class MessageEvent {
        // 필요에 의해 변수를 필드에 작성
    }

    @Subscribe
    public void onMessageEvent(MessageEvent event) {


        EventBus.getDefault().post(new MessageEvent());

    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }
}
