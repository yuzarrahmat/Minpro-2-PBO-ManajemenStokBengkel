package com.mycompany.minpro2pbo.model;

public class Oli extends BarangBengkel {
    private String viskositas;

    public Oli(String kode, String nama, double harga, int stok, String viskositas) {
        super(kode, nama, harga, stok);
        this.viskositas = viskositas;
    }

    public String getViskositas() { 
        return viskositas; 
    }
    
    public void setViskositas(String viskositas) { 
        this.viskositas = viskositas; 
    }

    @Override
    public String getKategori() {
        return "Oli Pelumas";
    }

    @Override
    public String getDetailKhusus() {
        return "SAE: " + viskositas;
    }
}