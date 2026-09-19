package com.mycompany.minpro2pbo.controller;

import com.mycompany.minpro2pbo.model.BarangBengkel;
import com.mycompany.minpro2pbo.model.Oli;
import com.mycompany.minpro2pbo.model.Sparepart;
import java.util.ArrayList;

public class BengkelController {
    private final ArrayList<BarangBengkel> inventaris = new ArrayList<>();

    public BengkelController() {

        inventaris.add(new Sparepart("SP01", "Kampas Rem Depan", 45000, 15, "Keramik/Baja"));
        inventaris.add(new Sparepart("SP02", "Busi Iridium", 65000, 10, "Iridium"));
        inventaris.add(new Oli("OL01", "Oli Mesin Matic 10W-30", 55000, 20, "10W-30"));
    }

    public ArrayList<BarangBengkel> getAllBarang() {
        return inventaris;
    }

    public boolean tambahBarang(BarangBengkel barang) {
        if (cariBarangByKode(barang.getKode()) != null) {
            return false;
        }
        inventaris.add(barang);
        return true;
    }

    public BarangBengkel cariBarangByKode(String kode) {
        for (BarangBengkel b : inventaris) {
            if (b.getKode().equalsIgnoreCase(kode)) {
                return b;
            }
        }
        return null;
    }

    public boolean updateBarang(String kode, double hargaBaru, int stokBaru) {
        BarangBengkel barang = cariBarangByKode(kode);
        if (barang != null) {
            barang.setHarga(hargaBaru);
            barang.setStok(stokBaru);
            return true;
        }
        return false;
    }

    public boolean hapusBarang(String kode) {
        BarangBengkel barang = cariBarangByKode(kode);
        if (barang != null) {
            inventaris.remove(barang);
            return true;
        }
        return false;
    }
}