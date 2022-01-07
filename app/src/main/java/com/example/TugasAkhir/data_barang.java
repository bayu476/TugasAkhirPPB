package com.example.TugasAkhir;

public class data_barang {
    //Deklarasi Variable
    private String nama;
    private String jenis;
    private String kondisi;
    private String hrg_beli;
    private String hrg_jual;
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenis() {
        return jenis;
    }
    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getKondisi() {
        return kondisi;
    }
    public void setKondisi(String jenis) {
        this.kondisi = kondisi;
    }

    public String getHrg_beli() {
        return hrg_beli;
    }
    public void setHrg_beli(String hrg_beli) {
        this.hrg_beli = hrg_beli;
    }

    public String getHrg_jual() {
        return hrg_jual;
    }
    public void setHrg_jual(String hrg_jual) {
        this.hrg_jual = hrg_jual;
    }

    //Membuat Konstuktor kosong untuk membaca data snapshot
    public data_barang(){
    }

    //Konstruktor dengan beberapa parameter, untuk mendapatkan Input Data dari User
    public data_barang(String nama, String jenis, String kondisi, String hrg_beli, String hrg_jual) {
        this.nama = nama;
        this.jenis = jenis;
        this.kondisi = kondisi;
        this.hrg_beli = hrg_beli;
        this.hrg_jual = hrg_jual;
    }
}
