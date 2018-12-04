package com.keyroom.com.keyroom.Rest;

import com.keyroom.com.keyroom.Model.GetDetailKelas;
import com.keyroom.com.keyroom.Model.GetKelas;
import com.keyroom.com.keyroom.Model.GetUser;
import com.keyroom.com.keyroom.Model.PostPutDellFasilitas;
import com.keyroom.com.keyroom.Model.PostPutDellJadwal;
import com.keyroom.com.keyroom.Model.PostPutDellKelas;

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

    //   get all User
    @GET("User/UserAll")
    Call<GetUser> getUser();

}
