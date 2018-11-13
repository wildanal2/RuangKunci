package com.keyroom.com.keyroom.Rest;

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

//    Kendaraan
//    @GET("Kendaraan")
//    Call<GetKendaraan> getKendaraan();

//    @FormUrlEncoded
//    @POST("Kendaraan")
//    Call<PostPutDellKendaraan> postKendaraan(@Field("nama") String nama,
//                                             @Field("harga") String harga,
//                                             @Field("img") String img);
//    @FormUrlEncoded
//    @PUT("Kendaraan")
//    Call<PostPutDellKendaraan> putKendaraan(@Field("id") String id,
//                                            @Field("nama") String nama,
//                                            @Field("harga") String harga,
//                                            @Field("img") String img);
//    @FormUrlEncoded
//    @HTTP(method = "DELETE", path = "Kendaraan", hasBody = true)
//    Call<PostPutDellKendaraan> deleteKendaraan(@Field("id") String id);
//      Kendaraan END

//    Karyawan Start
//    @GET("Karyawan")
//    Call<GetKaryawan> getKaryawan();
//
//    @FormUrlEncoded
//    @POST("Karyawan")
//    Call<PostPutDellKaryawan> postKaryawan(@Field("nama") String nama,
//                                           @Field("nohp") String nohp,
//                                           @Field("alamat") String alamat,
//                                           @Field("password") String password,
//                                           @Field("level") String level);
//    @FormUrlEncoded
//    @PUT("Karyawan")
//    Call<PostPutDellKaryawan> putKaryawan(@Field("id") String id,
//                                          @Field("nama") String nama,
//                                          @Field("nohp") String nohp,
//                                          @Field("alamat") String alamat,
//                                          @Field("password") String password,
//                                          @Field("level") String level);
//    @FormUrlEncoded
//    @HTTP(method = "DELETE", path = "Karyawan", hasBody = true)
//    Call<PostPutDellKaryawan> deleteKaryawan(@Field("id") String id);
////  Karyawan END
//
//
////    Kondisi Motor Start
//    @GET("Kondisi")
//    Call<GetKondisi> getKondisi();
//
//    @FormUrlEncoded
//    @POST("kondisi")
//    Call<PostPutDellKondisi> postKondisi(@Field("nama") String nama,
//                                         @Field("harga") String harga,
//                                         @Field("img") String img);
//    @FormUrlEncoded
//    @PUT("kondisi")
//    Call<PostPutDellKondisi> putKondisi(@Field("id") String id,
//                                        @Field("nama") String nama,
//                                        @Field("harga") String harga,
//                                        @Field("img") String img);
//    @FormUrlEncoded
//    @HTTP(method = "DELETE", path = "kondisi", hasBody = true)
//    Call<PostPutDellKondisi> deleteKondisi(@Field("id") String id);
//
//
////        Transaksi Start
//    @GET("Transaksi")
//    Call<GetTransaksi> getTransaksi();
//
//    @FormUrlEncoded
//    @POST("UserTransaksi")
//    Call<GetTransaksi> getTransaksiBy(@Field("kasir") String kasir,
//                                      @Field("where") String where);
//
//    @FormUrlEncoded
//    @POST("Transaksi")
//    Call<PostputDellTransaksi> postTransaksi(@Field("nopol") String nopol,
//                                             @Field("kendaraan") String kendaraan,
//                                             @Field("harga_kendaraan") String harga_kendaraan,
//                                             @Field("kondisi") String kondisi,
//                                             @Field("harga_kondisi") String harga_kondisi,
//                                             @Field("total") String total,
//                                             @Field("kasir") String kasir);
//    @FormUrlEncoded
//    @PUT("Transaksi")
//    Call<PostputDellTransaksi> putTransaksi(@Field("id") String id,
//                                            @Field("nopol") String nopol,
//                                            @Field("kendaraan") String kendaraan,
//                                            @Field("harga_kendaraan") String harga_kendaraan,
//                                            @Field("kondisi") String kondisi,
//                                            @Field("harga_kondisi") String harga_kondisi,
//                                            @Field("total") String total,
//                                            @Field("kasir") String kasir);
//    @FormUrlEncoded
//    @HTTP(method = "DELETE", path = "Transaksi", hasBody = true)
//    Call<PostputDellTransaksi> deleteTransaksi(@Field("id") String id);
////  Transaksi END

}
