package com.example.sqlite_database;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnStore, btnGet;
    private EditText etName;
    private TextView tvNames;

    private ArrayList<String> arrayList;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);

        tvNames = (TextView) findViewById(R.id.tvNames);
        btnStore = (Button) findViewById(R.id.btnStore);
        btnGet = (Button) findViewById(R.id.btnGet);
        etName = (EditText) findViewById(R.id.etName);

        btnStore.setOnClickListener(this);
        btnGet.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStore:
                simpan();
                break;
            case R.id.btnGet:
                tampil();
                break;
        }
    }

    public void simpan() {
        dbHelper.addStudentDetail(etName.getText().toString());
        etName.setText("");
        Toast.makeText(MainActivity.this, "Berhasil disimpan!", Toast.LENGTH_SHORT).show();
    }

    public void tampil() {
        arrayList = dbHelper.getAllStudentsList();
        tvNames.setText("");
        for (int i=0; i < arrayList.size(); i++) {
            tvNames.setText(tvNames.getText().toString() + ", " + arrayList.get(i));
        }
    }
}