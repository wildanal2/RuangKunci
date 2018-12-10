package com.keyroom.com.keyroom.Rest;

import com.keyroom.com.keyroom.Model.GetDetailKelas;
import com.keyroom.com.keyroom.Model.GetKelas;
import com.keyroom.com.keyroom.Model.GetPeminjaman;
import com.keyroom.com.keyroom.Model.GetUser;
import com.keyroom.com.keyroom.Model.PostPutDellFasilitas;
import com.keyroom.com.keyroom.Model.PostPutDellJadwal;
import com.keyroom.com.keyroom.Model.PostPutDellKelas;
import com.keyroom.com.keyroom.Model.PostPutDellPeminjaman;
import com.keyroom.com.keyroom.Model.PostPutDellUser;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface ApiInterface {

//    Login
//    @FormUrlEncoded
//    @POST("Login")
//    Call<PostPutDellKaryawan> getLogin(@Field("nohp") String nohp,
//                                       @Field("password") String password);

//   get all Kelas
    @GET("Ruangan/RuanganAll")
    Call<GetKelas> getKelas();

    @FormUrlEncoded
    @POST("Ruangan/RuanganDetail")
    Call<GetDetailKelas> getDetail(@Field("id") String id);

//    Post Kelas
    @Multipart
    @POST("Ruangan/TambahRuangan")
    Call<PostPutDellKelas> postKelas(@Part MultipartBody.Part file,
                                       @Part("ruang") RequestBody ruang,
                                       @Part("nama_lab") RequestBody nama_lab,
                                       @Part("lokasi")  RequestBody lokasi);
//    POst Fasilitas
    @FormUrlEncoded
    @POST("Fasilitas")
    Call<PostPutDellFasilitas> postFasilitas (@Field("id_ruang") String id_ruang,
                                             @Field("nama_fasilitas") String nama_fasilitas,
                                             @Field("jumlah") String jumlah);
//    POst Jadwal
    @FormUrlEncoded
    @POST("Jadwal")
    Call<PostPutDellJadwal> postJadwal (@Field("id_kelas") String id_kelas,
                                        @Field("hari") String hari,
                                        @Field("waktu_mulai") String waktu_mulai,
                                        @Field("waktu_selesai") String waktu_selesai,
                                        @Field("matkul") String matkul,
                                        @Field("sks") String sks,
                                        @Field("jam") String jam,
                                        @Field("kelas") String kelas,
                                        @Field("pengajar") String pengajar);

    @FormUrlEncoded
    @POST("User")
    Call<PostPutDellUser> postUser (@Field("nim") String nim,
                                    @Field("nama") String nama,
                                    @Field("email") String email,
                                    @Field("password") String password,
                                    @Field("img") String img,
                                    @Field("kelas") String kelas,
                                    @Field("level") String level);

    //   get all User
    @GET("User/UserAll")
    Call<GetUser> getUser();

//    POst Fasilitas
    @FormUrlEncoded
    @POST("Peminjaman/CariNim")
    Call<PostPutDellUser> cariUser (@Field("nim") String nim);

    //    Post meminjam
    @Multipart
    @POST("Peminjaman/Meminjam")
    Call<PostPutDellPeminjaman> meminjamBaru(@Part MultipartBody.Part file,
                                     @Part("id_kelas") RequestBody id_kelas,
                                     @Part("id_user") RequestBody id_user,
                                     @Part("status")  RequestBody status,
                                     @Part("id_admin") RequestBody id_admin);
    //   get all Peminjaman
    @GET("Peminjaman")
    Call<GetPeminjaman> getpeminjaman();

}
