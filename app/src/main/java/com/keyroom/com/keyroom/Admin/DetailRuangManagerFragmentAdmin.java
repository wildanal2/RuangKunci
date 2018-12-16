package com.keyroom.com.keyroom.Admin;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.keyroom.com.keyroom.Adapter.RecyclerAdapterFasilitas;
import com.keyroom.com.keyroom.Adapter.RecyclerAdapterJadwal;
import com.keyroom.com.keyroom.Model.Fasilitas;
import com.keyroom.com.keyroom.Model.GetDetailKelas;
import com.keyroom.com.keyroom.Model.Jadwal;
import com.keyroom.com.keyroom.Model.Kelas;
import com.keyroom.com.keyroom.Model.PostPutDellFasilitas;
import com.keyroom.com.keyroom.Model.PostPutDellJadwal;
import com.keyroom.com.keyroom.R;
import com.keyroom.com.keyroom.Rest.ApiClient;
import com.keyroom.com.keyroom.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailRuangManagerFragmentAdmin extends Fragment {

    Bundle bndl;
    View viw;
    private ApiInterface mApiInterface;
    Kelas mKelas;
    TimePickerDialog time_dialog;


    @Override
    public View onCreateView( LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        viw =inflater.inflate(R.layout.fragment_admin_detailruangmanager,container,false);
        bndl = getArguments();

        initialize();
        init_listener();
        return viw;
    }


    public void initialize(){
        //init
        final ImageView img_poster = viw.findViewById(R.id.img_detailruang_admin);
        final TextView txv_namaruangan = viw.findViewById(R.id.txv_namaruangdatail_admin);
        final TextView txv_lokasi = viw.findViewById(R.id.txv_lokasi_detail_admin);
        final RecyclerView mRecyclerViewJadwal = viw.findViewById(R.id.recycler_jadwl_detail_admin);
        final RecyclerView mRecyclerViewFasilitas = viw.findViewById(R.id.recycler_fasilitas_detail_admin);

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<GetDetailKelas> getDetail = mApiInterface.getDetail(bndl.getString("id"));
        getDetail.enqueue(new Callback<GetDetailKelas>() {
            @Override
            public void onResponse(Call<GetDetailKelas> call, Response<GetDetailKelas> response) {
                mKelas = response.body().getDataKelas();
                List<Jadwal> mjadwal = response.body().getListDatajadwal();
                List<Fasilitas> mFasilitas = response.body().getListFasilitas();

                // set textview
                Glide.with(getContext()).load(ApiClient.BASE_ASSETS+mKelas.getImg()).into(img_poster);
                txv_namaruangan.setText(mKelas.getRuang());
                txv_lokasi.setText("Lokasi : "+mKelas.getLokasi());

                // init recycler jadwal
                RecyclerAdapterJadwal mAdapterJadwal = new RecyclerAdapterJadwal(mjadwal);
                mRecyclerViewJadwal.setLayoutManager(new LinearLayoutManager(getActivity()));
                mRecyclerViewJadwal.setAdapter(mAdapterJadwal);

                // init recycler Fasilitas
                RecyclerAdapterFasilitas mAdapterFasilitas = new RecyclerAdapterFasilitas(getContext(),mFasilitas);
                mRecyclerViewFasilitas.setLayoutManager(new LinearLayoutManager(getActivity()));
                mRecyclerViewFasilitas.setAdapter(mAdapterFasilitas);
            }

            @Override
            public void onFailure(Call<GetDetailKelas> call, Throwable t) {     Log.e("Retrofit Get", t.toString()); }
        });
    }

    void init_listener(){

        // button add new fasilitas
        FloatingActionButton btn_addfasilitas = viw.findViewById(R.id.btn_addfasilitaskelas_admin);
        btn_addfasilitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // conf menampilkan dialog
                final Dialog dialog_addfasilitas = new Dialog(getActivity());
                dialog_addfasilitas.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog_addfasilitas.setContentView(R.layout.dialognewfasilitas);

                // init data dari layoutdialog
                Button btn_save = dialog_addfasilitas.findViewById(R.id.btn_saveNewfasilitas);
                ImageView btn_close1 = dialog_addfasilitas.findViewById(R.id.btn_close_dialog);
                Button btn_batal = dialog_addfasilitas.findViewById(R.id.btn_batal);

                final EditText et_namafasilitas =dialog_addfasilitas.findViewById(R.id.et_namafasilitas_admin);
                final EditText et_jumlahfasilitas =dialog_addfasilitas.findViewById(R.id.et_jumlahfasilitas_admin);

//              btn_close dialogg
                btn_batal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog_addfasilitas.hide();
                    }
                });
                btn_close1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog_addfasilitas.hide();
                    }
                });

                // button simpan pada dialog
                btn_save.setEnabled(true);
                btn_save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        //call add fasilitas  rretrofit
                        Call<PostPutDellFasilitas> newFasilitas = mApiInterface.postFasilitas(   bndl.getString("id"),
                                                                                        et_namafasilitas.getText().toString(),
                                                                                        et_jumlahfasilitas.getText().toString());
                        newFasilitas.enqueue(new Callback<PostPutDellFasilitas>() {
                            @Override
                            public void onResponse(Call<PostPutDellFasilitas> call, Response<PostPutDellFasilitas> response) {
                                Toast.makeText(getContext(),"save sukses",Toast.LENGTH_SHORT).show();
                                dialog_addfasilitas.hide();
                                initialize();
                            }

                            @Override
                            public void onFailure(Call<PostPutDellFasilitas> call, Throwable t) {
                                Toast.makeText(getContext(),"Something Worng : "+t.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });

                // menampilkan dialog
                dialog_addfasilitas.show();
            }
        });


        // button add new jadwal
        FloatingActionButton btn_addjadwal = viw.findViewById(R.id.btn_addjadwalkelas_admin);
        btn_addjadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // conf menampilkan dialog
                final Dialog dialog_addjadwal = new Dialog(getActivity());
                dialog_addjadwal.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog_addjadwal.setContentView(R.layout.dialognewjadwal);
                dialog_addjadwal.setCancelable(false);

                // init data inputan layoutdialog
                final EditText hari = dialog_addjadwal.findViewById(R.id.et_hari_newjadwal);
                final EditText matkul = dialog_addjadwal.findViewById(R.id.et_matkul_newjadwal);
                final EditText kelas = dialog_addjadwal.findViewById(R.id.et_kelas_newjadwal);
                final EditText dosen = dialog_addjadwal.findViewById(R.id.et_dosen_newjadwal);
                final TextView jammulai = dialog_addjadwal.findViewById(R.id.et_waktmulai_newjadwal);
                final TextView jamselesai = dialog_addjadwal.findViewById(R.id.et_wktuselesai_newjadwal);
                final EditText jmlahsks = dialog_addjadwal.findViewById(R.id.et_jmlhsks_newjadwal);
                final EditText jmlahjam = dialog_addjadwal.findViewById(R.id.et_jmlahjam_newjadwal);
                // button
                Button btn_savenewjadwal = dialog_addjadwal.findViewById(R.id.btn_saveNewjadwal);
                Button btn_batal = dialog_addjadwal.findViewById(R.id.btn_backkk);
                ImageView btn_close = dialog_addjadwal.findViewById(R.id.btn_close_dialog);

                // jamm sett mulai
                jammulai.setPaintFlags(jammulai.getPaintFlags()|Paint.UNDERLINE_TEXT_FLAG);
                jammulai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        time_dialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                                jammulai.setText(String.format("%02d:%02d", hourOfDay, minutes));
                            }
                        }, 0, 0, true);
                        time_dialog.show();
                    }
                });

                // jamm sett selesai
                jamselesai.setPaintFlags(jammulai.getPaintFlags()|Paint.UNDERLINE_TEXT_FLAG);
                jamselesai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        time_dialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                                jamselesai.setText(String.format("%02d:%02d", hourOfDay, minutes));
                            }
                        }, 0, 0, true);
                        time_dialog.show();
                    }
                });


                // event btn save
                btn_savenewjadwal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //call add new jadwal  rretrofit
                        Call<PostPutDellJadwal> newJadwal = mApiInterface.postJadwal(bndl.getString("id"),
                                                                                        hari.getText().toString(),
                                                                                        jammulai.getText().toString(),
                                                                                        jamselesai.getText().toString(),
                                                                                        matkul.getText().toString(),
                                                                                        jmlahsks.getText().toString(),
                                                                                        jmlahjam.getText().toString(),
                                                                                        kelas.getText().toString(),
                                                                                        dosen.getText().toString());
                        newJadwal.enqueue(new Callback<PostPutDellJadwal>() {
                            @Override
                            public void onResponse(Call<PostPutDellJadwal> call, Response<PostPutDellJadwal> response) {
                                Toast.makeText(getContext(),"save sukses",Toast.LENGTH_SHORT).show();
                                dialog_addjadwal.hide();
                                initialize();
                            }

                            @Override
                            public void onFailure(Call<PostPutDellJadwal> call, Throwable t) {
                                Toast.makeText(getContext(),"Something Worng : "+t.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });

                // event btn cancel
                btn_batal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog_addjadwal.hide();
                    }
                });
                btn_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog_addjadwal.hide();
                    }
                });

                // menampilkan dialog
                dialog_addjadwal.show();
            }
        });

    }

}