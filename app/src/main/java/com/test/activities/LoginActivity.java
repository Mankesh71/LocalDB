package com.test.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.test.R;
import com.test.database.DBHelperSingleton;
import com.test.models.TestModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Maik on 4/10/2016.
 */
@SuppressWarnings("DefaultFileTemplate")
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.etFullName)
    EditText etFullName;
    @BindView(R.id.etMobileNumber)
    EditText etMobileNumber;
    @BindView(R.id.etEmailId)
    EditText etEmailId;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.btnRegister)
    Button btnRegister;

    private Context mContext = this;
    private String strFullName = "", strEmailID = "", strMobileNumber = "", strPassword = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        // Set up the login form.

        initialization();
    }

    private void initialization() {
        btnRegister.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        /*if (isNetworkAvailable(mContext)){

        }*/
        if (checkData()) {
            TestModel testModel = new TestModel();
            testModel.setId(null);
            testModel.setStrFullName(strFullName);
            testModel.setStrMobileNumber(strMobileNumber);
            testModel.setStrEmailId(strEmailID);
            testModel.setStrPassword(strPassword);
            DBHelperSingleton.mDBDbHelper.insertCategory(testModel);

            startActivity(new Intent(mContext,TestDataActivity.class));
            finish();
        }
    }

    private boolean checkData() {
        strFullName = etFullName.getText().toString().trim();
        strMobileNumber = etMobileNumber.getText().toString().trim();
        strEmailID = etEmailId.getText().toString().trim();
        strPassword = etPassword.getText().toString().trim();

        if ("".equals(strFullName)) {
            etFullName.setError("Enter full name");
            return false;
        } else if ("".equals(strMobileNumber)) {
            etMobileNumber.setError("Enter mobile number");
            return false;
        } else if (strMobileNumber.length() != 10) {
            etMobileNumber.setError("Enter valid mobile number");
            return false;
        } else if ("".equals(strEmailID)) {
            etEmailId.setError("Enter emailId");
            return false;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(strEmailID).matches()) {
            etEmailId.setError("Enter valid emailId");
            return false;
        } else if ("".equals(strPassword)) {
            etPassword.setError("Enter password");
            return false;
        }
        return true;
    }
}

