package com.test.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test.R;
import com.test.models.TestModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mankesh71 on 5/24/2017.
 */

public class TestDataFullActivity extends AppCompatActivity {

    @BindView(R.id.tvFullName)
    TextView tvFullName;
    @BindView(R.id.tvEmailId)
    TextView tvEmailId;
    @BindView(R.id.tvMobileNumber)
    TextView tvMobileNumber;
    @BindView(R.id.llMainItem)
    LinearLayout llMainItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_data_full);
        ButterKnife.bind(this);

        if (getIntent() != null && getIntent().hasExtra("data")) {
            TestModel item = getIntent().getParcelableExtra("data");
            tvFullName.setText(item.getStrFullName());
            tvEmailId.setText(item.getStrMobileNumber());
            tvMobileNumber.setText(item.getStrEmailId());
        }

    }

}
