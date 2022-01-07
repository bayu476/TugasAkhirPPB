package com.example.TugasAkhir;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.TugasAkhir.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    //Deklarasi Variable
    private ArrayList<data_barang> listBarang;
    private Context context;

    //Membuat Interfece
    public interface dataListener{
        void onDeleteData(data_barang data, int position);
    }

    //Deklarasi objek dari Interfece
    dataListener listener;

    //Membuat Konstruktor, untuk menerima input dari Database
    public RecyclerViewAdapter(ArrayList<data_barang> listMahasiswa, Context context) {
        this.listBarang = listMahasiswa;
        this.context = context;
        listener = (MyListData)context;
    }

    //ViewHolder Digunakan Untuk Menyimpan Referensi Dari View-View
    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView Nama, Jenis, Kondisi, Hrg_beli, Hrg_jual;
        private LinearLayout ListItem;

        ViewHolder(View itemView) {
            super(itemView);
            //Menginisialisasi View-View yang terpasang pada layout RecyclerView kita
            Nama = itemView.findViewById(R.id.nama);
            Jenis = itemView.findViewById(R.id.jenis);
            Kondisi = itemView.findViewById(R.id.kondisi);
            Hrg_beli = itemView.findViewById(R.id.hrg_beli);
            Hrg_jual = itemView.findViewById(R.id.hrg_jual);
            ListItem = itemView.findViewById(R.id.list_item);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Membuat View untuk Menyiapkan dan Memasang Layout yang Akan digunakan pada RecyclerView
        View V = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_design, parent, false);
        return new ViewHolder(V);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //Mengambil Nilai/Value yenag terdapat pada RecyclerView berdasarkan Posisi Tertentu
        final String Nama = listBarang.get(position).getNama();
        final String Jenis = listBarang.get(position).getJenis();
        final String Kondisi = listBarang.get(position).getKondisi();
        final String Hrg_beli = listBarang.get(position).getHrg_beli();
        final String Hrg_jual = listBarang.get(position).getHrg_jual();

        //Memasukan Nilai/Value kedalam View (TextView: Nama, Jenis, Kondisi, Hrg_jual, Hrg_beli)
        holder.Nama.setText("Nama: "+Nama);
        holder.Jenis.setText("Jenis: "+Jenis);
        holder.Kondisi.setText("Jenis: "+Kondisi);
        holder.Hrg_beli.setText("Jenis: "+Hrg_beli);
        holder.Hrg_jual.setText("Jenis: "+Hrg_jual);

        //Menampilkan Menu Update dan Delete saat user melakukan long klik pada salah satu item
        holder.ListItem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View view) {
                final String[] action = {"Update", "Delete"};
                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                alert.setItems(action,  new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        switch (i){
                            case 0:
                                /*
                                  Berpindah Activity pada halaman layout updateData
                                  dan mengambil data pada listMahasiswa, berdasarkan posisinya
                                  untuk dikirim pada activity updateData
                                 */
                                Bundle bundle = new Bundle();
                                bundle.putString("dataNama", listBarang.get(position).getNama());
                                bundle.putString("dataJenis", listBarang.get(position).getJenis());
                                bundle.putString("dataKondisi", listBarang.get(position).getKondisi());
                                bundle.putString("dataHrg_beli", listBarang.get(position).getHrg_beli());
                                bundle.putString("dataHrg_jual", listBarang.get(position).getHrg_jual());
                                bundle.putString("getPrimaryKey", listBarang.get(position).getKey());
                                Intent intent = new Intent(view.getContext(), updateData.class);
                                intent.putExtras(bundle);
                                context.startActivity(intent);
                                break;
                            case 1:
                                //Menggunakan interface untuk mengirim data mahasiswa, yang akan dihapus
                                listener.onDeleteData(listBarang.get(position), position);
                                break;
                        }
                    }
                });
                alert.create();
                alert.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        //Menghitung Ukuran/Jumlah Data Yang Akan Ditampilkan Pada RecyclerView
        return listBarang.size();
    }

}