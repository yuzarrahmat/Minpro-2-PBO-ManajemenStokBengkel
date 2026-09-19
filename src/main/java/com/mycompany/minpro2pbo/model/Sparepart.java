package com.mycompany.minpro2pbo.model;

public class Sparepart extends BarangBengkel {
    private String jenisMaterial;

    public Sparepart(String kode, String nama, double harga, int stok, String jenisMaterial) {
        super(kode, nama, harga, stok);
        this.jenisMaterial = jenisMaterial;
    }

    public String getJenisMaterial() { 
        return jenisMaterial; 
    }
    
    public void setJenisMaterial(String jenisMaterial) { 
        this.jenisMaterial = jenisMaterial; 
    }

    @Override
    public String getKategori() {
        return "Sparepart";
    }

    @Override
    public String getDetailKhusus() {
        return "Material: " + jenisMaterial;
    }
}