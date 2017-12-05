package com.test.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.test.R;
import com.test.models.TestModel;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Maik on 4/10/2016.
 */

@SuppressWarnings("DefaultFileTemplate")
public class TestDataFullActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.tvFullName)
    TextView tvFullName;
    @BindView(R.id.tvEmailId)
    TextView tvEmailId;
    @BindView(R.id.tvMobileNumber)
    TextView tvMobileNumber;
    @BindView(R.id.btnGenerateDB)
    Button btnGenerateDB;

    private final String TAG = TestDataFullActivity.class.getSimpleName();

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
        btnGenerateDB.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btnGenerateDB.getId()){
            replicateDb();
        }
    }

    private void replicateDb() {
        Context ctxt = this.getApplicationContext();
        File file, wfile;
        file = new File(ctxt.getDatabasePath("Test.db"), "");
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("dd-MM-yyyy-HH:mm:SS", Locale.getDefault());
        String strDate = mSimpleDateFormat.format(new Date());
        wfile = new File(ctxt.getExternalFilesDir(null), "Test_DB" + strDate + "_" + ".db3");

        FileInputStream fin = null;

        try {
            // create FileInputStream object
            Log.d(TAG, wfile.getAbsolutePath());

            fin = new FileInputStream(file);
            FileOutputStream fos = new FileOutputStream(wfile);
            BufferedOutputStream out = new BufferedOutputStream(fos);

            int content;
            while ((content = fin.read()) != -1) {
                // convert to char and display it
                out.write((char) content);
            }
            out.flush();
            out.close();

            Log.i(TAG, "Database copied successfully.");
        } catch (FileNotFoundException e) {
            Log.e(TAG, e.getMessage() + " File not found ");
        } catch (IOException ioe) {
            Log.e(TAG, ioe.getMessage() + " Exception file ");
        } finally {
            // close the streams using close method
            try {
                if (fin != null) {
                    fin.close();
                }
            } catch (IOException ioe) {
                Log.e(TAG, "Error closing stream: " + ioe.getMessage());
            }
        }
        Uri uri = Uri.fromFile(wfile);
        Intent i = new Intent(Intent.ACTION_SEND_MULTIPLE);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{"maik71mishra@gmail.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "subject of email is database file");
        ArrayList<Uri> uris = new ArrayList<>();
        uris.add(uri);
        i.putExtra(Intent.EXTRA_STREAM, uris);
        i.putExtra(Intent.EXTRA_TEXT, "This email contain database file and logcat file");
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "There are no email clients installed.",Toast.LENGTH_SHORT).show();
        }
    }
}
