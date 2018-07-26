package id.tiregdev.atentik.Activity;

import android.content.pm.ActivityInfo;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import id.tiregdev.atentik.Adapter.adapter_log;
import id.tiregdev.atentik.Adapter.adapter_log_mahasiswa;
import id.tiregdev.atentik.AtentikClient;
import id.tiregdev.atentik.Model.object_log;
import id.tiregdev.atentik.Model.object_log_mahasiswa;
import id.tiregdev.atentik.R;
import id.tiregdev.atentik.Util.AtentikHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class log_mahasiswa extends AppCompatActivity {

    RecyclerView rView;
    LinearLayoutManager  lLayout;
    String tokens;
    Locale localeID = new Locale("in", "ID");
    String jam = new SimpleDateFormat("HH:mm:ss ZZZZ", localeID).format(new Date());
    String tanggals = new SimpleDateFormat("dd-MM-yyyy", localeID).format(new Date());
    String hari = new SimpleDateFormat("EEEE", localeID).format(new Date());
    List<object_log_mahasiswa> logs = new ArrayList<>();
    List<object_log_mahasiswa> logs2 = new ArrayList<>();
    adapter_log_mahasiswa rcAdapter;
    SearchView searchView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_mahasiswa);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
        CekToken ct = new CekToken();
        tokens = ct.Cek(this);
        setSearch();
        setupAdapterLog();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                // todo: goto back activity from here
                log_mahasiswa.this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setSearch(){
        searchView = findViewById(R.id.search_bar);
        EditText searchEditText = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(getResources().getColor(R.color.putih));
        searchEditText.setHintTextColor(getResources().getColor(R.color.background));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });
    }

    public void setupAdapterLog(){
        AtentikClient client = AtentikHelper.getClient().create(AtentikClient.class);
        Call<List<object_log_mahasiswa>> call = client.lihatLogAbsenMahasiswa("Bearer " + tokens, tanggals, hari, jam);
        call.enqueue(new Callback<List<object_log_mahasiswa>>() {
            @Override
            public void onResponse(Call<List<object_log_mahasiswa>> call, Response<List<object_log_mahasiswa>> response) {
                if(response.isSuccessful())
                {
                    List<object_log_mahasiswa> simpan = response.body();
                    for(int i = 0; i < simpan.size(); i++)
                    {
                        logs.add(new object_log_mahasiswa(simpan.get(i).getNama(),simpan.get(i).getNim(),simpan.get(i).getJam_hadir(), simpan.get(i).getWaktu_telat(), simpan.get(i).getKompen()));
                    }
                    List<object_log_mahasiswa> rowListItem = logs;
                    logs2.addAll(logs);
                    lLayout = new LinearLayoutManager(log_mahasiswa.this);

                    rView = findViewById(R.id.rView);
                    rView.setLayoutManager(lLayout);

                    rcAdapter = new adapter_log_mahasiswa(log_mahasiswa.this, logs);
                    rView.setAdapter(rcAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<object_log_mahasiswa>> call, Throwable t) {
                Toast.makeText(log_mahasiswa.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void filter(String text) {
        logs.clear();
        if(text.isEmpty()){
            logs.addAll(logs2);
        }
        else{
            text = text.toLowerCase();
            for(int i = 0; i<logs2.size(); i++){
                if(logs2.get(i).getNama().toLowerCase().contains(text) ||  logs2.get(i).getNim().toLowerCase().contains(text)){
                    logs.add(logs2.get(i));
                }
            }
        }
        rcAdapter.notifyDataSetChanged();
    }

    private List<object_log_mahasiswa> getAllItemList(){
        List<object_log_mahasiswa> allItems = new ArrayList<>();
//        allItems.add(new object_log_mahasiswa("Muhammad Hafizh", "4314010034","08.10", "10", "1"));
//        allItems.add(new object_log_mahasiswa("Ilham Al Fajri", "4314010023","10.15", "15", "1"));
//        allItems.add(new object_log_mahasiswa("Kadek Pandu", "4314010013","13.00", "0", "0"));
//        allItems.add(new object_log_mahasiswa("Rifqie Fadlurrahman", "4414010036","08.10", "10", "1"));
//        allItems.add(new object_log_mahasiswa("Zaky Nuralifi", "4414010034","10.15", "15", "1"));

        return allItems;
    }
}
