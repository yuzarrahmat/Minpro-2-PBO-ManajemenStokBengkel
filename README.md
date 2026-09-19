# Minpro-2-PBO-ManajemenStokBengkel
Nama : Yuzar Rahmat Rafi Alhaq, NIM : 2509116025, Sistem Informasi A '2025

## 1. Deskripsi Singkat Program
**Sistem Stok Bengkel Motor** adalah aplikasi berbasis Command Line Interface (CLI) yang dibangun menggunakan bahasa pemrograman Java. Program ini dirancang untuk membantu pengelolaan inventaris barang di bengkel motor secara terstruktur. Sistem menyediakan fungsi CRUD (Create, Read, Update, Delete) untuk mengelola data barang, yang terbagi menjadi dua kategori utama: **Sparepart** dan **Oli Pelumas**.

---

## 2. Penjelasan Alur Program
Alur jalannya program dirancang interaktif menggunakan menu utama sebagai berikut:

1. **Inisialisasi Sistem:**
   * Program dijalankan melalui class `Minpro2PBO` yang menginstansiasi `BengkelController` dan `BengkelView`.
   * `BengkelController` secara otomatis memuat data awal (*dummy data*) berisi 3 item barang ke dalam memori (`ArrayList`).

2. **Menu Utama & Navigasi:**
   * **Menu 1 - Tampilkan Semua Stok Barang (Read):** Menampilkan seluruh data inventaris bengkel dalam bentuk tabel rapi, lengkap dengan kode, kategori, nama, harga, stok, dan detail khusus masing-masing barang.
     <img width="1920" height="1020" alt="Screenshot 2026-09-19 211859" src="https://github.com/user-attachments/assets/0b0f080c-1a54-42af-9ecd-c84db902a16d" />
   * **Menu 2 - Tambah Barang Baru (Create):** Pengguna memilih jenis barang (Sparepart / Oli), lalu menginputkan kode, nama, harga, stok, serta atribut khusus (Material untuk Sparepart, Viskositas/SAE untuk Oli).
     <img width="1920" height="1020" alt="Screenshot 2026-09-19 212000" src="https://github.com/user-attachments/assets/dc74a34e-8a11-4f56-a1fa-502d5958e387" />
   * **Menu 3 - Update Harga & Stok Barang (Update):** Pengguna memasukkan kode barang yang ingin diperbarui. Jika barang ditemukan, pengguna dapat memasukkan harga dan jumlah stok baru.
     <img width="1920" height="1020" alt="Screenshot 2026-09-19 212104" src="https://github.com/user-attachments/assets/3d1c30dc-d5f5-4388-9bee-b40aa32f6bfd" />
   * **Menu 4 - Hapus Barang dari Inventaris (Delete):** Pengguna memasukkan kode barang yang ingin dihapus. Sistem akan menghapus barang tersebut dari koleksi `ArrayList`.
     <img width="1920" height="1020" alt="Screenshot 2026-09-19 212132" src="https://github.com/user-attachments/assets/63798845-8fc0-4806-815c-e0c20c61bc0e" />

   * **Menu 5 - Keluar:** Menghentikan perulangan menu dan menutup program.

3. **Input Guard (Validasi Input):**
   * Di setiap proses input angka, layer View melakukan validasi tipe data menggunakan try-catch/Scanner check untuk mencegah program *crash* jika pengguna salah menginputkan data (misal menginput huruf pada kolom angka).

---

## 3. Penjelasan Penerapan Encapsulation dan Inheritance

### A. Encapsulation (Pengapsulan Data)
* **Penerapan:**
  * Seluruh atribut pada class `BarangBengkel` (`kode`, `nama`, `harga`, `stok`), class `Sparepart` (`jenisMaterial`), dan class `Oli` (`viskositas`) dideklarasikan dengan access modifier **`private`**.
  * Akses terhadap atribut-atribut tersebut dibatasi secara ketat dan hanya dapat dilakukan melalui method **Getter** dan **Setter** publik.
  * Pada method *setter* (seperti `setHarga` dan `setStok`), diterapkan logika validasi internal untuk memastikan nilai harga dan stok tidak boleh bernilai negatif (minus).

### B. Inheritance (Pewarisan sifat)
* **Penerapan:**
  * Class **`BarangBengkel`** bertindak sebagai *Abstract Superclass* yang menampung atribut dan method umum dari semua entitas barang di bengkel.
  * Class **`Sparepart`** dan **`Oli`** bertindak sebagai *Concrete Subclasses* yang mewarisi (*extends*) seluruh atribut dan method dari `BarangBengkel`.
  * Subclass menambahkan atribut spesifik masing-masing (`jenisMaterial` pada `Sparepart` dan `viskositas` pada `Oli`) serta mengimplementasikan (*override*) method abstrak `getKategori()` dan `getDetailKhusus()`.

---

## 4. Penjelasan Letak Penerapan Nilai Tambah

Project ini mengimplementasikan nilai tambah berupa **Penerapan Arsitektur MVC (Model-View-Controller)** dan **Polymorphism**:

1. **Arsitektur Model-View-Controller (MVC):**
   * **Model (`com.mycompany.minpro2pbo.model`):** Berisi class `BarangBengkel`, `Sparepart`, dan `Oli` yang murni merepresentasikan entitas data dan enkapsulasi.
   * **Controller (`com.mycompany.minpro2pbo.controller`):** Berisi class `BengkelController` yang mengisolasi seluruh logika bisnis, pemrosesan data, dan manajemen `ArrayList<BarangBengkel>`.
   * **View (`com.mycompany.minpro2pbo.view`):** Berisi class `BengkelView` yang khusus menangani tampilan CLI, pemformatan output tabel, dan mekanisme *Input Guard* (validasi input pengguna).
   * **Main (`com.mycompany.minpro2pbo.main`):** Berisi class `Minpro2PBO` sebagai *entry point* pemanggilan program.
   * *Manfaat Nilai Tambah:* Mencegah *monolithic code* (kode menumpuk dalam satu file) dan memastikan pemisahan tanggung jawab (*Separation of Concerns*) yang bersih.

2. **Penerapan Polymorphism & Abstraction:**
   * Class `BarangBengkel` dibuat sebagai `abstract class`.
   * Penggunaan `@Override` pada method `getKategori()` dan `getDetailKhusus()` di class `Sparepart` dan `Oli` memungkinkan program memproses koleksi heterogen `ArrayList<BarangBengkel>` secara dinamis saat menampilkan tabel inventaris.
