package com.mycompany.minpro2pbo.view;

import com.mycompany.minpro2pbo.controller.BengkelController;
import com.mycompany.minpro2pbo.model.BarangBengkel;
import com.mycompany.minpro2pbo.model.Oli;
import com.mycompany.minpro2pbo.model.Sparepart;
import java.util.Scanner;

public class BengkelView {
    private final BengkelController controller;
    private final Scanner scanner;

    public BengkelView(BengkelController controller, Scanner scanner) {
        this.controller = controller;
        this.scanner = scanner;
    }

    public int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                int val = scanner.nextInt();
                scanner.nextLine();
                return val;
            }
            System.out.println("Input harus berupa angka bulat!");
            scanner.nextLine();
        }
    }

    public double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextDouble()) {
                double val = scanner.nextDouble();
                scanner.nextLine();
                return val;
            }
            System.out.println("Input harus berupa angka!");
            scanner.nextLine();
        }
    }

    public String readString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Input tidak boleh kosong!");
        }
    }

    public void tampilkanMenuUtama() {
        boolean berjalan = true;
        while (berjalan) {
            System.out.println("\n=== SISTEM PENGELOLAAN STOK BENGKEL MOTOR ===");
            System.out.println("1. Tampilkan Semua Stok Barang");
            System.out.println("2. Tambah Barang Baru (Sparepart / Oli)");
            System.out.println("3. Update Harga & Stok Barang");
            System.out.println("4. Hapus Barang dari Inventaris");
            System.out.println("5. Keluar");
            
            int pilihan = readInt("Pilih menu (1-5): ");
            switch (pilihan) {
                case 1 -> menuTampilkanBarang();
                case 2 -> menuTambahBarang();
                case 3 -> menuUpdateBarang();
                case 4 -> menuHapusBarang();
                case 5 -> {
                    berjalan = false;
                    System.out.println(" Program selesai. Terima kasih!");
                }
                default -> System.out.println(" Pilihan tidak valid!");
            }
        }
    }

    private void menuTampilkanBarang() {
        var daftar = controller.getAllBarang();
        if (daftar.isEmpty()) {
            System.out.println("\n Stok barang kosong!");
            return;
        }

        System.out.println("\n==========================================================================================");
        System.out.printf("| %-6s | %-12s | %-25s | %-14s | %-6s | %-18s |\n", 
                "Kode", "Kategori", "Nama Barang", "Harga (Rp)", "Stok", "Detail Khusus");
        System.out.println("==========================================================================================");

        for (BarangBengkel b : daftar) {
            System.out.printf("| %-6s | %-12s | %-25s | Rp %-11.2f | %-6d | %-18s |\n",
                    b.getKode(), b.getKategori(), b.getNama(), b.getHarga(), b.getStok(), b.getDetailKhusus());
        }
        System.out.println("==========================================================================================");
    }

    private void menuTambahBarang() {
        System.out.println("\n--- TAMBAH BARANG BARU ---");
        System.out.println("1. Sparepart");
        System.out.println("2. Oli Pelumas");
        int jenis = readInt("Pilih jenis barang (1-2): ");

        if (jenis != 1 && jenis != 2) {
            System.out.println("Pilihan jenis barang tidak valid!");
            return;
        }

        String kode = readString("Masukkan Kode Barang: ");
        if (controller.cariBarangByKode(kode) != null) {
            System.out.println("Eror: Kode barang sudah digunakan!");
            return;
        }

        String nama = readString("Masukkan Nama Barang: ");
        double harga = readDouble("Masukkan Harga (Rp): ");
        int stok = readInt("Masukkan Jumlah Stok: ");

        if (jenis == 1) {
            String material = readString("Masukkan Jenis Material: ");
            controller.tambahBarang(new Sparepart(kode, nama, harga, stok, material));
        } else {
            String viskositas = readString("Masukkan Viskositas SAE (contoh: 10W-40): ");
            controller.tambahBarang(new Oli(kode, nama, harga, stok, viskositas));
        }

        System.out.println("Barang berhasil ditambahkan ke inventaris!");
    }

    private void menuUpdateBarang() {
        System.out.println("\n--- UPDATE HARGA & STOK ---");
        String kode = readString("Masukkan Kode Barang: ");
        BarangBengkel b = controller.cariBarangByKode(kode);

        if (b == null) {
            System.out.println("Data barang tidak ditemukan!");
            return;
        }

        System.out.println("Barang ditemukan: " + b.getNama());
        double hargaBaru = readDouble("Harga Baru: ");
        int stokBaru = readInt("Stok Baru: ");

        controller.updateBarang(kode, hargaBaru, stokBaru);
        System.out.println("Data barang berhasil diperbarui!");
    }

    private void menuHapusBarang() {
        System.out.println("\n--- HAPUS BARANG ---");
        String kode = readString("Masukkan Kode Barang: ");
        if (controller.hapusBarang(kode)) {
            System.out.println("Barang berhasil dihapus dari inventaris!");
        } else {
            System.out.println("Data barang tidak ditemukan!");
        }
    }
}