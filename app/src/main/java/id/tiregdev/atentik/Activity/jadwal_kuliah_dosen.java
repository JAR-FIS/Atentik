package id.tiregdev.atentik.Activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import id.tiregdev.atentik.Fragment.jadwal_jumat;
import id.tiregdev.atentik.Fragment.jadwal_jumat_dosen;
import id.tiregdev.atentik.Fragment.jadwal_kamis;
import id.tiregdev.atentik.Fragment.jadwal_kamis_dosen;
import id.tiregdev.atentik.Fragment.jadwal_rabu;
import id.tiregdev.atentik.Fragment.jadwal_rabu_dosen;
import id.tiregdev.atentik.Fragment.jadwal_selasa;
import id.tiregdev.atentik.Fragment.jadwal_selasa_dosen;
import id.tiregdev.atentik.Fragment.jadwal_senin;
import id.tiregdev.atentik.Fragment.jadwal_senin_dosen;
import id.tiregdev.atentik.R;

public class jadwal_kuliah_dosen extends AppCompatActivity {

    ViewPager pager;
    LinearLayout wrapJadwal;
    Toolbar toolbar;
    TabLayout tab;
    TextView tgl;
    Locale localeID = new Locale("in", "ID");
    String tokens, hari;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_kuliah);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);

        CekToken ct = new CekToken();
        tokens = ct.Cek(this);
        tgl = findViewById(R.id.tgl);
        String tanggal = new SimpleDateFormat("dd MMMM yyyy", localeID).format(new Date());
        hari = new SimpleDateFormat("EEEE", localeID).format(new Date());
        String haritanggal = hari + ", " + tanggal;
        tgl.setText(haritanggal);
        setMore();
        setUpPager();
        setColour();
    }

    public void setColour(){
        wrapJadwal = findViewById(R.id.wrapJadwal);
        wrapJadwal.setBackgroundColor(getResources().getColor(R.color.AbuBG));

        toolbar = findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.AbuBG));

        tab = findViewById(R.id.tabs);
        tab.setBackgroundColor(getResources().getColor(R.color.AbuBG));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                // todo: goto back activity from here
                jadwal_kuliah_dosen.this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setMore(){
        ImageView more = findViewById(R.id.more);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final LayoutInflater factory = LayoutInflater.from(jadwal_kuliah_dosen.this);
                final View exitDialogView = factory.inflate(R.layout.dialog_set_jadwal_dosen, null);
                final AlertDialog exitDialog = new AlertDialog.Builder(jadwal_kuliah_dosen.this).create();

                exitDialog.setView(exitDialogView);
                exitDialogView.findViewById(R.id.setJadwal1).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        exitDialog.dismiss();
                        Intent i = new Intent(getBaseContext(), set_jadwal_masuk_dosen_2.class);
                        startActivity(i);
                    }
                });
                exitDialogView.findViewById(R.id.setJadwal2).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        exitDialog.dismiss();
                        Toast.makeText(getBaseContext(), "Fitur ini akan disempurnakan", Toast.LENGTH_SHORT).show();
                    }
                });
                exitDialogView.findViewById(R.id.setLogMahasiswa).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        exitDialog.dismiss();
                        Intent i = new Intent(getBaseContext(), log_mahasiswa.class);
                        startActivity(i);
                    }
                });
                exitDialog.show();
            }
        });
    }

    public void setUpPager(){
        pager = findViewById(R.id.pager);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(pager);

        setupViewPager(pager);
        switch (hari) {
            case "Senin":
                pager.setCurrentItem(0);
                break;
            case "Selasa":
                pager.setCurrentItem(1);
                break;
            case "Rabu":
                pager.setCurrentItem(2);
                break;
            case "Kamis":
                pager.setCurrentItem(3);
                break;
            case "Jumat":
                pager.setCurrentItem(4);
                break;
        }
    }

    private void setupViewPager(ViewPager viewPager) {

        jadwal_kuliah_dosen.Adapter adapter = new jadwal_kuliah_dosen.Adapter(getSupportFragmentManager());
        adapter.addFragment(new jadwal_senin_dosen(), "SEN");
        adapter.addFragment(new jadwal_selasa_dosen(), "SEL");
        adapter.addFragment(new jadwal_rabu_dosen(), "RAB");
        adapter.addFragment(new jadwal_kamis_dosen(), "KAM");
        adapter.addFragment(new jadwal_jumat_dosen(), "JUM");

        viewPager.setAdapter(adapter);
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
