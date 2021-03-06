package id.tiregdev.atentik.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import id.tiregdev.atentik.Activity.CekToken;
import id.tiregdev.atentik.Activity.edit_profile_dosen;
import id.tiregdev.atentik.Activity.jadwal_kuliah_dosen;
import id.tiregdev.atentik.Activity.tracking_dosen;
import id.tiregdev.atentik.Adapter.adapter_kompen_terbanyak_dosen;
import id.tiregdev.atentik.AtentikClient;
import id.tiregdev.atentik.Model.object_total;
import id.tiregdev.atentik.Model.object_uang_kompen;
import id.tiregdev.atentik.Util.AtentikHelper;
import id.tiregdev.atentik.Model.object_dosen;
import id.tiregdev.atentik.Model.object_kompen_terbanyak;
import id.tiregdev.atentik.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by HVS on 13/03/18.
 */

public class home_dosen extends Fragment {

    ImageView foto;
    CardView openWeb, wrapJadwal, cvProfile, tracking;
    Button editProfile;
    RecyclerView rView;
    LinearLayoutManager  lLayout;
    View v;
    TextView email, tlp, imei, status, tgl, totalMasuk, totalTelat, totalIzin, totalGakMasuk, totalKompen, totalUang;
    String tokens;
    Locale localeID = new Locale("in", "ID");
    String jam = new SimpleDateFormat("HH:mm:ss ZZZZ", localeID).format(new Date());
    String tanggal = new SimpleDateFormat("dd MMMM yyyy", localeID).format(new Date());
    String tanggals = new SimpleDateFormat("dd-MM-yyyy", localeID).format(new Date());
    String hari = new SimpleDateFormat("EEEE", localeID).format(new Date());
    String haritanggal = hari + ", " + tanggal;
    List<object_kompen_terbanyak> kompenMhsw = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_home_dosen, container, false);
        CekToken ct = new CekToken();
        tokens = ct.Cek(getActivity());

        email = v.findViewById(R.id.email);
        tlp = v.findViewById(R.id.tlp);
        imei = v.findViewById(R.id.imei);
        status = v.findViewById(R.id.status);
        tgl = v.findViewById(R.id.tgl);
        foto = v.findViewById(R.id.foto);
        totalMasuk = v.findViewById(R.id.totalMasuk);
        totalTelat = v.findViewById(R.id.totalTelat);
        totalIzin = v.findViewById(R.id.totalIzin);
        totalGakMasuk = v.findViewById(R.id.totalGakMasuk);
//        totalSakit = v.findViewById(R.id.totalSakit);
//        totalBekom = v.findViewById(R.id.totalBekom);
        totalKompen = v.findViewById(R.id.totalKompen);
        totalUang = v.findViewById(R.id.totalUang);

        AtentikClient client = AtentikHelper.getClient().create(AtentikClient.class);
        Call<object_dosen> call = client.profilDosen("Bearer " + tokens);
        call.enqueue(new Callback<object_dosen>() {
            @Override
            public void onResponse(Call<object_dosen> call, Response<object_dosen> response) {
                if(response.isSuccessful())
                {
                    email.setText(response.body().getEmail());
                    tlp.setText(response.body().getTlp());
//                    imei.setText(response.body().getImei_hp());
                    status.setText(response.body().getStatus_dosen());
                    Glide.with(getContext()).load("https://atentik.id/assets/img/faces/" + response.body().getPhoto()).into(foto);
                }
            }
            @Override
            public void onFailure(Call<object_dosen> call, Throwable t) {
                Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        Call<object_total> calls = client.totalUangKompen("Bearer " + tokens, tanggals);
        calls.enqueue(new Callback<object_total>() {
            @Override
            public void onResponse(Call<object_total> call, Response<object_total> response) {
                if(response.isSuccessful())
                {
                    if(response.body() != null)
                    {
                        totalMasuk.setText(String.valueOf(response.body().getTotal_masuk()));
                        totalTelat.setText(String.valueOf(response.body().getTotal_telat()));
                        totalIzin.setText(String.valueOf(response.body().getTotal_izin()));
                        totalGakMasuk.setText(String.valueOf(response.body().getTotal_tidak_masuk()));
//                        totalSakit.setText(String.valueOf(response.body().getTotal_sakit()));
//                        totalBekom.setText(String.valueOf(response.body().getTotal_bekom()));
                        totalKompen.setText(String.valueOf(response.body().getTotal_kompen()));
                        totalUang.setText(String.valueOf(response.body().getBiaya()) + " IDR");
                    }
                    else
                    {
                        totalMasuk.setText("0");
                        totalTelat.setText("0");
                        totalIzin.setText("0");
                        totalGakMasuk.setText("0");
//                        totalSakit.setText("0");
//                        totalBekom.setText("0");
                        totalKompen.setText("0");
                        totalUang.setText("0 IDR");
                    }
                }
            }
            @Override
            public void onFailure(Call<object_total> call, Throwable t) {
                Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        tgl.setText("Jumlah data hingga " + hari + ", " + tanggal);
        findId();
        setupAdapterkompen_terbanyak();
        return v;
    }

    public void findId(){
        final String url = "https://www.atentik.com/dashboard";
        openWeb = v.findViewById(R.id.openWeb);
        openWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            }
        });

        wrapJadwal = v.findViewById(R.id.wrapJadwal);
        wrapJadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), jadwal_kuliah_dosen.class);
                startActivity(i);
            }
        });

        cvProfile = v.findViewById(R.id.cvProfile);
        cvProfile.setPreventCornerOverlap(false);
        cvProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), edit_profile_dosen.class);
                startActivity(i);
            }
        });

        editProfile = v.findViewById(R.id.editProfileBtn);
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), edit_profile_dosen.class);
                startActivity(i);
            }
        });

        tracking = v.findViewById(R.id.tracking);
        tracking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), tracking_dosen.class);
                startActivity(i);
            }
        });
    }

    public void setupAdapterkompen_terbanyak(){
        AtentikClient client = AtentikHelper.getClient().create(AtentikClient.class);
        Call<List<object_kompen_terbanyak>> callz = client.kompenTerbanyakDosen("Bearer " + tokens, tanggals);
        callz.enqueue(new Callback<List<object_kompen_terbanyak>>() {
            @Override
            public void onResponse(Call<List<object_kompen_terbanyak>> call, Response<List<object_kompen_terbanyak>> response) {
                if(response.isSuccessful())
                {
                    List<object_kompen_terbanyak> simpan = response.body();
                    for(int i = 0; i < simpan.size(); i++)
                    {
                        kompenMhsw.add(new object_kompen_terbanyak(String.valueOf(i+1)+".",simpan.get(i).getNama(),simpan.get(i).getNama_kelas(),simpan.get(i).getKompen() + " jam", simpan.get(i).getStatus_sp(), simpan.get(i).getPhoto()));
                    }
                    List<object_kompen_terbanyak> rowListItem = kompenMhsw;
                    lLayout = new LinearLayoutManager(getActivity());

                    rView = v.findViewById(R.id.rView);
                    rView.setLayoutManager(lLayout);

                    adapter_kompen_terbanyak_dosen rcAdapter = new adapter_kompen_terbanyak_dosen(getActivity(), rowListItem);
                    rView.setAdapter(rcAdapter);

                    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), lLayout.getOrientation());
                    dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.line_shape));
                    rView.addItemDecoration(dividerItemDecoration);
                }
            }

            @Override
            public void onFailure(Call<List<object_kompen_terbanyak>> call, Throwable t) {
                Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}

