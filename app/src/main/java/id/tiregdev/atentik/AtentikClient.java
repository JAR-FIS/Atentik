package id.tiregdev.atentik;

import java.util.List;

import id.tiregdev.atentik.Model.object_cubeacon;
import id.tiregdev.atentik.Model.object_dosen;
import id.tiregdev.atentik.Model.object_jadwal;
import id.tiregdev.atentik.Model.object_kompen_terbanyak;
import id.tiregdev.atentik.Model.object_log;
import id.tiregdev.atentik.Model.object_log_mahasiswa;
import id.tiregdev.atentik.Model.object_lokasi;
import id.tiregdev.atentik.Model.object_mahasiswa;
import id.tiregdev.atentik.Model.object_mhsw_dosen;
import id.tiregdev.atentik.Model.object_notif;
import id.tiregdev.atentik.Model.object_set_jadwal_masuk;
import id.tiregdev.atentik.Model.object_total;
import id.tiregdev.atentik.Model.object_uang_kompen;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface AtentikClient {
    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("registerMahasiswa")
    Call<object_mahasiswa> regisMahasiswa(@Field("nama") String nama,
                                          @Field("nim") String nim,
                                          @Field("password") String pass,
                                          @Field("confirmpassword") String confirmpass,
                                          @Field("imei_hp") String imei_hp);

    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("registerDosen")
    Call<object_dosen> regisDosen(@Field("nama") String nama,
                                  @Field("nip") String nip,
                                  @Field("password") String pass,
                                  @Field("confirmpassword") String confirmpass);

    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("loginMahasiswa")
    Call<object_mahasiswa> loginMahasiswa(@Field("nim") String nim,
                                          @Field("password") String pass,
                                          @Field("imei_hp") String imei_hp);

    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("loginDosen")
    Call<object_dosen> loginDosen(@Field("nip") String nip,
                                  @Field("password") String pass,
                                  @Field("imei_hp") String imei_hp);


    @Headers("Accept: application/json")
    @GET("profilMahasiswa")
    Call<object_mahasiswa> profilMahasiswa(@Header("Authorization") String auth);

    @Headers("Accept: application/json")
    @GET("profilDosen")
    Call<object_dosen> profilDosen(@Header("Authorization") String auth);

    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("profilEmailMahasiswa")
    Call<object_mahasiswa> profilEmailMahasiswa(@Header("Authorization") String auth,
                                                @Field("email") String email);

    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("profilTlpMahasiswa")
    Call<object_mahasiswa> profilTlpMahasiswa(@Header("Authorization") String auth,
                                              @Field("tlp") String tlp);

    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("profilEmailDosen")
    Call<object_dosen> profilEmailDosen(@Header("Authorization") String auth,
                                                @Field("email") String email);

    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("profilTlpDosen")
    Call<object_dosen> profilTlpDosen(@Header("Authorization") String auth,
                                              @Field("tlp") String tlp);

    @Headers("Accept: application/json")
    @GET("logoutMahasiswa")
    Call<object_mahasiswa> logoutMahasiswa(@Header("Authorization") String auth);

    @Headers("Accept: application/json")
    @GET("logoutDosen")
    Call<object_dosen> logoutDosen(@Header("Authorization") String auth);

    @Headers("Accept: application/json")
    @GET("dataDosen")
    Call<List<object_mhsw_dosen>> dataDosen(@Header("Authorization") String auth);

    @Headers("Accept: application/json")
    @GET("dataMahasiswaDsn")
    Call<List<object_mhsw_dosen>> dataMahasiswaDsn(@Header("Authorization") String auth);

    @Headers("Accept: application/json")
    @GET("dataDosenDsn")
    Call<List<object_mhsw_dosen>> dataDosenDsn(@Header("Authorization") String auth);

    @Headers("Accept: application/json")
    @GET("dataMahasiswa")
    Call<List<object_mhsw_dosen>> dataMahasiswa(@Header("Authorization") String auth);

    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("jadwalMahasiswa")
    Call<List<object_jadwal>> jadwalMahasiswa(@Header("Authorization") String auth,
                                              @Field("hari") String hari);

    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("jadwalDosen")
    Call<List<object_jadwal>> jadwalDosen(@Header("Authorization") String auth,
                                              @Field("hari") String hari);

    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("absenMahasiswa")
    Call<object_log_mahasiswa> absenMahasiswa(@Header("Authorization") String auth,
                                              @Field("hari") String hari,
                                              @Field("jam") String jam,
                                              @Field("tanggal") String tanggal,
                                              @Field("nama_cubeacon") String nama_cubeacon);

    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("logKehadiranMahasiswa")
    Call<List<object_log>> logKehadiranMahasiswa(@Header("Authorization") String auth,
                                                 @Field("tanggal") String tanggal);

    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("totalAbsensiMahasiswa")
    Call<object_total> totalAbsensiMahasiswa(@Header("Authorization") String auth,
                                             @Field("tanggal") String tanggal);

    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("totalSeluruhAbsensiMahasiswa")
    Call<object_total> totalSeluruhAbsensiMahasiswa(@Header("Authorization") String auth,
                                                    @Field("tanggal") String tanggal);

    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("totalUangKompen")
    Call <object_total> totalUangKompen(@Header("Authorization") String auth,
                                              @Field("tanggal") String tanggal);

    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("kompenTerbanyak")
    Call<List<object_kompen_terbanyak>> kompenTerbanyak(@Header("Authorization") String auth,
                                                        @Field("tanggal") String tanggal);

    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("kompenTerbanyakDosen")
    Call<List<object_kompen_terbanyak>> kompenTerbanyakDosen(@Header("Authorization") String auth,
                                                             @Field("tanggal") String tanggal);

    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("lihatJadwalMasukDosen")
    Call<List<object_set_jadwal_masuk>> lihatJadwalMasukDosen(@Header("Authorization") String auth,
                                                      @Field("tanggal") String tanggal,
                                                      @Field("hari") String hari);

    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("lihatJadwalMasukMahasiswa")
    Call<List<object_set_jadwal_masuk>> lihatJadwalMasukMahasiswa(@Header("Authorization") String auth,
                                                              @Field("tanggal") String tanggal,
                                                              @Field("hari") String hari);

    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("setJadwalMasukMahasiswa")
    Call<object_set_jadwal_masuk> setJadwalMasukMahasiswa(@Header("Authorization") String auth,
                                                      @Field("nama_matkul") String nama_matkul,
                                                      @Field("hari") String hari,
                                                      @Field("nama_dosen") String nama_dosen,
                                                      @Field("tanggal") String tanggal,
                                                      @Field("pilihan") String pilihan,
                                                      @Field("waktu_masuk") String waktu_masuk,
                                                      @Field("waktu_diubah") String waktu_diubah);

    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("setJadwalMasukDosen")
    Call<object_set_jadwal_masuk> setJadwalMasukDosen(@Header("Authorization") String auth,
                                                      @Field("nama_matkul") String nama_matkul,
                                                      @Field("hari") String hari,
                                                      @Field("nama_dosen") String nama_dosen,
                                                      @Field("tanggal") String tanggal,
                                                      @Field("pilihan") String pilihan,
                                                      @Field("waktu_masuk") String waktu_masuk,
                                                      @Field("waktu_diubah") String waktu_diubah);

    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("lihatLogAbsenMahasiswa")
    Call<List<object_log_mahasiswa>> lihatLogAbsenMahasiswa (@Header("Authorization") String auth,
                                                   @Field("tanggal") String tanggal,
                                                   @Field("hari") String hari,
                                                   @Field("jam") String jam);

    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("editLogAbsenMahasiswa")
    Call<object_log_mahasiswa> editLogAbsenMahasiswa (@Header("Authorization") String auth,
                                                             @Field("pilihans") String pilihans,
                                                             @Field("nim") String nim,
                                                            @Field("waktu_telat") int waktu_telat);

    @Headers("Accept: application/json")
    @GET("seluruhKelas")
    Call<List<object_mhsw_dosen>> seluruhKelas(@Header("Authorization") String auth);

    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("ruangCubeacon")
    Call<object_cubeacon> ruangCubeacon (@Header("Authorization") String auth,
                                         @Field("nama_cubeacon") String nama_cubeacon);

    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("lokasiMahasiswa")
    Call<object_cubeacon> lokasiMahasiswa (@Header("Authorization") String auth,
                                           @Field("nama_cubeacon") String nama_cubeacon);

    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("lokasiDosen")
    Call<object_cubeacon> lokasiDosen (@Header("Authorization") String auth,
                                           @Field("nama_cubeacon") String nama_cubeacon);

    @Headers("Accept: application/json")
    @GET("lihatLokasi")
    Call<List<object_lokasi>> lihatLokasi(@Header("Authorization") String auth);

    @Headers("Accept: application/json")
    @GET("lihatLokasiDsn")
    Call<List<object_lokasi>> lihatLokasiDsn(@Header("Authorization") String auth);

    @Headers("Accept: application/json")
    @GET("lihatNotif")
    Call<List<object_notif>> lihatNotif(@Header("Authorization") String auth);

    @Headers("Accept: application/json")
    @GET("lihatNotifDosen")
    Call<List<object_notif>> lihatNotifDosen(@Header("Authorization") String auth);
}
