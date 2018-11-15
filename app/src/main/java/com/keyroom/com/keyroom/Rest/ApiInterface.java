package com.keyroom.com.keyroom.Rest;

import com.keyroom.com.keyroom.Model.GetDetailKelas;
import com.keyroom.com.keyroom.Model.GetKelas;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ApiInterface {

//    Login
//    @FormUrlEncoded
//    @POST("Login")
//    Call<PostPutDellKaryawan> getLogin(@Field("nohp") String nohp,
//                                       @Field("password") String password);

//   get all Kelas
    @GET("Kelas/RuanganAll")
    Call<GetKelas> getKelas();

    @FormUrlEncoded
    @POST("Kelas/Ruangan")
    Call<GetDetailKelas> getDetail(@Field("id") String id);

}
