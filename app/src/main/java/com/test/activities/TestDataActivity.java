package com.test.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.test.R;
import com.test.adaptors.TestDataAdapter;
import com.test.database.DBHelperSingleton;
import com.test.models.TestModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mankesh71 on 5/24/2017.
 */

public class TestDataActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    @BindView(R.id.rvForData)
    RecyclerView rvForData;
    @BindView(R.id.etForSearch)
    EditText etForSearch;
    @BindView(R.id.ivClear)
    ImageView ivClear;

    private ArrayList<TestModel> mArrayListForData;
    private TestDataAdapter testDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_data);
        ButterKnife.bind(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvForData.setLayoutManager(layoutManager);
        rvForData.setHasFixedSize(true);

        mArrayListForData = new ArrayList<>();

        if (DBHelperSingleton.mDBDbHelper.getFullArrayList() != null && DBHelperSingleton.mDBDbHelper.getFullArrayList().size() > 0) {
            mArrayListForData.addAll(DBHelperSingleton.mDBDbHelper.getFullArrayList());

            testDataAdapter = new TestDataAdapter(this, mArrayListForData);
            rvForData.setAdapter(testDataAdapter);
        }

        ivClear.setOnClickListener(this);

        etForSearch.addTextChangedListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == ivClear.getId()) {
            if (!"".equals(etForSearch.getText().toString().trim())) {
                etForSearch.setText("");
                if (mArrayListForData != null) {
                    mArrayListForData.clear();
                } else {
                    mArrayListForData = new ArrayList<>();
                }

                if (DBHelperSingleton.mDBDbHelper.getFullArrayList() != null && DBHelperSingleton.mDBDbHelper.getFullArrayList().size() > 0) {
                    mArrayListForData.addAll(DBHelperSingleton.mDBDbHelper.getFullArrayList());

                    testDataAdapter = new TestDataAdapter(this, mArrayListForData);
                    rvForData.setAdapter(testDataAdapter);
                }
            }
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.length() > 1) {
            if (DBHelperSingleton.mDBDbHelper.getSearchList(s.toString()) != null && DBHelperSingleton.mDBDbHelper.getSearchList(s.toString()).size() > 0) {
                mArrayListForData.addAll(DBHelperSingleton.mDBDbHelper.getSearchList(s.toString()));

                testDataAdapter = new TestDataAdapter(this, mArrayListForData);
                rvForData.setAdapter(testDataAdapter);
            }
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
