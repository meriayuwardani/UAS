package com.meriayuwardani.katalogbuku;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton btnTambahKatalogbuku;
    ImageButton btnChangeUserName;
    ListView lvDaftarKatalogbuku;
    TextView txNoData, txUsername;
    DaftarKatalogbukuAdapter adapter;
    List<Katalogbuku> daftarKatalogbuku;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inisialisasiView();
        loadDataKatalogbuku();
        setupListview();
    }

    private void inisialisasiView() {
        btnTambahKatalogbuku = findViewById(R.id.btn_add_katalogbuku);
        btnTambahKatalogbuku.setOnClickListener(view -> bukaFormTambahKatalogbuku());
        btnChangeUserName = findViewById(R.id.btn_change_username);
        btnChangeUserName.setOnClickListener(view -> changeUserName());
        lvDaftarKatalogbuku = findViewById(R.id.lv_katalogbuku);
        txNoData = findViewById(R.id.tx_nodata);
        txUsername = findViewById(R.id.tx_user_name);
        txUsername.setText(SharedPreferenceUtility.getUserName(this));
    }

    private void setupListview() {
        adapter = new DaftarKatalogbukuAdapter(this, daftarKatalogbuku);
        lvDaftarKatalogbuku.setAdapter(adapter);
    }
    private void refreshListView() {
        adapter.clear();
        loadDataKatalogbuku();
        adapter.addAll(daftarKatalogbuku);
    }
    private void loadDataKatalogbuku() {
        daftarKatalogbuku = SharedPreferenceUtility.getAllKatalogbuku(this);
    }

    private void bukaFormTambahKatalogbuku() {
        Intent intent = new Intent(this, FormKatalogbukuActivity.class);
        startActivity(intent);
    }

    private void changeUserName() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Ganti nama");

        final EditText input = new EditText(this);
        builder.setView(input);

        builder.setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferenceUtility.saveUserName(getApplicationContext(),input.getText().toString());
                Toast.makeText(getApplicationContext(),"Nama user berhasil disimpan",Toast.LENGTH_SHORT).show();
                txUsername.setText(SharedPreferenceUtility.getUserName(getApplicationContext()));
            }
        });
        builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshListView();
    }
}
