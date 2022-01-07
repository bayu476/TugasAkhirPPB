package com.example.TugasAkhir;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.TugasAkhir.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

class updateData extends AppCompatActivity {

    //Deklarasi Variable
    private EditText namaBaru, jenisBaru, kondisiBaru, hrgbeliBaru, hrgjualBaru;
    private Button update;
    private DatabaseReference database;
    private FirebaseAuth auth;
    private String cekNama, cekJenis, cekKondisi, cekHrgbeli, cekHrgjual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);
        getSupportActionBar().setTitle("Update Data");
        namaBaru = findViewById(R.id.new_nama);
        jenisBaru = findViewById(R.id.new_jenis);
        kondisiBaru = findViewById(R.id.new_kondisi);
        hrgbeliBaru = findViewById(R.id.new_hrgbeli);
        hrgjualBaru = findViewById(R.id.new_hrgjual);
        update = findViewById(R.id.update);

        //Mendapatkan Instance autentikasi dan Referensi dari Database
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();
        getData();
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Mendapatkan Data Mahasiswa yang akan dicek
                cekNama = namaBaru.getText().toString();
                cekJenis = jenisBaru.getText().toString();
                cekKondisi = kondisiBaru.getText().toString();
                cekHrgbeli = hrgbeliBaru.getText().toString();
                cekHrgjual = hrgjualBaru.getText().toString();

                //Mengecek agar tidak ada data yang kosong, saat proses update
                if(isEmpty(cekNama) || isEmpty(cekJenis) || isEmpty(cekKondisi) || isEmpty(cekHrgbeli) || isEmpty(cekHrgjual)){
                    Toast.makeText(updateData.this, "Data tidak boleh ada yang kosong", Toast.LENGTH_SHORT).show();
                }else {
                    //Menjalankan proses update data
                    data_barang setMahasiswa = new data_barang();
                    setMahasiswa.setNama(namaBaru.getText().toString());
                    setMahasiswa.setJenis(jenisBaru.getText().toString());
                    setMahasiswa.setKondisi(kondisiBaru.getText().toString());
                    setMahasiswa.setHrg_beli(hrgbeliBaru.getText().toString());
                    setMahasiswa.setHrg_jual(hrgjualBaru.getText().toString());
                    updateMahasiswa(setMahasiswa);
                }
            }
        });
    }

    // Mengecek apakah ada data yang kosong, sebelum diupdate
    private boolean isEmpty(String s){
        return TextUtils.isEmpty(s);
    }

    //Menampilkan data yang akan di update
    private void getData(){
        final String getNama = getIntent().getExtras().getString("dataNama");
        final String getJenis = getIntent().getExtras().getString("dataJenis");
        final String getKondisi = getIntent().getExtras().getString("dataKondisi");
        final String getHrgbeli = getIntent().getExtras().getString("dataHrgbeli");
        final String getHrgjual = getIntent().getExtras().getString("dataHrgjual");
        namaBaru.setText(getNama);
        jenisBaru.setText(getJenis);
        kondisiBaru.setText(getKondisi);
        hrgbeliBaru.setText(getHrgbeli);
        hrgjualBaru.setText(getHrgjual);
        jenisBaru.setText(getJenis);
    }

    //Proses Update data yang sudah ditentukan
    private void updateMahasiswa(data_barang barang){
        String userID = auth.getUid();
        String getKey = getIntent().getExtras().getString("getPrimaryKey");
        database.child("Admin")
                .child(userID)
                .child("Barang")
                .child(getKey)
                .setValue(barang)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        namaBaru.setText("");
                        jenisBaru.setText("");
                        kondisiBaru.setText("");
                        hrgbeliBaru.setText("");
                        hrgjualBaru.setText("");
                        Toast.makeText(updateData.this, "Data Berhasil diubah", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }
}