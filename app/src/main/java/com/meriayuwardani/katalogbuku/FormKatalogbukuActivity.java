package com.meriayuwardani.katalogbuku;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.Date;

public class FormKatalogbukuActivity extends AppCompatActivity {

    Button btnSimpan;
    TextInputLayout tilJudul,tilPengarang,tilThnterbit,tilKodeisbn;
    EditText edtTanggal;
    Spinner spJenisKatalogbuku;
    Date tanggalKatalogbuku;
    final String[] tipeKatalogbuku = {Katalogbuku.FIKSI, Katalogbuku.NONFIKSI};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_katalogbuku);
        inisialisasiView();
    }

    private void inisialisasiView() {
        btnSimpan = findViewById(R.id.btn_simpan);
        btnSimpan.setOnClickListener(view -> simpan());
        edtTanggal = findViewById(R.id.edt_tanggal);
        edtTanggal.setOnClickListener(view -> pickDate());
        tilJudul = findViewById(R.id.til_judul);
        tilPengarang = findViewById(R.id.til_pengarang);
        tilThnterbit = findViewById(R.id.til_thnterbit);
        tilKodeisbn= findViewById(R.id.til_kodeisbn);
        spJenisKatalogbuku = findViewById(R.id.spn_jenis);
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                tipeKatalogbuku
        );
        spJenisKatalogbuku.setAdapter(adapter);
        spJenisKatalogbuku.setSelection(0);
    }

    private void simpan() {
        if (isDataValid()) {
            Katalogbuku tr = new Katalogbuku();
            tr.setJudul(tilJudul.getEditText().getText().toString());
            tr.setPengarang(tilPengarang.getEditText().getText().toString());
            tr.setThnterbit(tilPengarang.getEditText().getText().toString());
            tr.setKodeisbn(tilKodeisbn.getEditText().getText().toString());
            tr.setJenis(spJenisKatalogbuku.getSelectedItem().toString());
            tr.setTanggal(tanggalKatalogbuku);
            SharedPreferenceUtility.addKatalogbuku(this,tr);
            Toast.makeText(this,"Data berhasil disimpan",Toast.LENGTH_SHORT).show();

            // Kembali ke layar sebelumnya setelah 500 ms (0.5 detik)
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 500);


        }
    }

    private boolean isDataValid() {
        if (tilJudul.getEditText().getText().toString().isEmpty()
                || tilPengarang.getEditText().getText().toString().isEmpty()
                  || tilThnterbit.getEditText().getText().toString().isEmpty()
                    || tilKodeisbn.getEditText().getText().toString().isEmpty()
                       || spJenisKatalogbuku.getSelectedItem().toString().isEmpty()
        ) {
            Toast.makeText(this,"Lengkapi semua isian",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    /*
        Dipanggil saat user ingin mengisi tanggal transaksi
        Menampilkan date picker dalam popup dialog
     */
    private void pickDate() {
        final Calendar c = Calendar.getInstance();
        int thn = c.get(Calendar.YEAR);
        int bln = c.get(Calendar.MONTH);
        int tgl = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (DatePickerDialog.OnDateSetListener) (view, yyyy, mm, dd) -> {
                    edtTanggal.setText(dd + "-" + (mm + 1) + "-" + yyyy);
                    c.set(yyyy,mm,dd);
                    tanggalKatalogbuku = c.getTime();
                },
                thn, bln, tgl);
        datePickerDialog.show();
    }
}