package id.tiregdev.atentik.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Muhammad63 on 3/25/2018.
 */

public class object_log_mahasiswa {

    @SerializedName("jam_hadir")
    private String jam_hadir;
    @SerializedName("waktu_telat")
    private String waktu_telat;
    @SerializedName("kompen")
    private String kompen;
    @SerializedName("nama_matkul")
    private String nama_matkul;
    @SerializedName("ruangan")
    private String ruangan;
    @SerializedName("nama_cubeacon")
    private String nama_cubeacon;
    @SerializedName("id_mhsw")
    private String id_mhsw;
    @SerializedName("nama")
    private String nama;
    @SerializedName("nim")
    private String nim;
    @SerializedName("status")
    private String status;
    @SerializedName("pilihans")
    private String pilihans;

    public String getPilihans() {
        return pilihans;
    }

    public void setPilihans(String pilihans) {
        this.pilihans = pilihans;
    }

    public String getId_mhsw() {
        return id_mhsw;
    }

    public void setId_mhsw(String id_mhsw) {
        this.id_mhsw = id_mhsw;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNama_cubeacon() {
        return nama_cubeacon;
    }

    public void setNama_cubeacon(String nama_cubeacon) {
        this.nama_cubeacon = nama_cubeacon;
    }

    public String getJam_hadir() {
        return jam_hadir;
    }

    public void setJam_hadir(String jam_hadir) {
        this.jam_hadir = jam_hadir;
    }

    public String getWaktu_telat() {
        return waktu_telat;
    }

    public void setWaktu_telat(String waktu_telat) {
        this.waktu_telat = waktu_telat;
    }

    public String getNama_matkul() {
        return nama_matkul;
    }

    public void setNama_matkul(String nama_matkul) {
        this.nama_matkul = nama_matkul;
    }

    public String getRuangan() {
        return ruangan;
    }

    public void setRuangan(String ruangan) {
        this.ruangan = ruangan;
    }

    public object_log_mahasiswa(String nama, String nim, String jam_hadir, String waktu_telat, String kompen) {
        this.nama = nama;
        this.nim = nim;
        this.jam_hadir = jam_hadir;
        this.waktu_telat = waktu_telat;
        this.kompen = kompen;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getKompen() {
        return kompen;
    }

    public void setKompen(String kompen) {
        this.kompen = kompen;
    }
}
